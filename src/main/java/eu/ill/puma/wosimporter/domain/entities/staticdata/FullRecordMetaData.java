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
public class FullRecordMetaData {

	@XmlElementWrapper(name = "languages")
	@XmlElement(name = "language")
	private List<String> langages;

	@XmlElementWrapper(name = "normalized_languages")
	@XmlElement(name = "language")
	private List<String> normalizedLangages;

	@XmlElementWrapper(name = "normalized_doctypes")
	@XmlElement(name = "doctype")
	private List<String> normalizedDocType;

	@XmlElementWrapper(name = "addresses")
	@XmlElement(name = "address_name")
	private List<AddressName> address;

	@XmlElementWrapper(name = "reprint_addresses")
	@XmlElement(name = "address_name")
	private List<AddressName> rePrintAddress;

	@XmlElement(name = "category_info")
	private CategoryInfo categoryInfo;

	@XmlElementWrapper(name = "keywords")
	@XmlElement(name = "keyword")
	private List<String> keywords;

	@XmlElementWrapper(name = "abstracts")
	@XmlElement(name = "abstract")
	private List<Abstract> abstracts;

	public List<String> getLangages() {
		return langages;
	}

	public void setLangages(List<String> langages) {
		this.langages = langages;
	}

	public List<String> getNormalizedLangages() {
		return normalizedLangages;
	}

	public void setNormalizedLangages(List<String> normalizedLangages) {
		this.normalizedLangages = normalizedLangages;
	}

	public List<String> getNormalizedDocType() {
		return normalizedDocType;
	}

	public void setNormalizedDocType(List<String> normalizedDocType) {
		this.normalizedDocType = normalizedDocType;
	}

	public List<AddressName> getAddress() {
		return address;
	}

	public void setAddress(List<AddressName> address) {
		this.address = address;
	}

	public List<AddressName> getRePrintAddress() {
		return rePrintAddress;
	}

	public void setRePrintAddress(List<AddressName> rePrintAddress) {
		this.rePrintAddress = rePrintAddress;
	}

	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}

	public void setCategoryInfo(CategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<Abstract> getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(List<Abstract> abstracts) {
		this.abstracts = abstracts;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FullRecordMetaData that = (FullRecordMetaData) o;
		return Objects.equals(langages, that.langages) &&
				Objects.equals(normalizedLangages, that.normalizedLangages) &&
				Objects.equals(normalizedDocType, that.normalizedDocType) &&
				Objects.equals(address, that.address) &&
				Objects.equals(rePrintAddress, that.rePrintAddress) &&
				Objects.equals(categoryInfo, that.categoryInfo) &&
				Objects.equals(keywords, that.keywords) &&
				Objects.equals(abstracts, that.abstracts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(langages, normalizedLangages, normalizedDocType, address, rePrintAddress, categoryInfo, keywords, abstracts);
	}
}
