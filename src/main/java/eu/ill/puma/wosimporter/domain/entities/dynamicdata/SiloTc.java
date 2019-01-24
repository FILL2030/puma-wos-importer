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
package eu.ill.puma.wosimporter.domain.entities.dynamicdata;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class SiloTc {

	@XmlAttribute(name = "coll_id")
	private String collId;

	@XmlAttribute(name = "local_count")
	private String localCount;

	public String getCollId() {
		return collId;
	}

	public void setCollId(String collId) {
		this.collId = collId;
	}

	public String getLocalCount() {
		return localCount;
	}

	public void setLocalCount(String localCount) {
		this.localCount = localCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SiloTc siloTc = (SiloTc) o;
		return Objects.equals(collId, siloTc.collId) &&
				Objects.equals(localCount, siloTc.localCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(collId, localCount);
	}
}
