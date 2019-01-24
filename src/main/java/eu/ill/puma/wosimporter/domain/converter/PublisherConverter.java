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
import eu.ill.puma.wosimporter.domain.document.entities.BasePublisher;
import eu.ill.puma.wosimporter.domain.document.enumeration.MetadataConfidence;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.entities.staticdata.Summary;
import eu.ill.puma.wosimporter.domain.entities.staticdata.WOSPublisher;

public class PublisherConverter {
	public static void convert(BaseDocument document, Record record) {
		Summary summary = record.getStaticData().getSummary();

		if (summary.getPublishers() != null) {
			for (WOSPublisher wospublisher : summary.getPublishers()) {

				BasePublisher publisher = new BasePublisher();

				publisher.setAddress(wospublisher.getPublisherAddress().getFullAddress());
				publisher.setConfidence(MetadataConfidence.SURE);

				publisher.setCity(wospublisher.getPublisherAddress().getCity());

				if (wospublisher.getPublisherName() != null && wospublisher.getPublisherName().size() > 0) {
					publisher.setName(wospublisher.getPublisherName().get(0).getFullName());
				}

				document.getPublishers().add(publisher);
			}
		}
	}
}
