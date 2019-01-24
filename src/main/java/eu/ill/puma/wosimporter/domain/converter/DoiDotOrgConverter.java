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
import eu.ill.puma.wosimporter.domain.document.entities.BaseFileToDownload;
import eu.ill.puma.wosimporter.domain.document.enumeration.BaseFileType;
import eu.ill.puma.wosimporter.domain.document.enumeration.BaseUrlType;
import eu.ill.puma.wosimporter.domain.document.enumeration.MetadataConfidence;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.entities.dynamicdata.Identifier;

public class DoiDotOrgConverter {
	public static void convert(BaseDocument document, Record record) {
		BaseFileToDownload doiFileToDownload = new BaseFileToDownload();

		//set type from document type
		switch (document.getType()) {
			case PUBLICATION:
				doiFileToDownload.setType(BaseFileType.PUBLICATION);
				break;
			case PROCEEDING:
				doiFileToDownload.setType(BaseFileType.PROCEEDING);
				break;
			case MEETING_ABSTRACT:
				doiFileToDownload.setType(BaseFileType.MEETING_ABSTRACT);
				break;
			case REVIEW:
				doiFileToDownload.setType(BaseFileType.REVIEW);
				break;
			case LETTER:
				doiFileToDownload.setType(BaseFileType.LETTER);
				break;
			default:
				doiFileToDownload.setType(BaseFileType.UNKNOWN);
		}

		//set src url
		for (Identifier identifier : record.getDynamicData().getClusterRelated().getIdentifiers()) {
			if (identifier.getType().equals("doi")) {
				doiFileToDownload.setUrl("https://doi.org/" + identifier.getValue());
				doiFileToDownload.setUrlType(BaseUrlType.ARTICLE_PAGE);
				doiFileToDownload.setConfidence(MetadataConfidence.SURE);
				document.getFilesToDownload().add(doiFileToDownload);
			}

			if (doiFileToDownload.getUrl() == null && identifier.getType().equals("xref_doi")) {
				doiFileToDownload.setUrl("https://doi.org/" + identifier.getValue());
				doiFileToDownload.setUrlType(BaseUrlType.ARTICLE_PAGE);
				doiFileToDownload.setConfidence(MetadataConfidence.SURE);
				document.getFilesToDownload().add(doiFileToDownload);
			}
		}
	}
}
