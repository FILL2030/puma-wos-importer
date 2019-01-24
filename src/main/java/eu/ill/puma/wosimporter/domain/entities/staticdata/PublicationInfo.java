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

import eu.ill.puma.wosimporter.domain.entities.adapter.MonthAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublicationInfo {

	@XmlAttribute(name = "coverdate")
	private String coverDate;

	@XmlAttribute(name = "has_abstract")
	private String hasAbstract;

	@XmlAttribute(name = "pubtype")
	private String pubType;

	@XmlAttribute(name = "pubyear")
	private Integer pubYear;

	@XmlAttribute(name = "pubmonth")
	@XmlJavaTypeAdapter(MonthAdapter.class)
	private Integer pubMonth;

	@XmlAttribute(name = "sortdate")
	private String sortDate;

	@XmlAttribute(name = "vol")
	private String volume;

	@XmlElement(name = "page")
	private List<Pagination> paginations;

	public String getCoverDate() {
		return coverDate;
	}

	public void setCoverDate(String coverDate) {
		this.coverDate = coverDate;
	}

	public String getHasAbstract() {
		return hasAbstract;
	}

	public void setHasAbstract(String hasAbstract) {
		this.hasAbstract = hasAbstract;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public Integer getPubYear() {
		return pubYear;
	}

	public void setPubYear(Integer pubYear) {
		this.pubYear = pubYear;
	}

	public Integer getPubMonth() {
		return pubMonth;
	}

	public void setPubMonth(Integer pubMonth) {
		this.pubMonth = pubMonth;
	}

	public String getSortDate() {
		return sortDate;
	}

	public void setSortDate(String sortDate) {
		this.sortDate = sortDate;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public List<Pagination> getPaginations() {
		return paginations;
	}

	public void setPaginations(List<Pagination> paginations) {
		this.paginations = paginations;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PublicationInfo that = (PublicationInfo) o;
		return Objects.equals(coverDate, that.coverDate) &&
				Objects.equals(hasAbstract, that.hasAbstract) &&
				Objects.equals(pubType, that.pubType) &&
				Objects.equals(pubYear, that.pubYear) &&
				Objects.equals(pubMonth, that.pubMonth) &&
				Objects.equals(sortDate, that.sortDate) &&
				Objects.equals(volume, that.volume) &&
				Objects.equals(paginations, that.paginations);
	}

	@Override
	public int hashCode() {
		return Objects.hash(coverDate, hasAbstract, pubType, pubYear, pubMonth, sortDate, volume, paginations);
	}
}
