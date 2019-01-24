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
package eu.ill.puma.wosimporter.domain.document.entities;


import eu.ill.puma.wosimporter.domain.document.enumeration.BaseFileType;

public class BaseFile {

	private String data = null;
	private String name = null;
	private String md5 = null;
	private String mimeType = null;
	private String originUrl = null;
	private BaseFileType type = null;
	private Boolean base64Encoded = true;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public BaseFileType getType() {
		return type;
	}

	public void setType(BaseFileType type) {
		this.type = type;
	}

	public Boolean getBase64Encoded() {
		return base64Encoded;
	}

	public void setBase64Encoded(Boolean base64Encoded) {
		this.base64Encoded = base64Encoded;
	}
}

