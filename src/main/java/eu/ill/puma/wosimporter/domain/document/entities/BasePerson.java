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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BasePerson extends BaseEntity {

	private String publicationName = null;
	private String firstName = null;
	private String lastName = null;
	private String orcidId = null;
	private String researcherId = null;
	private String email = null;
	private Long laboratoryId = null;

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrcidId() {
		return orcidId;
	}

	public void setOrcidId(String orcidId) {
		this.orcidId = orcidId;
	}

	public String getResearcherId() {
		return researcherId;
	}

	public void setResearcherId(String researcherId) {
		this.researcherId = researcherId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getLaboratoryId() {
		return laboratoryId;
	}

	public void setLaboratoryId(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		BasePerson that = (BasePerson) o;

		return new EqualsBuilder()
				.append(publicationName, that.publicationName)
				.append(firstName, that.firstName)
				.append(lastName, that.lastName)
				.append(orcidId, that.orcidId)
				.append(researcherId, that.researcherId)
				.append(email, that.email)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(publicationName)
				.append(firstName)
				.append(lastName)
				.append(orcidId)
				.append(researcherId)
				.append(email)
				.toHashCode();
	}
}

