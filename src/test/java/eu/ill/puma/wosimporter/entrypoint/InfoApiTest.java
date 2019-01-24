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

import eu.ill.puma.wosimporter.domain.capabilities.Capabilities;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(locations = {
		"classpath:/applicationContext-test.xml"
})
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class})
public class InfoApiTest {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getInfo() throws Exception {
		ResponseEntity<Capabilities> response = restTemplate.getForEntity("/api/v1/info", Capabilities.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());

		Capabilities capabilities = response.getBody();

		Assert.assertNotNull(capabilities);
		Assert.assertNotNull(capabilities.getImporterName());
		Assert.assertNotNull(capabilities.getMetaDataAnalysisState());
		Assert.assertNotNull(capabilities.getSupportedOperations());
		Assert.assertNotNull(capabilities.getSupportedQueryFilter());
	}

}