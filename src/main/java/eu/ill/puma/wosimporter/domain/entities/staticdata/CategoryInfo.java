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
import java.util.Objects;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryInfo {

	@XmlElementWrapper(name = "headings")
	@XmlElement(name = "heading")
	private Set<String> category;

	@XmlElementWrapper(name = "subheadings")
	@XmlElement(name = "subheading")
	private Set<String> subCategory;

	@XmlElementWrapper(name = "subjects")
	@XmlElement(name = "subject")
	private Set<String> subject;

	public Set<String> getCategory() {
		return category;
	}

	public void setCategory(Set<String> category) {
		this.category = category;
	}

	public Set<String> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Set<String> subCategory) {
		this.subCategory = subCategory;
	}

	public Set<String> getSubject() {
		return subject;
	}

	public void setSubject(Set<String> subject) {
		this.subject = subject;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoryInfo that = (CategoryInfo) o;
		return Objects.equals(category, that.category) &&
				Objects.equals(subCategory, that.subCategory) &&
				Objects.equals(subject, that.subject);
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, subCategory, subject);
	}
}
