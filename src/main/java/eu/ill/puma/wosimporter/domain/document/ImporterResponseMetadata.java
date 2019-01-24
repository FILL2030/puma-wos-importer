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
package eu.ill.puma.wosimporter.domain.document;

import eu.ill.puma.wosimporter.domain.document.enumeration.ImporterStatusEnum;

public class ImporterResponseMetadata {

	private String nextCursor = null;
	private String previousCursor = null;
	private ImporterStatusEnum status = null;
	private String message = null;
	private Long count = null;
	private Long totalCount = null;
	private Long first = null;
	private long requestDuration;

	public ImporterStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ImporterStatusEnum status) {
		this.status = status;
	}

	public String getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(String nextCursor) {
		this.nextCursor = nextCursor;
	}

	public String getPreviousCursor() {
		return previousCursor;
	}

	public void setPreviousCursor(String previousCursor) {
		this.previousCursor = previousCursor;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getFirst() {
		return first;
	}

	public void setFirst(Long first) {
		this.first = first;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getRequestDuration() {
		return requestDuration;
	}

	public void setRequestDuration(long requestDuration) {
		this.requestDuration = requestDuration;
	}
}

