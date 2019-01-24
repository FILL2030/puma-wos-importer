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
import eu.ill.puma.wosimporter.domain.document.enumeration.BaseDocumentType;
import eu.ill.puma.wosimporter.domain.document.enumeration.BaseDocumentVersionSubType;
import eu.ill.puma.wosimporter.domain.document.enumeration.MetadataConfidence;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.entities.dynamicdata.Identifier;
import eu.ill.puma.wosimporter.domain.entities.staticdata.Title;

public class RecordConverter {

	public static void convert(BaseDocument document, Record record) {
		//source ID
		document.setSourceId(record.getWosId());

		//DOI
		if (record.getDynamicData().getClusterRelated().getIdentifiers() != null) {
			for (Identifier identifier : record.getDynamicData().getClusterRelated().getIdentifiers()) {
				if (identifier.getType().equals("doi")) {
					BaseStringEntity doiEntity = new BaseStringEntity(identifier.getValue());
					doiEntity.setConfidence(MetadataConfidence.SURE);
					document.setDoi(doiEntity);
				}

				if (document.getDoi() == null && identifier.getType().equals("xref_doi")) {
					BaseStringEntity doiEntity = new BaseStringEntity(identifier.getValue());
					doiEntity.setConfidence(MetadataConfidence.SURE);
					document.setDoi(doiEntity);
				}
			}
		}

		//shortName
		document.setShortName(null);

		//documentType
		String docType = null;
		if (record.getStaticData().getSummary().getDocType() != null &&
				record.getStaticData().getSummary().getDocType().size() > 0) {
			docType = record.getStaticData().getSummary().getDocType().get(0);
		}

		//set document type
		if (docType != null) {
			switch (docType.toLowerCase()) {
				case "article":
					document.setType(BaseDocumentType.PUBLICATION);
					break;
				case "proceedings paper":
					document.setType(BaseDocumentType.PROCEEDING);
					break;
				case "meeting abstract":
					document.setType(BaseDocumentType.MEETING_ABSTRACT);
					break;
				case "review":
					document.setType(BaseDocumentType.REVIEW);
					break;
				case "letter":
					document.setType(BaseDocumentType.LETTER);
					break;
				default:
					document.setType(BaseDocumentType.UNKNOWN);
			}
		}

		//documentSubType
		document.setSubType(BaseDocumentVersionSubType.FINAL);

		//title
		if (record.getStaticData().getSummary().getTitles() != null) {
			for (Title title : record.getStaticData().getSummary().getTitles()) {
				if (title.getType().equals("item")) {
					BaseStringEntity titleEntity = new BaseStringEntity(title.getValue());
					titleEntity.setConfidence(MetadataConfidence.SURE);
					document.setTitle(titleEntity);
				}
			}
		}


		//abstract text
		if (record.getStaticData().getFullRecordMetaData().getAbstracts() != null &&
				record.getStaticData().getFullRecordMetaData().getAbstracts().size() > 0) {

			String abstractText = "";
			for (String paragraph : record.getStaticData().getFullRecordMetaData().getAbstracts().get(0).getText()) {
				abstractText += paragraph;
			}
			BaseStringEntity abstractEntity = new BaseStringEntity(abstractText);
			abstractEntity.setConfidence(MetadataConfidence.SURE);
			document.setAbstract(abstractEntity);
		}

		//release date input format : 2017-07-01
		if (record.getStaticData().getSummary().getPublicationInfo().getSortDate() != null) {
			String date = record.getStaticData().getSummary().getPublicationInfo().getSortDate();
			String year = date.substring(0, 4);
			String month = date.substring(5, 7);
			String day = date.substring(8, 10);

			BaseStringEntity dateEntity = new BaseStringEntity(year + "-" + month + "-" + day + "T00:00:00.000Z");
			dateEntity.setConfidence(MetadataConfidence.SURE);
			document.setReleaseDate(dateEntity);
		}
	}
}
