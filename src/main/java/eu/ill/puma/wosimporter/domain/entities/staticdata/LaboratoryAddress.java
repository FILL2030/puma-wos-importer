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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class LaboratoryAddress {

	@XmlElement(name = "full_address")
	private String fullAddress;

	@XmlElementWrapper(name = "organizations")
	@XmlElement(name = "organization")
	private List<String> organizations;

	@XmlElementWrapper(name = "suborganizations")
	@XmlElement(name = "suborganization")
	private List<String> subOrganizations;

	@XmlElement(name = "street")
	private String street;

	@XmlElement(name = "city")
	private String city;

	@XmlElement(name = "country")
	private String country;

	@XmlElement(name = "zip")
	private String zip;

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public List<String> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<String> organizations) {
		this.organizations = organizations;
	}

	public List<String> getSubOrganizations() {
		return subOrganizations;
	}

	public void setSubOrganizations(List<String> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LaboratoryAddress that = (LaboratoryAddress) o;
		return Objects.equals(fullAddress, that.fullAddress) &&
				Objects.equals(organizations, that.organizations) &&
				Objects.equals(subOrganizations, that.subOrganizations) &&
				Objects.equals(street, that.street) &&
				Objects.equals(city, that.city) &&
				Objects.equals(country, that.country) &&
				Objects.equals(zip, that.zip);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fullAddress, organizations, subOrganizations, street, city, country, zip);
	}
}
