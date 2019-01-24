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
package eu.ill.puma.wosimporter.domain.entities;

import eu.ill.puma.wosimporter.domain.entities.dynamicdata.CitationRelated;
import eu.ill.puma.wosimporter.domain.entities.dynamicdata.ClusterRelated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class DynamicData {

    @XmlElement(name = "citation_related")
    private CitationRelated citationRelated;

    @XmlElement(name = "cluster_related")
    private ClusterRelated clusterRelated;

    public CitationRelated getCitationRelated() {
        return citationRelated;
    }

    public void setCitationRelated(CitationRelated citationRelated) {
        this.citationRelated = citationRelated;
    }

    public ClusterRelated getClusterRelated() {
        return clusterRelated;
    }

    public void setClusterRelated(ClusterRelated clusterRelated) {
        this.clusterRelated = clusterRelated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicData that = (DynamicData) o;
        return Objects.equals(citationRelated, that.citationRelated) &&
                Objects.equals(clusterRelated, that.clusterRelated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(citationRelated, clusterRelated);
    }
}
