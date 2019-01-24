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
package eu.ill.puma.wosimporter.domain.entities;

import com.thomsonreuters.wokmws.v3.woksearch.CitedReference;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REC")
public class Record {

	@XmlElement(name = "UID")
	private String wosId;

	@XmlElement(name = "dynamic_data")
	private DynamicData dynamicData;

	@XmlElement(name = "static_data")
	private StaticData staticData;

	private String rawXml;

	private Set<CitedReference> citedReferences = new HashSet();

	private Set<String> citation = new HashSet();

	private Map<String, String> amrResponse = new HashMap();

	public String getWosId() {
		return wosId;
	}

	public void setWosId(String wosId) {
		this.wosId = wosId;
	}

	public DynamicData getDynamicData() {
		return dynamicData;
	}

	public void setDynamicData(DynamicData dynamicData) {
		this.dynamicData = dynamicData;
	}

	public StaticData getStaticData() {
		return staticData;
	}

	public void setStaticData(StaticData staticData) {
		this.staticData = staticData;
	}

	public String getRawXml() {
		return rawXml;
	}

	public void setRawXml(String rawXml) {
		this.rawXml = rawXml;
	}

	public Set<CitedReference> getCitedReferences() {
		return citedReferences;
	}

	public void setCitedReferences(Set<CitedReference> citedReferences) {
		this.citedReferences = citedReferences;
	}

	public Set<String> getCitation() {
		return citation;
	}

	public void setCitation(Set<String> citation) {
		this.citation = citation;
	}

	public Map<String, String> getAmrResponse() {
		return amrResponse;
	}

	public void setAmrResponse(Map<String, String> amrResponse) {
		this.amrResponse = amrResponse;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Record record = (Record) o;
		return Objects.equals(wosId, record.wosId) &&
				Objects.equals(dynamicData, record.dynamicData) &&
				Objects.equals(staticData, record.staticData) &&
				Objects.equals(rawXml, record.rawXml) &&
				Objects.equals(citedReferences, record.citedReferences) &&
				Objects.equals(citation, record.citation) &&
				Objects.equals(amrResponse, record.amrResponse);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wosId, dynamicData, staticData, rawXml, citedReferences, citation, amrResponse);
	}
}
