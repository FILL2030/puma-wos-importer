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

import io.swagger.annotations.ApiModel;

/**
 * operation supported by the importer
 */
@ApiModel(description = "operation supported by the importer")
public class SupportedOperations {

	private Boolean getAll = null;

	public Boolean getGetAll() {
		return getAll;
	}

	public void setGetAll(Boolean getAll) {
		this.getAll = getAll;
	}

	@Override
	public String toString() {
		return "SupportedOperations{" +
				"getAll=" + getAll +
				'}';
	}

}

