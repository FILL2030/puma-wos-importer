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
public class AddressName {

	@XmlElementWrapper(name = "names")
	@XmlElement(name = "name")
	private List<Name> names;

	@XmlElement(name = "address_spec")
	private LaboratoryAddress laboAddress;

	@XmlAttribute(name = "addr_no=")
	private Integer addressNo;

	public List<Name> getNames() {
		return names;
	}

	public void setNames(List<Name> names) {
		this.names = names;
	}

	public LaboratoryAddress getLaboAddress() {
		return laboAddress;
	}

	public void setLaboAddress(LaboratoryAddress laboAddress) {
		this.laboAddress = laboAddress;
	}

	public Integer getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(Integer addressNo) {
		this.addressNo = addressNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AddressName that = (AddressName) o;
		return Objects.equals(names, that.names) &&
				Objects.equals(laboAddress, that.laboAddress) &&
				Objects.equals(addressNo, that.addressNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(names, laboAddress, addressNo);
	}
}
