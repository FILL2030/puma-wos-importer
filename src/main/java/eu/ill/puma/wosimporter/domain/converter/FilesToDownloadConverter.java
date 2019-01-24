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
import eu.ill.puma.wosimporter.domain.entities.Record;

public class FilesToDownloadConverter {
	public static void convert(BaseDocument document, Record record) {
		if (record.getAmrResponse() != null && record.getAmrResponse().get("sourceURL") != null) {
			BaseFileToDownload importerFileToDownload = new BaseFileToDownload();

			//set type from document type
			switch (document.getType()) {
				case PUBLICATION:
					importerFileToDownload.setType(BaseFileType.PUBLICATION);
					break;
				case PROCEEDING:
					importerFileToDownload.setType(BaseFileType.PROCEEDING);
					break;
				case MEETING_ABSTRACT:
					importerFileToDownload.setType(BaseFileType.MEETING_ABSTRACT);
					break;
				case REVIEW:
					importerFileToDownload.setType(BaseFileType.REVIEW);
					break;
				case LETTER:
					importerFileToDownload.setType(BaseFileType.LETTER);
					break;
				default:
					importerFileToDownload.setType(BaseFileType.UNKNOWN);
			}

			//set src url
			importerFileToDownload.setUrl(record.getAmrResponse().get("sourceURL"));

			//set url type
			importerFileToDownload.setUrlType(BaseUrlType.ARTICLE_PAGE);

			//add file to document
			document.getFilesToDownload().add(importerFileToDownload);
		}
	}
}