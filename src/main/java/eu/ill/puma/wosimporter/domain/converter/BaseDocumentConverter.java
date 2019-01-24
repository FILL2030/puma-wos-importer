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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.ill.puma.wosimporter.domain.document.BaseDocument;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.services.corecollection.WosBackUpService;
import eu.ill.puma.wosimporter.services.corecollection.WosXmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseDocumentConverter {

	private static final Logger log = LoggerFactory.getLogger(BaseDocumentConverter.class);

	private static ObjectMapper mapper = new ObjectMapper();

	public static BaseDocument convert(Record WOSRecord, Integer Id) {
		BaseDocument document = new BaseDocument();
		document.setId(Id.longValue());

		RecordConverter.convert(document, WOSRecord);

		JournalConverter.convert(document, WOSRecord);

		PublisherConverter.convert(document, WOSRecord);

		PersonAddressConverter.convert(document, WOSRecord);

		KeywordConverter.convert(document, WOSRecord);

		ResearchDomainConverter.convert(document, WOSRecord);

		//RawXmlConverter.convert(document, WOSRecord);

		FilesToDownloadConverter.convert(document, WOSRecord);

		ReferencesConverter.convert(document, WOSRecord);

		CitationConverter.convert(document, WOSRecord);

		DoiDotOrgConverter.convert(document, WOSRecord);

		return document;
	}

	public static List<BaseDocument> convert(List<Record> records, Integer startId, WosBackUpService wosBackUpService){
		List<BaseDocument> BaseDocumentList = new ArrayList();
		Integer i = startId;

		for (Record record :  records){
			BaseDocument baseDocument = BaseDocumentConverter.convert(record, i);
			BaseDocumentList.add(baseDocument);

			// Get previous record if it exists to determine if original document has changed
			String backupXml = null;
			if (wosBackUpService != null) {
				try {
					backupXml = wosBackUpService.getRecordBackup(record.getWosId());

				} catch (IOException e) {
					log.error("Failed to read backup record", e);
				}
			}

			if (backupXml != null) {
				// Deserialize XML to Record object
				Record backupRecord = WosXmlParser.getRecordForResult(backupXml);

				if (backupRecord != null) {
					// Convert to BaseDocument
					BaseDocument backupBaseDocument = BaseDocumentConverter.convert(record, i);

					// Compare JSON strings of new and old BaseDocuments
					try {
						String baseDocumentJsonString = mapper.writeValueAsString(baseDocument);
						String backupBaseDocumentJsonString = mapper.writeValueAsString(backupBaseDocument);

						boolean isModified = !backupBaseDocumentJsonString.equals(baseDocumentJsonString);
						baseDocument.setModifiedAtSource(isModified);

					} catch (JsonProcessingException e) {
						log.error("Failed to compare backup document", e);
					}
				}
			}

			i++;
		}
		return BaseDocumentList;
	}
}
