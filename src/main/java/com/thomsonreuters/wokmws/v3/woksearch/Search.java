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
 * <p>Classe Java pour search complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="search">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryParameters" type="{http://woksearch.v3.wokmws.thomsonreuters.com}queryParameters"/>
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
@XmlType(name = "search", propOrder = {
    "queryParameters",
    "retrieveParameters"
})
public class Search {

    @XmlElement(required = true)
    protected QueryParameters queryParameters;
    @XmlElement(required = true)
    protected RetrieveParameters retrieveParameters;

    /**
     * Obtient la valeur de la propriété queryParameters.
     * 
     * @return
     *     possible object is
     *     {@link QueryParameters }
     *     
     */
    public QueryParameters getQueryParameters() {
        return queryParameters;
    }

    /**
     * Définit la valeur de la propriété queryParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryParameters }
     *     
     */
    public void setQueryParameters(QueryParameters value) {
        this.queryParameters = value;
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
