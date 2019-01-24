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

import eu.ill.puma.wosimporter.domain.exception.ApiException;
import eu.ill.puma.wosimporter.domain.exception.BusyException;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.services.corecollection.exception.WosException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations={
		"classpath:/applicationContext-test.xml"
})
@TestExecutionListeners( {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class})
public class WosResultBagTest {

	@Autowired
	private WosSearchClient wosSearchClient;

	@Test
	public void testGetRecords() throws ApiException, BusyException, WosException {
		WosResultBag resultBag = wosSearchClient.search("TS=neutron acid ill diffraction");
		List<Record> recordList = resultBag.getRecords(1,5, true, true);

		Assert.assertNotNull(recordList);

		for(Record record : recordList){
			Assert.assertNotNull(record);

			Assert.assertNotNull(record.getAmrResponse());
			Assert.assertNotNull(record.getCitation());
			Assert.assertNotNull(record.getCitedReferences());
			Assert.assertNotNull(record.getDynamicData());
			Assert.assertNotNull(record.getStaticData());
			Assert.assertNotNull(record.getRawXml());
			Assert.assertNotNull(record.getWosId());
		}
	}
}
