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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Name {

	@XmlAttribute(name = "addr_no")
	private Integer addressNo;

	@XmlAttribute(name = "reprint")
	private String reprint;

	@XmlAttribute(name = "role")
	private String role;

	@XmlAttribute(name = "seq_no")
	private Integer SequenceNumber;

	@XmlAttribute(name = "orcid_id")
	private String orcidId;

	@XmlAttribute(name = "r_id")
	private String researcherId;

	@XmlElement(name = "display_name")
	private String displayName;

	@XmlElement(name = "full_name")
	private String fullName;

	@XmlElement(name = "wos_standard")
	private String wosStandardName;

	@XmlElement(name = "first_name")
	private String firstName;

	@XmlElement(name = "last_name")
	private String lastName;

	@XmlElement(name = "email_addr")
	private String emailAddress;

	public Integer getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(Integer addressNo) {
		this.addressNo = addressNo;
	}

	public String getReprint() {
		return reprint;
	}

	public void setReprint(String reprint) {
		this.reprint = reprint;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getSequenceNumber() {
		return SequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		SequenceNumber = sequenceNumber;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getWosStandardName() {
		return wosStandardName;
	}

	public void setWosStandardName(String wosStandardName) {
		this.wosStandardName = wosStandardName;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Name name = (Name) o;
		return Objects.equals(addressNo, name.addressNo) &&
				Objects.equals(reprint, name.reprint) &&
				Objects.equals(role, name.role) &&
				Objects.equals(SequenceNumber, name.SequenceNumber) &&
				Objects.equals(orcidId, name.orcidId) &&
				Objects.equals(researcherId, name.researcherId) &&
				Objects.equals(displayName, name.displayName) &&
				Objects.equals(fullName, name.fullName) &&
				Objects.equals(wosStandardName, name.wosStandardName) &&
				Objects.equals(firstName, name.firstName) &&
				Objects.equals(lastName, name.lastName) &&
				Objects.equals(emailAddress, name.emailAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressNo, reprint, role, SequenceNumber, orcidId, researcherId, displayName, fullName, wosStandardName, firstName, lastName, emailAddress);
	}
}
