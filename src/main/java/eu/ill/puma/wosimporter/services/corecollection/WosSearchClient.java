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
import eu.ill.puma.wosimporter.domain.exception.BusyException;
import eu.ill.puma.wosimporter.services.corecollection.exception.WosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class WosSearchClient {

	private static final Logger log = LoggerFactory.getLogger(WosSearchClient.class);

	private WosProxyPool proxyPool;

	@Autowired
	public WosSearchClient(WosProxyPool proxyPool) {
		this.proxyPool = proxyPool;
	}

	private QueryParameters buildBaseQueryParameters() {

		//edition
		// Science Citation Index Expanded
		EditionDesc desc_sci = new EditionDesc();
		desc_sci.setCollection("WOS");
		desc_sci.setEdition("SCI");

		// Conference Proceedings Citation Index - Science
		EditionDesc desc_istp = new EditionDesc();
		desc_istp.setCollection("WOS");
		desc_istp.setEdition("ISTP");

		// Index Chemicus
		EditionDesc desc_ic = new EditionDesc();
		desc_ic.setCollection("WOS");
		desc_ic.setEdition("IC");

		// Current Chemical Reactions
		EditionDesc desc_ccr = new EditionDesc();
		desc_ccr.setCollection("WOS");
		desc_ccr.setEdition("CCR");

		//query parameters
		QueryParameters queryParameters = new QueryParameters();
		queryParameters.setDatabaseId("WOS");
		queryParameters.setQueryLanguage("en");
		queryParameters.getEditions().add(desc_sci);
		queryParameters.getEditions().add(desc_istp);
		queryParameters.getEditions().add(desc_ic);
		queryParameters.getEditions().add(desc_ccr);

		return queryParameters;
	}


	/**
	 * Run a wos search
	 *
	 * @param query
	 * @return
	 */
	public WosResultBag search(String query) throws WosException, BusyException {

		QueryParameters queryParameters = this.buildBaseQueryParameters();
		queryParameters.setUserQuery(query);

		//retrieveIds parameters
		RetrieveParameters retrieveParameters = new RetrieveParameters();
		retrieveParameters.setFirstRecord(1);
		retrieveParameters.setCount(0);

		return this.runSearch(retrieveParameters, queryParameters, null, null);
	}

	/**
	 * Run a wos search
	 *
	 * @param query
	 * @return
	 */
	public WosResultBag search(String query, Integer year, Integer month) throws WosException, BusyException {

		//build query
		Integer currentYear = Year.now().getValue();
		String queryString = "(" + query + ") AND ( PY=" + year;

		for (int i = 1; i <= currentYear - year; ++i) {
			queryString += " OR PY=" + (year + i);
		}
		queryString += " )";
		log.debug("query string : " + queryString);

		QueryParameters queryParameters = this.buildBaseQueryParameters();
		queryParameters.setUserQuery(query);

		//sortField
		SortField sortField = new SortField();
		sortField.setName("PY");
		sortField.setSort("D");

		//retrieveIds parameters
		RetrieveParameters retrieveParameters = new RetrieveParameters();
		retrieveParameters.setFirstRecord(1);
		retrieveParameters.setCount(0);
		retrieveParameters.getSortField().add(sortField);

		return this.runSearch(retrieveParameters, queryParameters, year, month);
	}

	/**
	 * retrieveIds a document by the passing UID
	 *
	 * @param UID
	 * @return
	 */
	public WosResultBag getByWOSUID(String UID) throws WosException, BusyException {

		//query parameters
		RetrieveParameters retrieveParameters = new RetrieveParameters();
		retrieveParameters.setFirstRecord(1);
		retrieveParameters.setCount(0);

		QueryParameters queryParameters = this.buildBaseQueryParameters();

		//id to fetch
		List<String> UIDlist = new ArrayList();
		UIDlist.add(UID);

		return this.retrieveIds(UIDlist, retrieveParameters, queryParameters);
	}


	/**
	 * retrieveIds a document by the passing UIDs
	 *
	 * @param UIDs
	 * @return
	 */
	public WosResultBag getByWOSUID(List<String> UIDs) throws WosException, BusyException {

		//query parameters
		RetrieveParameters retrieveParameters = new RetrieveParameters();
		retrieveParameters.setFirstRecord(1);
		retrieveParameters.setCount(0);

		QueryParameters queryParameters = this.buildBaseQueryParameters();

		return this.retrieveIds(UIDs, retrieveParameters, queryParameters);
	}


	/**
	 * retrieveIds the uid with the retrieveIds parameter
	 *
	 * @param UIDlist
	 * @param retrieveParameters
	 * @return
	 * @throws WosException
	 */
	private WosResultBag retrieveIds(List<String> UIDlist, RetrieveParameters retrieveParameters, QueryParameters queryParameters) throws WosException, BusyException {
		ProxyLinkedFullRecordSearchResults results = this.proxyPool.retrieveById("WOS", UIDlist, "en", retrieveParameters);

		return new WosResultBag(results.getLinkedProxyId(), this.proxyPool, retrieveParameters, queryParameters, results.getSearchResults(), null, null);
	}

	/**
	 * run a search with retry if the session id is invalid
	 *
	 * @param retrieveParameters
	 * @param queryParameters
	 * @return
	 * @throws WosException
	 */
	private WosResultBag runSearch(RetrieveParameters retrieveParameters, QueryParameters queryParameters, Integer year, Integer month) throws WosException, BusyException {
		ProxyLinkedFullRecordSearchResults results = this.proxyPool.search(queryParameters, retrieveParameters);

		return new WosResultBag(results.getLinkedProxyId(), this.proxyPool, retrieveParameters, queryParameters, results.getSearchResults(), year, month);
	}
}
