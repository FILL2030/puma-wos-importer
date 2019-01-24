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
public class PublisherAddress {

	@XmlAttribute(name = "addr_no")
	private String publisherAddressNo;

	@XmlElement(name = "full_address")
	private String fullAddress;

	@XmlElement(name = "city")
	private String city;

	public String getPublisherAddressNo() {
		return publisherAddressNo;
	}

	public void setPublisherAddressNo(String publisherAddressNo) {
		this.publisherAddressNo = publisherAddressNo;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PublisherAddress that = (PublisherAddress) o;
		return Objects.equals(publisherAddressNo, that.publisherAddressNo) &&
				Objects.equals(fullAddress, that.fullAddress) &&
				Objects.equals(city, that.city);
	}

	@Override
	public int hashCode() {
		return Objects.hash(publisherAddressNo, fullAddress, city);
	}
}
