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
/*
 * Importer Api
 * importer api to launch importer a send document to the pcc
 *
 * OpenAPI spec version: 1.0.0
 * 
 */


package eu.ill.puma.wosimporter.domain.capabilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.util.Objects;

/**
 * query filter supported by the importer
 */
@ApiModel(description = "query filter supported by the importer")
public class SupportedQueryFilter {
	@JsonProperty("year")
	private Boolean year = null;

	@JsonProperty("month")
	private Boolean month = null;

	@JsonProperty("search")
	private Boolean search = null;

	@JsonProperty("ids")
	private Boolean ids = null;

	@JsonProperty("start")
	private Boolean start = null;

	public Boolean getYear() {
		return year;
	}

	public void setYear(Boolean year) {
		this.year = year;
	}

	public Boolean getMonth() {
		return month;
	}

	public void setMonth(Boolean month) {
		this.month = month;
	}

	public Boolean getSearch() {
		return search;
	}

	public void setSearch(Boolean search) {
		this.search = search;
	}

	public Boolean getIds() {
		return ids;
	}

	public void setIds(Boolean ids) {
		this.ids = ids;
	}

	public Boolean getStart() {
		return start;
	}

	public void setStart(Boolean start) {
		this.start = start;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SupportedQueryFilter that = (SupportedQueryFilter) o;
		return Objects.equals(year, that.year) &&
				Objects.equals(month, that.month) &&
				Objects.equals(search, that.search) &&
				Objects.equals(ids, that.ids) &&
				Objects.equals(start, that.start);
	}

	@Override
	public int hashCode() {
		return Objects.hash(year, month, search, ids, start);
	}
}

