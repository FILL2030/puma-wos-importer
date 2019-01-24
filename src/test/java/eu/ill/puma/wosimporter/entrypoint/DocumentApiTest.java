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
package eu.ill.puma.wosimporter.entrypoint;

import eu.ill.puma.wosimporter.domain.document.BaseDocument;
import eu.ill.puma.wosimporter.domain.document.ImporterResponse;
import eu.ill.puma.wosimporter.domain.document.ImporterResponseMetadata;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(locations = {
		"classpath:/applicationContext-test.xml"
})
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class})
public class DocumentApiTest {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private TestRestTemplate restTemplate;

	@Value("${app.wosTestRecord}")
	private String testId;

	@Value("${app.maxDocPerPage}")
	private Integer maxDocPerPage;


	@Test
	public void getDocumentsByIds() throws Exception {
		Thread.sleep(2000);

		//get response
		ResponseEntity<ImporterResponse> response = restTemplate.getForEntity("/api/v1/documents?ids=[" + testId + "]", ImporterResponse.class);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());

		//get importer response metaData entity
		ImporterResponse importerResponse = response.getBody();

		//get sub entities
		Assert.assertNotNull(importerResponse.getData());
		Assert.assertNotNull(importerResponse.getMetadata());
		ImporterResponseMetadata importerResponseMetadata = importerResponse.getMetadata();
		List<BaseDocument> responseData = importerResponse.getData();

		//test metaDataSection
		Assert.assertNull(importerResponseMetadata.getPreviousCursor());
		Assert.assertNull(importerResponseMetadata.getNextCursor());
		Assert.assertNotNull(importerResponseMetadata.getCount());
		Assert.assertEquals(1l, importerResponseMetadata.getFirst().longValue());
		Assert.assertNotNull(importerResponseMetadata.getRequestDuration());
		Assert.assertNotNull(importerResponseMetadata.getStatus());
		Assert.assertEquals(1l, importerResponseMetadata.getTotalCount().longValue());

		//test response data section
		Assert.assertEquals(1, responseData.size());

		BaseDocument document = responseData.get(0);
		Assert.assertEquals(1l,document.getId().longValue());
		Assert.assertNotNull(document.getSourceId());
		Assert.assertNotNull(document.getType());
		Assert.assertNotNull(document.getSubType());
	}

	@Test
	public void getDocumentBySearch() throws Exception {
		Thread.sleep(2000);

		//get response
		ResponseEntity<ImporterResponse> response = restTemplate.getForEntity("/api/v1/documents?query=TS=neutron ILL DIFFRACTION&startYear=2009&startMonth=6", ImporterResponse.class);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());

		//get importer response metaData entity
		ImporterResponse importerResponse = response.getBody();

		//get sub entities
		Assert.assertNotNull(importerResponse.getData());
		Assert.assertNotNull(importerResponse.getMetadata());
		ImporterResponseMetadata importerResponseMetadata = importerResponse.getMetadata();
		List<BaseDocument> responseData = importerResponse.getData();

		//test metaDataSection
		Assert.assertNull(importerResponseMetadata.getPreviousCursor());
		Assert.assertNotNull(importerResponseMetadata.getNextCursor());
		Assert.assertNotNull(importerResponseMetadata.getCount());
		Assert.assertEquals(1l, importerResponseMetadata.getFirst().longValue());
		Assert.assertNotNull(importerResponseMetadata.getRequestDuration());
		Assert.assertNotNull(importerResponseMetadata.getStatus());
		Assert.assertEquals(-1l, importerResponseMetadata.getTotalCount().longValue());

		//test response data section
		Assert.assertEquals(maxDocPerPage.intValue(), responseData.size());

		BaseDocument document = responseData.get(0);
		Assert.assertEquals(1l,document.getId().longValue());
		Assert.assertNotNull(document.getSourceId());
		Assert.assertNotNull(document.getType());
		Assert.assertNotNull(document.getSubType());
	}

	@Test
	public void getDocumentsByCursor() throws Exception {
		Thread.sleep(2000);

		//get response
		ResponseEntity<ImporterResponse> response = restTemplate.getForEntity("/api/v1/documents?query=TS=neutron ILL acid", ImporterResponse.class);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());

		//get importer response metaData entity
		ImporterResponse importerResponse = response.getBody();
		ImporterResponseMetadata importerResponseMetadata = importerResponse.getMetadata();

		//test metaDataSection
		Assert.assertNotNull(importerResponseMetadata.getNextCursor());

		ResponseEntity<ImporterResponse> nextResponse = restTemplate.getForEntity("/api/v1/documents/cursor/" + importerResponseMetadata.getNextCursor(), ImporterResponse.class);

		Assert.assertNotNull(nextResponse);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
		Assert.assertEquals(maxDocPerPage.intValue(), response.getBody().getData().size());
		Assert.assertEquals(maxDocPerPage.intValue(), nextResponse.getBody().getData().size());
	}

	@Test
	public void getAllDocuments() throws Exception {
		Thread.sleep(2000);

		//get response
		ResponseEntity<ImporterResponse> response = restTemplate.getForEntity("/api/v1/documents?query=TS=neutron ILL acid diffraction", ImporterResponse.class);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());

		//get importer response entity
		ImporterResponse importerResponse = response.getBody();
		Assert.assertNotNull(importerResponse);

		//get sub entities
		Assert.assertNotNull(importerResponse.getData());
		Assert.assertNotNull(importerResponse.getMetadata());
		ImporterResponseMetadata importerResponseMetadata = importerResponse.getMetadata();
		List<BaseDocument> responseData = importerResponse.getData();

		//test metaDataSection
		Assert.assertNull(importerResponseMetadata.getPreviousCursor());
		Assert.assertNotNull(importerResponseMetadata.getNextCursor());
		Assert.assertNotNull(importerResponseMetadata.getCount());
		Assert.assertNotNull(importerResponseMetadata.getFirst());
		Assert.assertNotNull(importerResponseMetadata.getRequestDuration());
		Assert.assertNotNull(importerResponseMetadata.getStatus());
		Assert.assertNotNull(importerResponseMetadata.getTotalCount());

		//test response data section
		Assert.assertEquals(maxDocPerPage.intValue(), responseData.size());
		for (BaseDocument document : responseData) {
			Assert.assertNotNull(document.getId());
			Assert.assertNotNull(document.getSourceId());
			Assert.assertNotNull(document.getType());
			Assert.assertNotNull(document.getSubType());
		}
	}

}