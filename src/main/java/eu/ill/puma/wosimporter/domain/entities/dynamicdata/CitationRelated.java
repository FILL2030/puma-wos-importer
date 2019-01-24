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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class CitationRelated {

	@XmlElementWrapper(name = "tc_list")
	@XmlElement(name = "silo_tc")
	private List<SiloTc> siloTc;

	public List<SiloTc> getSiloTc() {
		return siloTc;
	}

	public void setSiloTc(List<SiloTc> siloTc) {
		this.siloTc = siloTc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CitationRelated that = (CitationRelated) o;
		return Objects.equals(siloTc, that.siloTc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(siloTc);
	}
}
