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

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

	@XmlElement(name = "ids")
	private String ids;

	@XmlElement(name = "bib_id")
	private String bidId;

	@XmlElement(name = "bib_pagecount")
	private Integer bidPageCount;

	@XmlElementWrapper(name = "keywords_plus")
	@XmlElement(name = "keyword")
	private List<String> keywords;

	@XmlAttribute(name = "coll_id")
	private String collection;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public Integer getBidPageCount() {
		return bidPageCount;
	}

	public void setBidPageCount(Integer bidPageCount) {
		this.bidPageCount = bidPageCount;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return Objects.equals(ids, item.ids) &&
				Objects.equals(bidId, item.bidId) &&
				Objects.equals(bidPageCount, item.bidPageCount) &&
				Objects.equals(keywords, item.keywords) &&
				Objects.equals(collection, item.collection);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ids, bidId, bidPageCount, keywords, collection);
	}
}
