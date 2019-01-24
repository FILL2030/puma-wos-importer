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
package eu.ill.puma.wosimporter.domain.converter;

import eu.ill.puma.wosimporter.domain.document.BaseDocument;
import eu.ill.puma.wosimporter.domain.document.entities.BaseStringEntity;
import eu.ill.puma.wosimporter.domain.document.enumeration.MetadataConfidence;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.entities.staticdata.CategoryInfo;
import org.unbescape.html.HtmlEscape;

public class ResearchDomainConverter {
	public static void convert(BaseDocument document, Record record) {
		CategoryInfo categoryInfo = record.getStaticData().getFullRecordMetaData().getCategoryInfo();

		//category
		if (categoryInfo != null && categoryInfo.getCategory() != null) {
			for (String category : categoryInfo.getCategory()) {
				BaseStringEntity domainEntity = new BaseStringEntity(HtmlEscape.unescapeHtml(category));
				domainEntity.setConfidence(MetadataConfidence.SURE);
				document.getResearchDomains().add(domainEntity);
			}
		}

		//sub category
		if (categoryInfo != null && categoryInfo.getSubCategory() != null) {
			for (String subCategory : categoryInfo.getSubCategory()) {
				BaseStringEntity domainEntity = new BaseStringEntity(HtmlEscape.unescapeHtml(subCategory));
				domainEntity.setConfidence(MetadataConfidence.SURE);
				document.getResearchDomains().add(domainEntity);			}
		}

		//subject
		if (categoryInfo != null && categoryInfo.getSubject() != null) {
			for (String subject : categoryInfo.getSubject()) {
				BaseStringEntity domainEntity = new BaseStringEntity(HtmlEscape.unescapeHtml(subject));
				domainEntity.setConfidence(MetadataConfidence.SURE);
				document.getResearchDomains().add(domainEntity);			}
		}
	}
}
