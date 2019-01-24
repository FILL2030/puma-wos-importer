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
 * <p>Classe Java pour queryParameters complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="queryParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="databaseId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userQuery" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="editions" type="{http://woksearch.v3.wokmws.thomsonreuters.com}editionDesc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="symbolicTimeSpan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeSpan" type="{http://woksearch.v3.wokmws.thomsonreuters.com}timeSpan" minOccurs="0"/>
 *         &lt;element name="queryLanguage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryParameters", propOrder = {
    "databaseId",
    "userQuery",
    "editions",
    "symbolicTimeSpan",
    "timeSpan",
    "queryLanguage"
})
public class QueryParameters {

    @XmlElement(required = true)
    protected String databaseId;
    @XmlElement(required = true)
    protected String userQuery;
    protected List<EditionDesc> editions;
    protected String symbolicTimeSpan;
    protected TimeSpan timeSpan;
    @XmlElement(required = true)
    protected String queryLanguage;

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
     * Obtient la valeur de la propriété userQuery.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserQuery() {
        return userQuery;
    }

    /**
     * Définit la valeur de la propriété userQuery.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserQuery(String value) {
        this.userQuery = value;
    }

    /**
     * Gets the value of the editions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the editions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EditionDesc }
     * 
     * 
     */
    public List<EditionDesc> getEditions() {
        if (editions == null) {
            editions = new ArrayList<EditionDesc>();
        }
        return this.editions;
    }

    /**
     * Obtient la valeur de la propriété symbolicTimeSpan.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbolicTimeSpan() {
        return symbolicTimeSpan;
    }

    /**
     * Définit la valeur de la propriété symbolicTimeSpan.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbolicTimeSpan(String value) {
        this.symbolicTimeSpan = value;
    }

    /**
     * Obtient la valeur de la propriété timeSpan.
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     * Définit la valeur de la propriété timeSpan.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setTimeSpan(TimeSpan value) {
        this.timeSpan = value;
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

}
