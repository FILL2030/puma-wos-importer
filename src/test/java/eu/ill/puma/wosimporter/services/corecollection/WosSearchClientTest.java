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

import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.exception.ApiException;
import eu.ill.puma.wosimporter.domain.exception.BusyException;
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

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/applicationContext-test.xml"
})
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class})
public class WosSearchClientTest {

	@Autowired
	private WosSearchClient wosSearchClient;

	@Test
	public void testSearch() throws WosException, BusyException {
		WosResultBag resultBag = wosSearchClient.search("TS=neutron");

		Assert.assertNotNull(resultBag);
		Assert.assertNotNull(resultBag.getQueryId());
		Assert.assertTrue(resultBag.getRecordNumber() > 10000);
	}

	@Test
	public void testSearchWithFilter() throws WosException, ApiException, BusyException {
		WosResultBag resultBag = wosSearchClient.search("TS=neutron ILL DIFFRACTION", 2009, 6);

		Assert.assertNotNull(resultBag);
		Assert.assertNotNull(resultBag.getQueryId());
		Assert.assertTrue(62 < resultBag.getRecordNumber());

		List<Record> recordList = resultBag.getRecords(1, 11, false, false);

		for (Record record : recordList) {
			String date = record.getStaticData().getSummary().getPublicationInfo().getSortDate();
			String year = date.substring(0, 4);
			String month = date.substring(5, 7);
			Integer monthInt = Integer.parseInt(month);

			Assert.assertTrue(Integer.parseInt(year) >= 2009);
		}
	}

	@Test
	public void testSearchWithId() throws WosException, ApiException, BusyException {
		WosResultBag resultBag = wosSearchClient.getByWOSUID("WOS:000243341200007");

		Assert.assertNotNull(resultBag);
		Assert.assertNotNull(resultBag.getQueryId());
		Assert.assertEquals(1, resultBag.getRecordNumber().intValue());

	}

	@Test
	public void testGetByWOSUID() throws WosException, BusyException {
		WosResultBag resultBag = wosSearchClient.getByWOSUID("WOS:000262665500004");

		Assert.assertNotNull(resultBag);
		Assert.assertNotNull(resultBag.getQueryId());
		Assert.assertTrue(resultBag.getRecordNumber() == 1);
	}

	@Test
	public void testGetByWOSUIDs() throws WosException, BusyException {
		WosResultBag resultBag = wosSearchClient.getByWOSUID(Arrays.asList("WOS:000286151400029", "WOS:000326300200041", "WOS:000322050200005"));

		Assert.assertNotNull(resultBag);
		Assert.assertNotNull(resultBag.getQueryId());
		Assert.assertTrue(resultBag.getRecordNumber() == 3);
	}


}
