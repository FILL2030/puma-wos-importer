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
public class WOSPublisher {

	@XmlElement(name = "address_spec")
	private PublisherAddress publisherAddress;

	@XmlElementWrapper(name = "names")
	@XmlElement(name = "name")
	private List<PublisherName> publisherName;

	public PublisherAddress getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(PublisherAddress publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public List<PublisherName> getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(List<PublisherName> publisherName) {
		this.publisherName = publisherName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WOSPublisher that = (WOSPublisher) o;
		return Objects.equals(publisherAddress, that.publisherAddress) &&
				Objects.equals(publisherName, that.publisherName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(publisherAddress, publisherName);
	}
}
