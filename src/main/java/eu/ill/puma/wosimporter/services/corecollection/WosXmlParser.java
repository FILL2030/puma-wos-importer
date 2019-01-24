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

import com.thomsonreuters.wokmws.v3.woksearch.FullRecordData;
import com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.entities.Records;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class WosXmlParser {

    private static final Logger log = LoggerFactory.getLogger(WosXmlParser.class);

    /**
     * parse xml response and return a records ovjecct
     *
     * @param xml
     * @return
     */
    public static Records getRecordsForResult(String xml) {
        try {
            //string editing
            xml = xml.replace("xmlns=\"http://scientific.thomsonreuters.com/schema/wok5.4/public/FullRecord\"", "");
            StringReader xmlDataReader = new StringReader(xml);

            JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Records records = (Records) jaxbUnmarshaller.unmarshal(xmlDataReader);
            WosXmlParser.attachRawXml(records, xml);

            return records;
        } catch (Exception ex) {
            log.error("can not parse wos of science xml\n" + xml, ex);

            return null;
        }

    }

    /**
     * parse xml response and return a records ovjecct
     *
     * @param xml
     * @return
     */
    public static Record getRecordForResult(String xml) {
        try {
            //string editing
            StringReader xmlDataReader = new StringReader(xml);

            JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Record record = (Record) jaxbUnmarshaller.unmarshal(xmlDataReader);

            return record;
        } catch (Exception ex) {
            log.error("can not parse wos of science xml for single record\n" + xml, ex);

            return null;
        }

    }

    /**
     * parse xml response and return a records ovjecct
     *
     * @param results
     * @return
     */
    public static Records getRecordsForResult(FullRecordData results) {
        return getRecordsForResult(results.getRecords());
    }

    /**
     * parse xml response and return a records ovjecct
     *
     * @param results
     * @return
     */
    public static Records getRecordsForResult(FullRecordSearchResults results) {
        return getRecordsForResult(results.getRecords());
    }

    /**
     * attach raw xml to records
     *
     * @param records
     * @param xml
     */
    private static void attachRawXml(Records records, String xml) {
        List<String> xmlList = new ArrayList();

        //remove xmlns
        String rawXml = xml.replace("</records>", "");
        rawXml = rawXml.replace("<records >", "");
        rawXml = rawXml.replace("\n", "");

        //add each doc to list
        String[] XmlArray = rawXml.split("<REC");
        for (String xmlRecord : XmlArray) {
            if (xmlRecord.length() > 30) {
                xmlList.add("<REC" + xmlRecord);
            }
        }

        //save raw wml
        List<Record> recordsList = records.getRecords();
        for (int i = 0; i < recordsList.size(); i++) {
            recordsList.get(i).setRawXml(xmlList.get(i));
        }
    }
}
