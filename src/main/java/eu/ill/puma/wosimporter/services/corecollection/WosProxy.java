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
import eu.ill.puma.wosimporter.services.corecollection.exception.WosProxyDeadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WosProxy {

	private static final Logger log = LoggerFactory.getLogger(WosProxy.class);

	private static int proxyCounter = 0;

	private int id;
	private WosAuthClient authClient;
	private WokSearch port;

	private int numberOfIDRequests = 0;
	private int maxNumberOfIdRequests = 1000 + (int)(Math.random() * 1000);

	private static synchronized int getNextId() {
		proxyCounter++;

		return proxyCounter;
	}

	public WosProxy(String user, String password) {
		this.id = getNextId();
		this.authClient = new WosAuthClient(user, password);
	}

	public int getId() {
		return id;
	}

	public void openSession() throws WosException {
		this.authClient.openSession();
		this.port = this.authClient.getPort();
	}

	public void closeSession() {
		this.port = null;
		this.authClient.closeSession();
	}

	public synchronized List<CitedReference> citedReferencesRetrieve(String queryId, RetrieveParameters retrieveParameters) throws WosException, WosProxyDeadException, BusyException {
		List<CitedReference> citedReferences = null;

		try {
			citedReferences = port.citedReferencesRetrieve(queryId, retrieveParameters);

		} catch (SessionException_Exception sessionError) {
			log.error("[" + this.getId() + "] WosProxy.citedReferencesRetrieve : wos session error (expired or invalid), trying to renew the session identifier: " + sessionError.getMessage());

			throw new WosProxyDeadException();

		} catch (AuthenticationException_Exception authError) {
			log.error("[" + this.getId() + "] WosProxy.citedReferencesRetrieve : wos authentication error: " + authError.getMessage());
			if (authError.getMessage().contains("Request denied by Throttle server")) {
				throw new BusyException(authError.getMessage(), authError);
			}

			throw new WosException("Unable to get full citing articles: " + authError.getMessage(), authError);

		} catch (Exception e) {
			log.error("[" + this.getId() + "] WosProxy.citedReferencesRetrieve : + Received error during references request to WoS : " + e.getMessage());

			throw new WosException("Unable to get cited references: " + e.getMessage(), e);
		}

		return citedReferences;
	}

	public synchronized FullRecordSearchResults relatedRecords(String databaseId, String uid, List<EditionDesc> editions, TimeSpan timeSpan, String queryLanguage, RetrieveParameters retrieveParameters) throws WosException, WosProxyDeadException, BusyException {
		FullRecordSearchResults relatedRecords = null;

		// Update number of ID requests
		this.updateNumberOfIDRequests();

		try {
			relatedRecords = port.relatedRecords(databaseId, uid, editions, timeSpan, queryLanguage, retrieveParameters);

		} catch (SessionException_Exception sessionError) {
			log.error("[" + this.getId() + "] WosProxy.relatedRecords : wos session error (expired or invalid), trying to renew the session identifier: " + sessionError.getMessage());

			throw new WosProxyDeadException();

		} catch (AuthenticationException_Exception authError) {
			log.error("[" + this.getId() + "] WosProxy.relatedRecords : wos authentication error: " + authError.getMessage());
			if (authError.getMessage().contains("Request denied by Throttle server")) {
				throw new BusyException(authError.getMessage(), authError);
			}

			throw new WosException("Unable to get full citing articles: " + authError.getMessage(), authError);

		} catch (Exception e) {
			log.error("[" + this.getId() + "] WosProxy.relatedRecords : Received error during related records request to WoS : " + e.getMessage());

			throw new WosException("Unable to get related records: " + e.getMessage(), e);
		}

		return relatedRecords;
	}

	public synchronized CitedReferencesSearchResults citedReferences(String databaseId, String uid, String queryLanguage, RetrieveParameters retrieveParameters) throws WosException, WosProxyDeadException, BusyException {
		CitedReferencesSearchResults searchResults = null;

		// Update number of ID requests
		this.updateNumberOfIDRequests();

		try {
			searchResults = port.citedReferences(databaseId, uid, queryLanguage, retrieveParameters);

		} catch (SessionException_Exception sessionError) {
			log.error("[" + this.getId() + "] WosProxy.citedReferences : wos session error (expired or invalid), trying to renew the session identifier: " + sessionError.getMessage());

			throw new WosProxyDeadException();

		} catch (AuthenticationException_Exception authError) {
			log.error("[" + this.getId() + "] WosProxy.citedReferences : wos authentication error: " + authError.getMessage());
			if (authError.getMessage().contains("Request denied by Throttle server")) {
				throw new BusyException(authError.getMessage(), authError);
			}

			throw new WosException("Unable to get full citing articles: " + authError.getMessage(), authError);

		} catch (Exception e) {
			log.error("[" + this.getId() + "] WosProxy.citedReferences : Received error during references request to WoS : " + e.getMessage());

			throw new WosException("Unable to get cited references: " + e.getMessage(), e);
		}

		return searchResults;
	}

	public synchronized FullRecordData retrieve(String queryId, RetrieveParameters retrieveParameters) throws WosException, WosProxyDeadException, BusyException {
		FullRecordData fullRecordData = null;

		try {
			fullRecordData = port.retrieve(queryId, retrieveParameters);

		} catch (SessionException_Exception sessionError) {
			log.error("[" + this.getId() + "] WosProxy.retrieve : wos session error (expired or invalid), trying to renew the session identifier: " + sessionError.getMessage());

			throw new WosProxyDeadException();

		} catch (AuthenticationException_Exception authError) {
			log.error("[" + this.getId() + "] WosProxy.retrieve : wos authentication error: " + authError.getMessage());
			if (authError.getMessage().contains("Request denied by Throttle server")) {
				throw new BusyException(authError.getMessage(), authError);
			}

			throw new WosException("Unable to get full citing articles: " + authError.getMessage(), authError);

		} catch (Exception e) {
			log.error("[" + this.getId() + "] WosProxy.retrieve : Received error during retrieve request to WoS : " + e.getMessage());
			throw new WosException("Unable to get full record: " + e.getMessage(), e);
		}

		return fullRecordData;
	}

	public synchronized FullRecordSearchResults search(QueryParameters queryParameters, RetrieveParameters retrieveParameters) throws WosException, WosProxyDeadException, BusyException {
		FullRecordSearchResults fullRecordSearchResults = null;

		try {
			fullRecordSearchResults = port.search(queryParameters, retrieveParameters);

		} catch (SessionException_Exception sessionError) {
			log.error("[" + this.getId() + "] WosProxy.search : wos session error (expired or invalid), trying to renew the session identifier: " + sessionError.getMessage());

			throw new WosProxyDeadException();

		} catch (AuthenticationException_Exception authError) {
			log.error("[" + this.getId() + "] WosProxy.search : wos authentication error: " + authError.getMessage());
			if (authError.getMessage().contains("Request denied by Throttle server")) {
				throw new BusyException(authError.getMessage(), authError);
			}

			throw new WosException("Unable to get full citing articles: " + authError.getMessage(), authError);

		} catch (Exception e) {
			log.error("[" + this.getId() + "] WosProxy.search : Received error during full record search request to WoS : " + e.getMessage());
			throw new WosException("Unable to get full record search results: " + e.getMessage(), e);
		}

		return fullRecordSearchResults;
	}

	public synchronized FullRecordSearchResults citingArticles(String databaseId, String uid, List<EditionDesc> editions, TimeSpan timeSpan, String queryLanguage, RetrieveParameters retrieveParameters) throws WosException, WosProxyDeadException, BusyException {
		FullRecordSearchResults fullRecordSearchResults = null;

		// Update number of ID requests
		this.updateNumberOfIDRequests();

		try {
			fullRecordSearchResults = port.citingArticles(databaseId, uid, editions, timeSpan, queryLanguage, retrieveParameters);

		} catch (SessionException_Exception sessionError) {
			log.error("[" + this.getId() + "] WosProxy.citingArticles : wos session error (expired or invalid), trying to renew the session identifier: " + sessionError.getMessage());

			throw new WosProxyDeadException();

		} catch (AuthenticationException_Exception authError) {
			log.error("[" + this.getId() + "] WosProxy.citingArticles : wos authentication error: " + authError.getMessage());
			if (authError.getMessage().contains("Request denied by Throttle server")) {
				throw new BusyException(authError.getMessage(), authError);
			}

			throw new WosException("Unable to get full citing articles: " + authError.getMessage(), authError);

		} catch (Exception e) {
			log.error("[" + this.getId() + "] WosProxy.citingArticles : Received error during citing articles request to WoS : " + e.getMessage());
			throw new WosException("Unable to get full citing articles: " + e.getMessage(), e);
		}

		return fullRecordSearchResults;
	}

	public synchronized FullRecordSearchResults retrieveById(String databaseId, List<String> uid, String queryLanguage, RetrieveParameters retrieveParameters) throws WosException, WosProxyDeadException, BusyException {
		FullRecordSearchResults fullRecordSearchResults = null;

		// Update number of ID requests
		this.updateNumberOfIDRequests();

		try {
			fullRecordSearchResults = port.retrieveById(databaseId, uid, queryLanguage, retrieveParameters);

		} catch (SessionException_Exception sessionError) {
			log.error("[" + this.getId() + "] WosProxy.retrieveById : wos session error (expired or invalid), trying to renew the session identifier: " + sessionError.getMessage());

			throw new WosProxyDeadException();

		} catch (AuthenticationException_Exception authError) {
			log.error("[" + this.getId() + "] WosProxy.retrieveById : wos authentication error: " + authError.getMessage());
			if (authError.getMessage().contains("Request denied by Throttle server")) {
				throw new BusyException(authError.getMessage(), authError);
			}

			throw new WosException("Unable to get full citing articles: " + authError.getMessage(), authError);

		} catch (Exception e) {
			log.error("[" + this.getId() + "] WosProxy.retrieveById : Received error during retrieve by Id request to WoS : " + e.getMessage());
			throw new WosException("Unable to retrieve by Id: " + e.getMessage(), e);
		}

		return fullRecordSearchResults;
	}

	private void updateNumberOfIDRequests() throws WosProxyDeadException {
		this.numberOfIDRequests++;
		if (this.numberOfIDRequests >= maxNumberOfIdRequests) {

			throw new WosProxyDeadException();
		}
	}
}
