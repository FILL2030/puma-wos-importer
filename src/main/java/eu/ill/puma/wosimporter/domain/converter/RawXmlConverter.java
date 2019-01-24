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

import com.migcomponents.migbase64.Base64;
import eu.ill.puma.wosimporter.domain.document.BaseDocument;
import eu.ill.puma.wosimporter.domain.document.entities.BaseFile;
import eu.ill.puma.wosimporter.domain.document.enumeration.BaseFileType;
import eu.ill.puma.wosimporter.domain.entities.Record;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RawXmlConverter {

	private static final Logger log = LoggerFactory.getLogger(RawXmlConverter.class);

	public static void convert(BaseDocument document, Record record) {
		//get raw xml
		String rawXml = record.getRawXml();

		//if not null
		if (rawXml != null) {
			//base64  encoding
			String base64 = Base64.encodeToString(rawXml.getBytes(), false);

			//create importer file object
			BaseFile importerFile = new BaseFile();
			importerFile.setData(base64);
			importerFile.setName("wos_record.xml");
			importerFile.setMimeType("application/xml");
			importerFile.setType(BaseFileType.OTHER);
			importerFile.setMd5(DigestUtils.md5Hex(rawXml));

			//add it
			document.getFiles().add(importerFile);
		}
	}
}
