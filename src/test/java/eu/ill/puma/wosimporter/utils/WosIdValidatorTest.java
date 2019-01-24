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
package eu.ill.puma.wosimporter.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class WosIdValidatorTest {
	@Test
	public void testValidWosId() {
		Assert.assertTrue(WosIdValidator.validWosId("WOS:000302141500012"));
	}

	@Test
	public void testValidWosIds() {
		List<String> idList = Arrays.asList(    "WOS:000302141500012",
				"WOS:000322050200005",
				"WOS:000297000600049",
				"WOS:000306988700037",
				"WOS:000259780200038",
				"WOS:000261426500041",
				"WOS:000332348600036",
				"WOS:000280471700053",
				"WOS:000323593700037",
				"WOS:000357592100021",
				"WOS:000299631100021",
				"WOS:000336879700010",
				"WOS:000286897600128",
				"WOS:000315760100017",
				"WOS:000333948000042",
				"WOS:000286151400029",
				"WOS:000379267300004",
				"WOS:000281334800002",
				"WOS:000289396300011",
				"WOS:000316261400007",
				"WOS:000265030500039",
				"WOS:000266811200003",
				"WOS:000273410600027",
				"WOS:000270110000008",
				"WOS:000277743900005",
				"WOS:000386937300009",
				"WOS:000262665500004",
				"WOS:000280204900003",
				"WOS:000269252500041",
				"WOS:000386402100031",
				"WOS:000326300200041",
				"WOS:000327773900021");

		Assert.assertTrue(WosIdValidator.validWosId("WOS:000302141500012"));
	}
}
