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


import eu.ill.puma.wosimporter.domain.entities.staticdata.Contributor;
import eu.ill.puma.wosimporter.domain.entities.staticdata.FullRecordMetaData;
import eu.ill.puma.wosimporter.domain.entities.staticdata.Item;
import eu.ill.puma.wosimporter.domain.entities.staticdata.Summary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class StaticData {

	@XmlElement(name = "summary")
	private Summary summary;

	@XmlElement(name = "fullrecord_metadata")
	private FullRecordMetaData fullRecordMetaData;

	@XmlElementWrapper(name = "contributors")
	@XmlElement(name = "contributor")
	private List<Contributor> contributors;

	@XmlElement(name = "item")
	private Item items;

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public FullRecordMetaData getFullRecordMetaData() {
		return fullRecordMetaData;
	}

	public void setFullRecordMetaData(FullRecordMetaData fullRecordMetaData) {
		this.fullRecordMetaData = fullRecordMetaData;
	}

	public List<Contributor> getContributors() {
		return contributors;
	}

	public void setContributors(List<Contributor> contributors) {
		this.contributors = contributors;
	}

	public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StaticData that = (StaticData) o;
		return Objects.equals(summary, that.summary) &&
				Objects.equals(fullRecordMetaData, that.fullRecordMetaData) &&
				Objects.equals(contributors, that.contributors) &&
				Objects.equals(items, that.items);
	}

	@Override
	public int hashCode() {
		return Objects.hash(summary, fullRecordMetaData, contributors, items);
	}
}
