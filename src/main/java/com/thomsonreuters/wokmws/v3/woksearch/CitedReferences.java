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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour citedReferences complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="citedReferences">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="databaseId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="queryLanguage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="retrieveParameters" type="{http://woksearch.v3.wokmws.thomsonreuters.com}retrieveParameters"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "citedReferences", propOrder = {
    "databaseId",
    "uid",
    "queryLanguage",
    "retrieveParameters"
})
public class CitedReferences {

    @XmlElement(required = true)
    protected String databaseId;
    @XmlElement(required = true)
    protected String uid;
    @XmlElement(required = true)
    protected String queryLanguage;
    @XmlElement(required = true)
    protected RetrieveParameters retrieveParameters;

    /**
     * Obtient la valeur de la propriété databaseId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseId() {
        return databaseId;
    }

    /**
     * Définit la valeur de la propriété databaseId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseId(String value) {
        this.databaseId = value;
    }

    /**
     * Obtient la valeur de la propriété uid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUid() {
        return uid;
    }

    /**
     * Définit la valeur de la propriété uid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Obtient la valeur de la propriété queryLanguage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryLanguage() {
        return queryLanguage;
    }

    /**
     * Définit la valeur de la propriété queryLanguage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryLanguage(String value) {
        this.queryLanguage = value;
    }

    /**
     * Obtient la valeur de la propriété retrieveParameters.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveParameters }
     *     
     */
    public RetrieveParameters getRetrieveParameters() {
        return retrieveParameters;
    }

    /**
     * Définit la valeur de la propriété retrieveParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveParameters }
     *     
     */
    public void setRetrieveParameters(RetrieveParameters value) {
        this.retrieveParameters = value;
    }

}
