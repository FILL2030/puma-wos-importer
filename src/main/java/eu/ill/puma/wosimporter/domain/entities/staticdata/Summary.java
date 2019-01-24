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
package eu.ill.puma.wosimporter.domain.entities.staticdata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Summary {

	@XmlElement(name = "EWUID")
	private WOSInfo wosInfo;

	@XmlElement(name = "pub_info")
	private PublicationInfo publicationInfo;

	@XmlElementWrapper(name = "titles")
	@XmlElement(name = "title")
	private List<Title> titles;

	@XmlElementWrapper(name = "names")
	@XmlElement(name = "name")
	private List<Name> names;

	@XmlElementWrapper(name = "doctypes")
	@XmlElement(name = "doctype")
	private List<String> docType;

	@XmlElementWrapper(name = "publishers")
	@XmlElement(name = "publisher")
	private List<WOSPublisher> publishers;

	public WOSInfo getWosInfo() {
		return wosInfo;
	}

	public void setWosInfo(WOSInfo wosInfo) {
		this.wosInfo = wosInfo;
	}

	public PublicationInfo getPublicationInfo() {
		return publicationInfo;
	}

	public void setPublicationInfo(PublicationInfo publicationInfo) {
		this.publicationInfo = publicationInfo;
	}

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public List<Name> getNames() {
		return names;
	}

	public void setNames(List<Name> names) {
		this.names = names;
	}

	public List<String> getDocType() {
		return docType;
	}

	public void setDocType(List<String> docType) {
		this.docType = docType;
	}

	public List<WOSPublisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<WOSPublisher> publishers) {
		this.publishers = publishers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Summary summary = (Summary) o;
		return Objects.equals(wosInfo, summary.wosInfo) &&
				Objects.equals(publicationInfo, summary.publicationInfo) &&
				Objects.equals(titles, summary.titles) &&
				Objects.equals(names, summary.names) &&
				Objects.equals(docType, summary.docType) &&
				Objects.equals(publishers, summary.publishers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wosInfo, publicationInfo, titles, names, docType, publishers);
	}
}
