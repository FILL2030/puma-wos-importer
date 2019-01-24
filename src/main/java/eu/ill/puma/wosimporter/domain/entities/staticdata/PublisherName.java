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
public class PublisherName {

	@XmlAttribute(name = "addr_no")
	private String publisherAddressNo;

	@XmlAttribute(name = "role")
	private String publisherRole;

	@XmlElement(name = "display_name")
	private String displayName;

	@XmlElement(name = "full_name")
	private String fullName;

	public String getPublisherAddressNo() {
		return publisherAddressNo;
	}

	public void setPublisherAddressNo(String publisherAddressNo) {
		this.publisherAddressNo = publisherAddressNo;
	}

	public String getPublisherRole() {
		return publisherRole;
	}

	public void setPublisherRole(String publisherRole) {
		this.publisherRole = publisherRole;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PublisherName that = (PublisherName) o;
		return Objects.equals(publisherAddressNo, that.publisherAddressNo) &&
				Objects.equals(publisherRole, that.publisherRole) &&
				Objects.equals(displayName, that.displayName) &&
				Objects.equals(fullName, that.fullName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(publisherAddressNo, publisherRole, displayName, fullName);
	}
}
