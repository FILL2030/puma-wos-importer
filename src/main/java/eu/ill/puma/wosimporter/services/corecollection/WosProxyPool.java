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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WosProxyPool {

	private static final Logger log = LoggerFactory.getLogger(WosProxyPool.class);

	private static final int WOS_MIN_REQUEST_TIME_BASE_MS = 1000;
	private static final int WOS_MAX_REQUEST_TIME_BASE_MS = 1500;
	private static final int WOS_MAX_REQUESTS_IN_TIME_BASE = 2;
	private static final int WOS_REQUEST_TIME_BASE_MIN_STEP_MS = 10;


	private static final int WOS_CONNECTION_TIME_BASE_MS = 5 * 60 * 1000;
	private static final int WOS_MAX_CONNECTIONS_IN_TIME_BASE = 5;

	private static final int MAX_NUMBER_OF_RETRIES = 3;

	private static final long REQUEST_TIME_UPDATE_DELAY_MS = 30 * 1000;
	private static final long REQUEST_TIME_UPDATE_MIN_REQUEST_COUNT = 30;

	@Value("${app.wos-user}")
	private String user;

	@Value("${app.wos-password}")
	private String password;

	@Value("${app.wos-maxNumberOfSearchConnections}")
	private int maxNumberOfSearchProxies;

	@Value("${app.wos-maxNumberOfUIDConnections}")
	private int maxNumberOfUIDProxies;

	private Deque<WosProxy> searchProxies = new ArrayDeque<WosProxy>();
	private Deque<WosProxy> uidProxies = new ArrayDeque<WosProxy>();

	private List<Long> lastCreationTimesMs = new ArrayList<>();
	private List<Long> lastRequestTimesMs = new ArrayList<>();

	private int wosRequestTimeBaseMs = WOS_MIN_REQUEST_TIME_BASE_MS;
	private int wosLastGoodRequestTimeBaseMs = WOS_MAX_REQUEST_TIME_BASE_MS;
	private int wosLastBadRequestTimeBaseMs = WOS_MIN_REQUEST_TIME_BASE_MS;
	private long requestCounterStartTime = 0;
	private long requestCounter = 0;
	private long failedRequestCounter = 0;
	private double failTolerance = 0.0;

	private List<Long> debugLastRequestTimes = Arrays.asList(0l, 0l);
	private int debugLastRequestTimeIndex = 0;

	@PostConstruct
	public void initProxies() {
		// Create a single proxy for each request type initially
		this.createProxy(this.searchProxies);
		this.createProxy(this.uidProxies);

		log.info("Created " + this.searchProxies.size() + " WoS proxies for search requests at init");
		log.info("Created " + this.uidProxies.size() + " WoS proxies for uid requests at init");
	}

	@PreDestroy
	public void closeProxies() {
		this.searchProxies.forEach(proxy -> proxy.closeSession());
		this.uidProxies.forEach(proxy -> proxy.closeSession());
		this.searchProxies.clear();
		this.uidProxies.clear();
	}

	public List<CitedReference> citedReferencesRetrieve(int proxyId, String queryId, RetrieveParameters retrieveParameters) throws WosException, BusyException {
		int callCount = 0;
		List<CitedReference> citedReferences = null;
		while (citedReferences == null && callCount < MAX_NUMBER_OF_RETRIES) {
			callCount++;
			WosProxy proxy = this.getProxyById(proxyId);
			try {
				this.waitForThrottleAndUpdate();

				citedReferences = proxy.citedReferencesRetrieve(queryId, retrieveParameters);

			} catch (WosException | BusyException e) {
				this.failedRequestCounter++;
				if (callCount == MAX_NUMBER_OF_RETRIES) {
					throw e;

				} else {
					log.warn("Retrying WoS request after error caught from proxy: " + e.getMessage());
				}

			} catch (WosProxyDeadException e) {
				log.info("WosProxy " + proxy.getId() + " has died: removing from pool");
				this.removeSearchProxy(proxy);
			}
		}

		return citedReferences;
	}

	public ProxyLinkedFullRecordSearchResults relatedRecords(String databaseId, String uid, List<EditionDesc> editions, TimeSpan timeSpan, String queryLanguage, RetrieveParameters retrieveParameters) throws WosException, BusyException {
		int callCount = 0;
		ProxyLinkedFullRecordSearchResults proxyLinkedSearchResults = null;
		while (proxyLinkedSearchResults == null && callCount < MAX_NUMBER_OF_RETRIES) {
			callCount++;
			WosProxy proxy = this.getUIDProxy();
			try {
				this.waitForThrottleAndUpdate();

				FullRecordSearchResults searchResults = proxy.relatedRecords(databaseId, uid, editions, timeSpan, queryLanguage, retrieveParameters);

				proxyLinkedSearchResults = new ProxyLinkedFullRecordSearchResults(searchResults, proxy.getId());

			} catch (WosException | BusyException e) {
				this.failedRequestCounter++;
				if (callCount == MAX_NUMBER_OF_RETRIES) {
					throw e;

				} else {
					log.warn("Retrying WoS request after error caught from proxy: " + e.getMessage());
				}

			} catch (WosProxyDeadException e) {
				log.info("WosProxy " + proxy.getId() + " has died: removing from pool");
				this.removeUIDProxy(proxy);
			}
		}

		return proxyLinkedSearchResults;
	}

	public CitedReferencesSearchResults citedReferences(String databaseId, String uid, String queryLanguage, RetrieveParameters retrieveParameters) throws WosException, BusyException {
		int callCount = 0;
		CitedReferencesSearchResults citedReferences = null;
		while (citedReferences == null && callCount < MAX_NUMBER_OF_RETRIES) {
			callCount++;
			WosProxy proxy = this.getUIDProxy();
			try {
				this.waitForThrottleAndUpdate();

				citedReferences = proxy.citedReferences(databaseId, uid, queryLanguage, retrieveParameters);

			} catch (WosException | BusyException e) {
				this.failedRequestCounter++;
				if (callCount == MAX_NUMBER_OF_RETRIES) {
					throw e;

				} else {
					log.warn("Retrying WoS request after error caught from proxy: " + e.getMessage());
				}

			} catch (WosProxyDeadException e) {
				log.info("WosProxy " + proxy.getId() + " has died: removing from pool");
				this.removeUIDProxy(proxy);
			}
		}

		return citedReferences;
	}

	public FullRecordData retrieve(int proxyId, String queryId, RetrieveParameters retrieveParameters) throws WosException, BusyException {
		int callCount = 0;
		FullRecordData recordData = null;
		while (recordData == null && callCount < MAX_NUMBER_OF_RETRIES) {
			callCount++;
			WosProxy proxy = this.getProxyById(proxyId);
			try {
				this.waitForThrottleAndUpdate();

				recordData = proxy.retrieve(queryId, retrieveParameters);

			} catch (WosException | BusyException e) {
				this.failedRequestCounter++;
				if (callCount == MAX_NUMBER_OF_RETRIES) {
					throw e;

				} else {
					log.warn("Retrying WoS request after error caught from proxy: " + e.getMessage());
				}

			} catch (WosProxyDeadException e) {
				log.info("WosProxy " + proxy.getId() + " has died: removing from pool");
				this.removeSearchProxy(proxy);
			}
		}

		return recordData;
	}

	public ProxyLinkedFullRecordSearchResults search(QueryParameters queryParameters, RetrieveParameters retrieveParameters) throws WosException, BusyException {
		int callCount = 0;
		ProxyLinkedFullRecordSearchResults proxyLinkedSearchResults = null;
		while (proxyLinkedSearchResults == null && callCount < MAX_NUMBER_OF_RETRIES) {
			callCount++;
			WosProxy proxy = this.getSearchProxy();
			try {
				this.waitForThrottleAndUpdate();

				FullRecordSearchResults searchResults = proxy.search(queryParameters, retrieveParameters);

				proxyLinkedSearchResults = new ProxyLinkedFullRecordSearchResults(searchResults, proxy.getId());

			} catch (WosException | BusyException e) {
				this.failedRequestCounter++;
				if (callCount == MAX_NUMBER_OF_RETRIES) {
					throw e;

				} else {
					log.warn("Retrying WoS request after error caught from proxy: " + e.getMessage());
				}

			} catch (WosProxyDeadException e) {
				log.info("WosProxy " + proxy.getId() + " has died: removing from pool");
				this.removeSearchProxy(proxy);
			}
		}

		return proxyLinkedSearchResults;
	}

	public ProxyLinkedFullRecordSearchResults citingArticles(String databaseId, String uid, List<EditionDesc> editions, TimeSpan timeSpan, String queryLanguage, RetrieveParameters retrieveParameters) throws WosException, BusyException {
		int callCount = 0;
		ProxyLinkedFullRecordSearchResults proxyLinkedSearchResults = null;
		while (proxyLinkedSearchResults == null && callCount < MAX_NUMBER_OF_RETRIES) {
			callCount++;
			WosProxy proxy = this.getUIDProxy();
			try {
				this.waitForThrottleAndUpdate();

				FullRecordSearchResults searchResults = proxy.citingArticles(databaseId, uid, editions, timeSpan, queryLanguage, retrieveParameters);

				proxyLinkedSearchResults = new ProxyLinkedFullRecordSearchResults(searchResults, proxy.getId());

			} catch (WosException | BusyException e) {
				this.failedRequestCounter++;
				if (callCount == MAX_NUMBER_OF_RETRIES) {
					throw e;

				} else {
					log.warn("Retrying WoS request after error caught from proxy: " + e.getMessage());
				}

			} catch (WosProxyDeadException e) {
				log.info("WosProxy " + proxy.getId() + " has died: removing from pool");
				this.removeUIDProxy(proxy);
			}
		}

		return proxyLinkedSearchResults;
	}

	public ProxyLinkedFullRecordSearchResults retrieveById(String databaseId, List<String> uid, String queryLanguage, RetrieveParameters retrieveParameters) throws WosException, BusyException {
		int callCount = 0;
		ProxyLinkedFullRecordSearchResults proxyLinkedSearchResults = null;
		while (proxyLinkedSearchResults == null && callCount < MAX_NUMBER_OF_RETRIES) {
			callCount++;
			WosProxy proxy = this.getUIDProxy();
			try {
				this.waitForThrottleAndUpdate();

				FullRecordSearchResults searchResults = proxy.retrieveById(databaseId, uid, queryLanguage, retrieveParameters);

				proxyLinkedSearchResults = new ProxyLinkedFullRecordSearchResults(searchResults, proxy.getId());

			} catch (WosException | BusyException e) {
				this.failedRequestCounter++;
				if (callCount == MAX_NUMBER_OF_RETRIES) {
					throw e;

				} else {
					log.warn("Retrying WoS request after error caught from proxy: " + e.getMessage());
				}

			} catch (WosProxyDeadException e) {
				log.info("WosProxy " + proxy.getId() + " has died: removing from pool");
				this.removeUIDProxy(proxy);
			}
		}

		return proxyLinkedSearchResults;
	}

	private synchronized WosProxy getSearchProxy() throws WosException {
		return this.getProxy(this.searchProxies, this.maxNumberOfSearchProxies, "search");
	}

	private synchronized WosProxy getUIDProxy() throws WosException {
		return this.getProxy(this.uidProxies, this.maxNumberOfUIDProxies, "uid");
	}

	private synchronized WosProxy getProxy(Deque<WosProxy> proxiesContainer, int maxNumberOfProxies, String type) throws WosException {
		// Create by default a new proxy
		if (proxiesContainer.size() < maxNumberOfProxies) {
			this.createProxy(proxiesContainer);
		}

		// Get from start of the deque
		WosProxy proxy = proxiesContainer.pollFirst();

		if (proxy == null) {
			log.error("No proxies are available for " + type + " request");
			throw new WosException("Unable to get proxy for " + type + " request");
		}

		// Put to the back of the deque
		proxiesContainer.offerLast(proxy);

		return proxy;
	}

	private synchronized WosProxy getProxyById(int proxyId) throws WosException {
		Optional<WosProxy> proxyOptional;

		proxyOptional = this.searchProxies.stream()
				.filter(wosProxy -> wosProxy.getId() == proxyId)
				.findFirst();

		if (!proxyOptional.isPresent()) {
			proxyOptional = this.uidProxies.stream()
					.filter(wosProxy -> wosProxy.getId() == proxyId)
					.findFirst();
		}

		if (!proxyOptional.isPresent()) {
			throw new WosException("Couldn't find proxy for query Id " + proxyId);
		}

		WosProxy proxy = proxyOptional.get();

		// Move proxy to the back of the queue
		this.searchProxies.remove(proxy);
		this.searchProxies.offerLast(proxy);

		return proxy;
	}

	private synchronized void removeSearchProxy(WosProxy proxy) {
		this.searchProxies.remove(proxy);
	}

	private synchronized void removeUIDProxy(WosProxy proxy) {
		this.uidProxies.remove(proxy);
	}

	private synchronized boolean createProxy(Deque<WosProxy> proxyContainer) {
		if (this.canCreateProxy()) {

			WosProxy proxy = new WosProxy(this.user, this.password);
			try {
				// Open session
				proxy.openSession();

				// Update session creation times
				this.lastCreationTimesMs.add(System.currentTimeMillis());

				// Add to list of proxies
				proxyContainer.addFirst(proxy);

				log.info("Created new WosProxy with Id " + proxy.getId());

				return true;

			} catch (WosException e) {
				log.error("Unable to open session to WosProxy: " + e.getMessage());
			}

		} else {
			log.info("Cannot create new WosProxy due to Wos sessions restrictions");
		}

		return false;
	}

	private synchronized boolean canCreateProxy() {
		// Verify that we do not create too many sessions in a specific time limit
		Long currentTime = System.currentTimeMillis();
		this.lastCreationTimesMs = this.lastCreationTimesMs.stream().filter(timeMs -> currentTime - timeMs < WOS_CONNECTION_TIME_BASE_MS).collect(Collectors.toList());

		return this.lastCreationTimesMs.size() < WOS_MAX_CONNECTIONS_IN_TIME_BASE;
	}

	private synchronized void waitForThrottleAndUpdate() {
		long currentTimeMs = System.currentTimeMillis();
//		int wosRequestTimeBaseMs = WOS_MIN_REQUEST_TIME_BASE_MS;
		int wosRequestTimeBaseMs = this.wosRequestTimeBaseMs;

		List<Long> newRequestTimesMs = new ArrayList<>();
		for (Long lastRequestTimeMs : this.lastRequestTimesMs) {
			if (currentTimeMs - lastRequestTimeMs < wosRequestTimeBaseMs) {
				newRequestTimesMs.add(lastRequestTimeMs);
			}
		}

		this.lastRequestTimesMs = newRequestTimesMs;

		if (this.lastRequestTimesMs.size() >= WOS_MAX_REQUESTS_IN_TIME_BASE) {
			// Get the older request time and wait for required time
			long timeSinceOldestRequestMs = currentTimeMs - this.lastRequestTimesMs.get(0);
			log.info("Sleeping " + (wosRequestTimeBaseMs - timeSinceOldestRequestMs) + "ms before next request to wos");
			try {
				Thread.sleep(wosRequestTimeBaseMs - timeSinceOldestRequestMs);

			} catch (InterruptedException e) {
				log.error("Got an error in throttle sleep : " + e.getMessage());
			}
		} else {
			log.info("Number of last request times (" + this.lastRequestTimesMs.size() + ") below limit (" + WOS_MAX_REQUESTS_IN_TIME_BASE + ")");
		}

		long currentTimeForUpdateMs = System.currentTimeMillis();
		this.lastRequestTimesMs.add(currentTimeForUpdateMs);

		this.updateRequestCount();

		currentTimeForUpdateMs = System.currentTimeMillis();
		long timeSinceOldestRequestMs = currentTimeForUpdateMs - this.debugLastRequestTimes.get(this.debugLastRequestTimeIndex);
		this.debugLastRequestTimes.set(this.debugLastRequestTimeIndex, currentTimeForUpdateMs);
		this.debugLastRequestTimeIndex = 1 - this.debugLastRequestTimeIndex;

		log.info("Time since 2 requests previously = " + timeSinceOldestRequestMs);
	}

	private synchronized void updateRequestCount() {
		if (this.requestCounterStartTime == 0) {
			this.requestCounterStartTime = System.currentTimeMillis();
		}
		this.requestCounter++;

		this.updateWosRequestTimeBase();
	}

	private synchronized void updateWosRequestTimeBase() {
		if (System.currentTimeMillis() - this.requestCounterStartTime > REQUEST_TIME_UPDATE_DELAY_MS && this.requestCounter >= REQUEST_TIME_UPDATE_MIN_REQUEST_COUNT) {
			double failRate = 1.0 * this.failedRequestCounter / this.requestCounter;

			int newWosRequestTimeBaseMs = 0;
			if (failRate > this.failTolerance) {
				newWosRequestTimeBaseMs = (int)(0.5 * (this.wosRequestTimeBaseMs + this.wosLastGoodRequestTimeBaseMs));
				this.wosLastBadRequestTimeBaseMs = this.wosRequestTimeBaseMs;

				if (newWosRequestTimeBaseMs - this.wosRequestTimeBaseMs < WOS_REQUEST_TIME_BASE_MIN_STEP_MS) {
					newWosRequestTimeBaseMs = this.wosRequestTimeBaseMs + WOS_REQUEST_TIME_BASE_MIN_STEP_MS;
				}

			} else {
				newWosRequestTimeBaseMs = (int)(0.5 * (this.wosRequestTimeBaseMs + this.wosLastBadRequestTimeBaseMs));
				this.wosLastGoodRequestTimeBaseMs = this.wosRequestTimeBaseMs;

				if (this.wosRequestTimeBaseMs - newWosRequestTimeBaseMs < WOS_REQUEST_TIME_BASE_MIN_STEP_MS) {
					newWosRequestTimeBaseMs = this.wosRequestTimeBaseMs - WOS_REQUEST_TIME_BASE_MIN_STEP_MS;
				}
			}

			newWosRequestTimeBaseMs = Math.max(newWosRequestTimeBaseMs, WOS_MIN_REQUEST_TIME_BASE_MS);
			newWosRequestTimeBaseMs = Math.min(newWosRequestTimeBaseMs, WOS_MAX_REQUEST_TIME_BASE_MS);

			if (this.wosRequestTimeBaseMs != newWosRequestTimeBaseMs) {
				log.info("Fail rate for " + this.requestCounter + " requests = " + failRate + ": Changing wos request time base from " + this.wosRequestTimeBaseMs + " to " + newWosRequestTimeBaseMs);
				this.wosRequestTimeBaseMs = newWosRequestTimeBaseMs;
			}

			this.requestCounterStartTime = System.currentTimeMillis();
			this.requestCounter = 0;
			this.failedRequestCounter = 0;
		}
	}
}
