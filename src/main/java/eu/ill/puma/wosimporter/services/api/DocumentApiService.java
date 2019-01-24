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
package eu.ill.puma.wosimporter.services.api;

import eu.ill.puma.wosimporter.domain.Token;
import eu.ill.puma.wosimporter.domain.converter.BaseDocumentConverter;
import eu.ill.puma.wosimporter.domain.document.BaseDocument;
import eu.ill.puma.wosimporter.domain.document.ImporterResponseMetadata;
import eu.ill.puma.wosimporter.domain.document.enumeration.ImporterStatusEnum;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.exception.ApiException;
import eu.ill.puma.wosimporter.domain.exception.BusyException;
import eu.ill.puma.wosimporter.services.amr.AmrClient;
import eu.ill.puma.wosimporter.services.corecollection.WosBackUpService;
import eu.ill.puma.wosimporter.services.corecollection.exception.WosException;
import eu.ill.puma.wosimporter.services.corecollection.WosResultBag;
import eu.ill.puma.wosimporter.services.corecollection.WosSearchClient;
import eu.ill.puma.wosimporter.utils.ResponseService;
import eu.ill.puma.wosimporter.utils.TokenService;
import eu.ill.puma.wosimporter.utils.WosIdValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentApiService {

	private static final Logger log = LoggerFactory.getLogger(DocumentApiService.class);

	private WosSearchClient wosSearchClient;

	private AmrClient amrClient;

	private WosBackUpService wosBackUpService;

	@Value("${app.maxDocPerPage}")
	private Integer maxDocPerPage;

	@Value("${app.debug.maxDocPerOperation}")
	private Integer maxDocPerOperation;

//	@Value("${app.retrieveWosPage}")
//	private boolean retrieveWosPage;

	@Autowired
	public DocumentApiService(WosSearchClient wosSearchClient, AmrClient amrClient, WosBackUpService wosBackUpService) {
		this.wosSearchClient = wosSearchClient;
		this.amrClient = amrClient;
		this.wosBackUpService = wosBackUpService;
	}

	public Response getDocumentsByCursor(String token, boolean retrieveCitation, boolean retrieveReferences, boolean useBackup) throws BusyException, ApiException, IOException {
		if (token != null && TokenService.get(token) != null) {
			return this.buildResponseForCursor(token, retrieveCitation, retrieveReferences, useBackup);
		} else {
			return ResponseService.notFoundResponse(null);
		}
	}

	/**
	 * @param ids list of id to get, format : [id1,id2,id3...]
	 * @return Response
	 */

	public Response getDocuments(List<String> ids, boolean retrieveCitation, boolean retrieveReferences, boolean useBackup) throws BusyException, ApiException, IOException, WosException {
		if (WosIdValidator.validWosIds(ids)) {

			//get data from wos
			WosResultBag resultBag = wosSearchClient.getByWOSUID(ids);

			return this.buildResponseForCursor(resultBag, null, retrieveCitation, retrieveReferences, useBackup);
		} else {
			return ResponseService.notFoundResponse(null);
		}
	}

	/**
	 * return document by search query
	 *
	 * @param query the string query
	 * @param year  the year filter
	 * @param month the month filter
	 * @return Response
	 */
	public Response getDocumentsBySearchQuery(@NotNull String query, Integer year, Integer month, boolean retrieveCitation, boolean retrieveReferences, boolean useBackup) throws BusyException, ApiException, IOException, WosException {
		if (query != null && year != null && month != null) {
			//get wos data for query, year and month
			WosResultBag resultBag = wosSearchClient.search(query, year, month);
			return this.buildResponseForCursor(resultBag, null, retrieveCitation, retrieveReferences, useBackup);
		} else if (query != null && year != null) {
			//get wos data for query and year
			WosResultBag resultBag = wosSearchClient.search(query, year, null);
			return this.buildResponseForCursor(resultBag, null, retrieveCitation, retrieveReferences, useBackup);
		} else if (query != null) {
			//get wos data for query
			WosResultBag resultBag = wosSearchClient.search(query);
			return this.buildResponseForCursor(resultBag, null, retrieveCitation, retrieveReferences, useBackup);
		} else {
			return ResponseService.notFoundResponse(null);
		}
	}


	public Response getAll() {
		return ResponseService.notSupportedResponse("this importer does not support this operation");
	}


	/**
	 * @param currentCursor the cursor to fetch
	 * @return Response
	 */
	private Response buildResponseForCursor(String currentCursor, boolean retrieveCitation, boolean retrieveReferences, boolean useBackup) throws BusyException, ApiException, IOException {

		//retrieve resultBag from cursor
		WosResultBag resultBag = (WosResultBag) TokenService.get(currentCursor).get("resultBag");

		//return result
		return this.buildResponseForCursor(resultBag, currentCursor, retrieveCitation, retrieveReferences, useBackup);
	}


	/**
	 * send next result from iterator to the client
	 *
	 * @param resultBag wos result bag to fetch
	 * @return Response
	 */
	private Response buildResponseForCursor(WosResultBag resultBag, String currentTokenKey, boolean retrieveCitation, boolean retrieveReferences, boolean useBackup) throws BusyException, ApiException, IOException {

		List<BaseDocument> responseData = new ArrayList();
		ImporterResponseMetadata responseMetadata = new ImporterResponseMetadata();

		//get current token
		Token currentToken = this.getCurrentToken(resultBag, currentTokenKey);
		int firstDocument = (Integer) currentToken.get("first");

		//get record
		List<Record> records = resultBag.getRecords(firstDocument, this.maxDocPerPage, retrieveCitation, retrieveReferences);
		if (resultBag.getMonth() != null) {
			records = this.filterRecordList(records, resultBag.getYear(), resultBag.getMonth());
		}

		//get amr
//		if (retrieveWosPage) {
//			amrClient.retrieveFilesToDownload(records);
//		}

		//convert record
		List<BaseDocument> documents = BaseDocumentConverter.convert(records, firstDocument, useBackup ? this.wosBackUpService : null);
		responseData.addAll(documents);

		//save xml
		if (useBackup) {
			try {
				this.wosBackUpService.saveXmlRecord(records);
			} catch (IOException e) {
				log.error("can not save wos record on disk", e);
			}
		}

		//get previous token key
		String previousTokenKey = (String) currentToken.get("previousTokenKey");

		//if have next page
		if (records.size() > 0 && (firstDocument + records.size()) <= resultBag.getRecordNumber()) {
			Token nextToken = this.getNextToken(resultBag, currentToken, records);
			String nextTokenKey = nextToken.getKey();
			responseMetadata.setNextCursor(nextTokenKey);
			responseMetadata.setStatus(ImporterStatusEnum.WORKING);
		} else {
			responseMetadata.setStatus(ImporterStatusEnum.FINISHED);
		}

		//set metadata
		responseMetadata.setCount((long) records.size());
		responseMetadata.setPreviousCursor(previousTokenKey);
		responseMetadata.setFirst(((Integer) currentToken.get("first")).longValue());

		if (resultBag.getMonth() == null) {
			responseMetadata.setTotalCount(resultBag.getRecordNumber().longValue());
		} else {
			responseMetadata.setTotalCount(-1L);
		}

		//return response
		return ResponseService.buildResponse(responseData, responseMetadata);
	}


	/**
	 * return the current token
	 *
	 * @param resultBag       wosResultBag
	 * @param currentTokenKey the key of the current token, can be null
	 */
	private Token getCurrentToken(WosResultBag resultBag, String currentTokenKey) {

		Token currentToken;

		//if there is no key, we create the token, it means that this token is the first
		if (currentTokenKey == null) {
			currentToken = new Token();
			currentToken.put("first", 1);
			currentToken.put("resultBag", resultBag);
			currentToken.put("id", resultBag.getQueryId());
			currentToken.put("previousTokenKey", null);
			TokenService.save(currentToken);

			//if the key exist
		} else {
			currentToken = TokenService.get(currentTokenKey);
		}

		return currentToken;
	}


	/**
	 * create next token, return the token to save
	 *
	 * @param resultBag    the current wosResultBag
	 * @param currentToken the current token
	 * @return the next token
	 */
	private Token createNextToken(WosResultBag resultBag, Token currentToken, List<Record> records) {
		Integer nextStartId = ((Integer) currentToken.get("first")) + records.size();

		Token nextToken = new Token();
		nextToken.put("first", nextStartId);
		nextToken.put("resultBag", resultBag);
		nextToken.put("id", resultBag.getQueryId());
		nextToken.put("previousTokenKey", currentToken.getKey());

		TokenService.save(nextToken);

		currentToken.put("nextTokenKey", nextToken.getKey());

		return nextToken;
	}

	/**
	 * return the next token
	 *
	 * @param resultBag
	 * @param currentToken
	 * @return
	 */
	private Token getNextToken(WosResultBag resultBag, Token currentToken, List<Record> records) {
		Token nextToken;
		if (currentToken.get("nextTokenKey") == null) {
			nextToken = this.createNextToken(resultBag, currentToken, records);
		} else {
			nextToken = TokenService.get((String) currentToken.get("nextTokenKey"));
		}

		TokenService.update();

		return nextToken;
	}

	/**
	 * apply month filter on the recordlist
	 *
	 * @param recordList
	 * @param month
	 * @return
	 */
	private List<Record> filterRecordList(List<Record> recordList, Integer year, Integer month) {
		//list to return
		List<Record> returnedRecordList = new ArrayList();

		//filter loop
		for (Record record : recordList) {

			//get month from record
			String recordMonth = record.getStaticData().getSummary().getPublicationInfo().getSortDate().substring(5, 7);
			String recordyear = record.getStaticData().getSummary().getPublicationInfo().getSortDate().substring(0, 4);

			recordMonth = recordMonth.replace(" ", "");
			Integer recorddate = Integer.parseInt(recordyear + recordMonth);

			//check month
			if (recorddate >= year * 100 + month) {
				returnedRecordList.add(record);
			}
		}
		return returnedRecordList;
	}

}
