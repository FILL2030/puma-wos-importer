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

import com.thomsonreuters.wokmws.v3.woksearch.CitedReference;
import eu.ill.puma.wosimporter.domain.document.BaseDocument;
import eu.ill.puma.wosimporter.domain.document.entities.BaseStringEntity;
import eu.ill.puma.wosimporter.domain.document.enumeration.MetadataConfidence;
import eu.ill.puma.wosimporter.domain.entities.Record;

public class ReferencesConverter {
	public static void convert(BaseDocument document, Record record) {
		StringBuilder referenceStringBuilder;

		for (CitedReference reference : record.getCitedReferences()) {
			if (reference.getCitedAuthor() != null &&
					reference.getCitedTitle() != null &&
					reference.getCitedWork() != null &&
					reference.getVolume() != null &&
					reference.getYear() != null &&
					reference.getPage() != null) {

				referenceStringBuilder = new StringBuilder();

				//author
				referenceStringBuilder.append(reference.getCitedAuthor());
				referenceStringBuilder.append(", ");

				//title
				referenceStringBuilder.append(reference.getCitedTitle());
				referenceStringBuilder.append(", ");

				//journal name
				referenceStringBuilder.append(reference.getCitedWork());
				referenceStringBuilder.append(" ");

				//journal volume
				referenceStringBuilder.append(reference.getVolume());
				referenceStringBuilder.append(" (");

				//date
				referenceStringBuilder.append(reference.getYear());
				referenceStringBuilder.append("), ");

				//page
				referenceStringBuilder.append(reference.getPage());

				BaseStringEntity referenceEntity = new BaseStringEntity(referenceStringBuilder.toString());
				referenceEntity.setConfidence(MetadataConfidence.SURE);
				document.getReferences().add(referenceEntity);
			}
		}
	}
}