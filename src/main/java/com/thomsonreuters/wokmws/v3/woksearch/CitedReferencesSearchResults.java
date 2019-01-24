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
package com.thomsonreuters.wokmws.v3.woksearch;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour citedReferencesSearchResults complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="citedReferencesSearchResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="references" type="{http://woksearch.v3.wokmws.thomsonreuters.com}citedReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="recordsFound" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recordsSearched" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "citedReferencesSearchResults", propOrder = {
    "queryId",
    "references",
    "recordsFound",
    "recordsSearched"
})
public class CitedReferencesSearchResults {

    protected String queryId;
    @XmlElement(nillable = true)
    protected List<CitedReference> references;
    protected int recordsFound;
    protected long recordsSearched;

    /**
     * Obtient la valeur de la propriété queryId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * Définit la valeur de la propriété queryId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryId(String value) {
        this.queryId = value;
    }

    /**
     * Gets the value of the references property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the references property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CitedReference }
     * 
     * 
     */
    public List<CitedReference> getReferences() {
        if (references == null) {
            references = new ArrayList<CitedReference>();
        }
        return this.references;
    }

    /**
     * Obtient la valeur de la propriété recordsFound.
     * 
     */
    public int getRecordsFound() {
        return recordsFound;
    }

    /**
     * Définit la valeur de la propriété recordsFound.
     * 
     */
    public void setRecordsFound(int value) {
        this.recordsFound = value;
    }

    /**
     * Obtient la valeur de la propriété recordsSearched.
     * 
     */
    public long getRecordsSearched() {
        return recordsSearched;
    }

    /**
     * Définit la valeur de la propriété recordsSearched.
     * 
     */
    public void setRecordsSearched(long value) {
        this.recordsSearched = value;
    }

}
