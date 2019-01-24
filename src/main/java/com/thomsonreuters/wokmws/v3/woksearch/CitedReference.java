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
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *     In version 2, the sequence was articleID, citedAuthor, citedTitle, citedWork, page, recID, refID, timesCited, volume, year.
 *     
 * 
 * <p>Classe Java pour citedReference complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="citedReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="articleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="citedAuthor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timesCited" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="volume" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="citedTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="citedWork" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "citedReference", propOrder = {
    "uid",
    "docid",
    "articleId",
    "citedAuthor",
    "timesCited",
    "year",
    "page",
    "volume",
    "citedTitle",
    "citedWork",
    "hot"
})
public class CitedReference {

    protected String uid;
    protected String docid;
    protected String articleId;
    protected String citedAuthor;
    protected String timesCited;
    protected String year;
    protected String page;
    protected String volume;
    protected String citedTitle;
    protected String citedWork;
    protected String hot;

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
     * Obtient la valeur de la propriété docid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocid() {
        return docid;
    }

    /**
     * Définit la valeur de la propriété docid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocid(String value) {
        this.docid = value;
    }

    /**
     * Obtient la valeur de la propriété articleId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * Définit la valeur de la propriété articleId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleId(String value) {
        this.articleId = value;
    }

    /**
     * Obtient la valeur de la propriété citedAuthor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitedAuthor() {
        return citedAuthor;
    }

    /**
     * Définit la valeur de la propriété citedAuthor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitedAuthor(String value) {
        this.citedAuthor = value;
    }

    /**
     * Obtient la valeur de la propriété timesCited.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimesCited() {
        return timesCited;
    }

    /**
     * Définit la valeur de la propriété timesCited.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimesCited(String value) {
        this.timesCited = value;
    }

    /**
     * Obtient la valeur de la propriété year.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYear() {
        return year;
    }

    /**
     * Définit la valeur de la propriété year.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Obtient la valeur de la propriété page.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPage() {
        return page;
    }

    /**
     * Définit la valeur de la propriété page.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPage(String value) {
        this.page = value;
    }

    /**
     * Obtient la valeur de la propriété volume.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Définit la valeur de la propriété volume.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolume(String value) {
        this.volume = value;
    }

    /**
     * Obtient la valeur de la propriété citedTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitedTitle() {
        return citedTitle;
    }

    /**
     * Définit la valeur de la propriété citedTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitedTitle(String value) {
        this.citedTitle = value;
    }

    /**
     * Obtient la valeur de la propriété citedWork.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitedWork() {
        return citedWork;
    }

    /**
     * Définit la valeur de la propriété citedWork.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitedWork(String value) {
        this.citedWork = value;
    }

    /**
     * Obtient la valeur de la propriété hot.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHot() {
        return hot;
    }

    /**
     * Définit la valeur de la propriété hot.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHot(String value) {
        this.hot = value;
    }

}
