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
package eu.ill.puma.wosimporter.services.amr;

import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.utils.ResourceLoader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Service
public class AmrClient {

	private static final Logger log = LoggerFactory.getLogger(AmrClient.class);

	@Value("${app.amr-user}")
	private String user;

	@Value("${app.amr-password}")
	private String password;

	private String queryTemplate;

	private boolean available = true;

	@PostConstruct
	private void init() {
		log.debug("wos amr user : " + user);
		log.debug("wos amr password : " + password);

		if (user == null || password == null) {
			log.error("no amr login and/or password defined, amr service unavailable");
			available = false;
		}

		try {
			queryTemplate = ResourceLoader.readString("wosAMRQuery.xml");
		} catch (UnsupportedEncodingException e) {
			log.error("can not read wos amr query template, service unavailable");
			queryTemplate = "";
			available = false;
		}
	}


	public void retrieveFilesToDownload(List<Record> records) {
		if (available) {
			for (Record record : records) {
				this.retrieveFilesToDownload(record);
			}
		}
	}

	public void retrieveFilesToDownload(Record record) {
		if (available) {
			log.debug("retrieve file to download for record : " + record.getWosId());
			HashMap<String, String> amrResponse = this.getByWosID(record.getWosId());
			record.setAmrResponse(amrResponse);
		} else {
			log.error("wos amr service unavailable");
		}
	}

	/**
	 * @param WosId
	 * @return
	 */
	public HashMap<String, String> getByWosID(String WosId) {
		if (available) {
			WosId = WosId.replace("WOS:", "");
			String response = null;

			try {
				String docRequest = queryTemplate.replace("%user%", user);
				docRequest = docRequest.replace("%password%", password);
				docRequest = docRequest.replace("%key%", "ut");
				docRequest = docRequest.replace("%value%", WosId);

				response = this.getAPIResponse(docRequest);
				return this.readResponse(response);
			} catch (IOException e) {
				log.error("amr cient IO error", e);
				return new HashMap<String, String>();
			}
		} else {
			log.error("wos amr service unavailable");
			return new HashMap<String, String>();
		}
	}

	/**
	 * @param doi
	 * @return
	 */
	public HashMap<String, String> getByDOI(String doi) {
		if (available) {
			String response = null;

			try {
				String docRequest = queryTemplate.replace("%user%", user);
				docRequest = docRequest.replace("%password%", password);
				docRequest = docRequest.replace("%key%", "doi");
				docRequest = docRequest.replace("%value%", doi);

				response = this.getAPIResponse(docRequest);
				return this.readResponse(response);
			} catch (IOException e) {
				log.error("amr cient IO error", e);
				return new HashMap<String, String>();
			}
		} else {
			log.error("wos amr service unavailable");
			return new HashMap<String, String>();
		}
	}

	/**
	 * @param docRequest
	 * @return
	 * @throws IOException
	 */
	private String getAPIResponse(String docRequest) throws IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().useSystemProperties().build();
//		if (proxyEnabled) {
//			HttpHost proxy = new HttpHost(proxyHost, proxyPort);
//			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
//			httpclient = HttpClients.custom()
//					.setRoutePlanner(routePlanner)
//					.build();
//		} else {
//			httpclient = HttpClients.createDefault();
//		}

		try {
			HttpPost httpPost = new HttpPost("https://ws.isiknowledge.com/cps/xrpc");
			httpPost.setEntity(new StringEntity(docRequest));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			String xml = EntityUtils.toString(response.getEntity());
			response.close();
			return xml;

		} finally {
			httpclient.close();
		}
	}

	/**
	 * @param amrResponse
	 * @return
	 */
	private HashMap<String, String> readResponse(String amrResponse) {
		HashMap hash = new HashMap<String, String>();
		if (amrResponse != null) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = null;

			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				return hash;
			}

			InputStream stream = new ByteArrayInputStream(amrResponse.getBytes(StandardCharsets.UTF_8));
			Document document = null;
			try {
				document = builder.parse(stream);
			} catch (org.xml.sax.SAXException e) {
				return hash;
			} catch (IOException e) {
				return hash;
			}
			document.getDocumentElement().normalize();

			// store response here
			// Get all the values
			NodeList nList = document.getElementsByTagName("val");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String value = eElement.getTextContent().trim();
					String key = eElement.getAttribute("name");
					// no results found will have message key.
					if (!key.equals("message")) {
						hash.put(key, value);
					}
				}
			}
		}
		return hash;
	}
}
