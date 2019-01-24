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
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class WosBackUpService {

	private static final Logger log = LoggerFactory.getLogger(WosBackUpService.class);

	@Value("${app.wosBackUpDirectory}")
	private String wosBackUpDirectoryPath;

	/**
	 * check if the directory to save xml record have the correct permission
	 */
	@PostConstruct
	private void checkDirectory() {
		log.info("wos backup directory : " + wosBackUpDirectoryPath);
		File backUpDirectory = new File(wosBackUpDirectoryPath);

		if (wosBackUpDirectoryPath == null && (!backUpDirectory.isDirectory() && backUpDirectory.canRead() && backUpDirectory.canRead())) {
			throw new RuntimeException("wos back up directory not readable, check apps settings and permissions");
		}
	}

	/**
	 * Save the xml on the file system. To preserve good performance due to the large number of files, files are stored in sub folder based on the modulo 100 of their ID
	 *
	 * @param recordList the wos record list to save
	 * @throws IOException
	 */
	public void saveXmlRecord(List<Record> recordList) throws IOException {
		for (Record record : recordList) {
			this.saveXmlRecord(record);
		}
	}

	/**
	 * Save the xml on the file system. To preserve good performance due to the large number of files, files are stored in sub folder based on the modulo 100 of their ID
	 *
	 * @param record the wos record to save
	 * @throws IOException
	 */
	public void saveXmlRecord(Record record) throws IOException {
		this.saveXmlRecord(record.getRawXml(), record.getWosId());
	}

	/**
	 * Save the xml on the file system. To preserve good performance due to the large number of files, files are stored in sub folder based on the modulo 100 of their ID
	 *
	 * @param xmlRecord the xml record to save
	 * @param wosId     the id of the xml record
	 */
	public void saveXmlRecord(String xmlRecord, String wosId) throws IOException {
		//get modulo
		String modulo = wosId.substring(wosId.length() - 2);

		//get file
		String filePath = wosBackUpDirectoryPath + File.separator + modulo + File.separator + wosId + ".xml";
		File xmlFile = new File(filePath);

		//save file
		FileUtils.writeStringToFile(xmlFile, xmlRecord, false);
	}

	public String getRecordBackup(String wosId) throws IOException {
		//get modulo
		String modulo = wosId.substring(wosId.length() - 2);

		//get file
		String filePath = wosBackUpDirectoryPath + File.separator + modulo + File.separator + wosId + ".xml";
		File xmlFile = new File(filePath);

		if (xmlFile.exists()) {
			String xml = FileUtils.readFileToString(xmlFile);
			return xml;
		}

		return null;
	}
}
