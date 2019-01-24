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

import eu.ill.puma.wosimporter.domain.entities.adapter.PageNumberAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Pagination {

	@XmlAttribute(name = "begin")
	@XmlJavaTypeAdapter(PageNumberAdapter.class)
	private Integer begin;

	@XmlAttribute(name = "end")
	@XmlJavaTypeAdapter(PageNumberAdapter.class)
	private Integer end;

	@XmlAttribute(name = "page_count")
	@XmlJavaTypeAdapter(PageNumberAdapter.class)
	private Integer pageCount;

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pagination that = (Pagination) o;
		return Objects.equals(begin, that.begin) &&
				Objects.equals(end, that.end) &&
				Objects.equals(pageCount, that.pageCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(begin, end, pageCount);
	}
}
