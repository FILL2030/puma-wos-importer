/*
 * Copyright 2019 Institut Laue–Langevin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.ill.puma.wosimporter.services.corecollection;

import com.thomsonreuters.wokmws.v3.woksearch.*;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.exception.ApiException;
import eu.ill.puma.wosimporter.domain.exception.BusyException;
import eu.ill.puma.wosimporter.services.corecollection.exception.WosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WosResultBag {

	private static final Logger log = LoggerFactory.getLogger(WosResultBag.class);

	//wos client objects
	private int proxyId;
	private WosProxyPool proxyPool;
	private String queryId;
	private RetrieveParameters retrieveParameters;
	private QueryParameters queryParameters;
	private Integer month;
	private Integer year;

	//count
	private Integer recordNumber;

	public WosResultBag(int proxyId, WosProxyPool proxyPool, RetrieveParameters retrieveParameters, QueryParameters queryParameters, FullRecordSearchResults results, Integer year, Integer month) {
		this.proxyId = proxyId;
		this.proxyPool = proxyPool;
		this.queryId = results.getQueryId();
		this.recordNumber = results.getRecordsFound();
		this.retrieveParameters = retrieveParameters;
		this.queryParameters = queryParameters;
		this.month = month;
		this.year = year;
	}

	/**
	 * return count record if they exist after the start record. start should be greater than 0
	 *
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Record> getRecords(Integer start, Integer count, boolean retrieveCitation, boolean retrieveReference) throws ApiException, BusyException {

		try {
			//wos client parameter
			retrieveParameters.setFirstRecord(start);
			retrieveParameters.setCount(count);

			return this.getNextResult(retrieveCitation, retrieveReference);

		} catch (WosException e) {
			throw new ApiException(500, e.getMessage());
		}
	}

	private void retrieveSecondaryData(List<Record> recordList, boolean retrieveCitation, boolean retrieveReference) throws WosException, BusyException {
		//retireve citing data and files to download
		for (Record record : recordList) {
			if (retrieveReference) this.retrieveReferences(record);
			if (retrieveCitation) this.retrieveCitation(record);
		}
	}

	public Integer getRecordNumber() {
		return recordNumber;
	}

	public String getQueryId() {
		return queryId;
	}

	/**
	 * @param record
	 */
	private void retrieveReferences(Record record) throws WosException, BusyException {
		log.info("retrieve references for record : " + record.getWosId());
		Set<CitedReference> citedReferences = new HashSet<>();

		//retrieve parameter
		RetrieveParameters retrieveParameters = new RetrieveParameters();
		retrieveParameters.setFirstRecord(1);
		retrieveParameters.setCount(100);

		//get first result set
		CitedReferencesSearchResults searchResults = this.getCitedReferencesSearchResults(record, retrieveParameters);
		citedReferences.addAll(searchResults.getReferences());

		//counters
		Integer currentCount = citedReferences.size();

		//fetch loop
		while (currentCount < searchResults.getRecordsFound()) {
			//replace result cursor
			retrieveParameters.setFirstRecord(currentCount + 1);

			searchResults = this.getCitedReferencesSearchResults(record, retrieveParameters);

			//add result to resultSet
			citedReferences.addAll(searchResults.getReferences());

			//update current counter
			currentCount = citedReferences.size();
		}

		record.setCitedReferences(citedReferences);
	}

	private CitedReferencesSearchResults getCitedReferencesSearchResults(Record record, RetrieveParameters retrieveParameters) throws WosException, BusyException {
		return this.proxyPool.citedReferences("WOS", record.getWosId(), "en", retrieveParameters);
	}


	public void retrieveCitation(Record record) throws WosException, BusyException {
		log.info("retrieve citation for record : " + record.getWosId());
		Set<String> citationStringList = new HashSet();

		//retrieve parameter
		RetrieveParameters retrieveParameters = new RetrieveParameters();
		retrieveParameters.setFirstRecord(1);
		retrieveParameters.setCount(100);

		//edition
		List<EditionDesc> editions = this.queryParameters.getEditions();

		TimeSpan timeSpan = new TimeSpan();
		timeSpan.setBegin("1970-01-01");
		timeSpan.setEnd("2050-01-01");

		int currentCount = -1;
		int recordsFound = 0;
		while (currentCount < recordsFound) {
			ProxyLinkedFullRecordSearchResults linkedSearchResults = this.proxyPool.citingArticles("WOS", record.getWosId(), editions, timeSpan, "en", retrieveParameters);;
			FullRecordSearchResults searchResults = linkedSearchResults.getSearchResults();
			recordsFound = searchResults.getRecordsFound();

			final Matcher matcher = Pattern.compile("WOS:[0-9]{15}").matcher(searchResults.getRecords());

			//find wos ID
			while (matcher.find()) {
				citationStringList.add(matcher.group(0));
			}

			currentCount += retrieveParameters.getCount();

			//replace result cursor
			retrieveParameters.setFirstRecord(retrieveParameters.getFirstRecord() + retrieveParameters.getCount());
		}

		record.setCitation(citationStringList);
	}

	private List<Record> getNextResult(boolean retrieveCitation, boolean retrieveReference) throws WosException, BusyException {
		FullRecordData result = this.proxyPool.retrieve(this.proxyId, this.queryId, this.retrieveParameters);

		List<Record> recordList = WosXmlParser.getRecordsForResult(result).getRecords();

		//retireve citing data and files to download
		retrieveSecondaryData(recordList, retrieveCitation, retrieveReference);

		return recordList;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getYear() {
		return year;
	}
}
