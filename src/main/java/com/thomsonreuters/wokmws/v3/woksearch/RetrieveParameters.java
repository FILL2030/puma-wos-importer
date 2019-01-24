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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour retrieveParameters complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firstRecord" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sortField" type="{http://woksearch.v3.wokmws.thomsonreuters.com}sortField" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="viewField" type="{http://woksearch.v3.wokmws.thomsonreuters.com}viewField" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="option" type="{http://woksearch.v3.wokmws.thomsonreuters.com}keyValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveParameters", propOrder = {
    "firstRecord",
    "count",
    "sortField",
    "viewField",
    "option"
})
public class RetrieveParameters {

    protected int firstRecord;
    protected int count;
    protected List<SortField> sortField;
    protected List<ViewField> viewField;
    protected List<KeyValuePair> option;

    /**
     * Obtient la valeur de la propriété firstRecord.
     * 
     */
    public int getFirstRecord() {
        return firstRecord;
    }

    /**
     * Définit la valeur de la propriété firstRecord.
     * 
     */
    public void setFirstRecord(int value) {
        this.firstRecord = value;
    }

    /**
     * Obtient la valeur de la propriété count.
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * Définit la valeur de la propriété count.
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the value of the sortField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sortField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSortField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SortField }
     * 
     * 
     */
    public List<SortField> getSortField() {
        if (sortField == null) {
            sortField = new ArrayList<SortField>();
        }
        return this.sortField;
    }

    /**
     * Gets the value of the viewField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the viewField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViewField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ViewField }
     * 
     * 
     */
    public List<ViewField> getViewField() {
        if (viewField == null) {
            viewField = new ArrayList<ViewField>();
        }
        return this.viewField;
    }

    /**
     * Gets the value of the option property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the option property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOption().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValuePair }
     * 
     * 
     */
    public List<KeyValuePair> getOption() {
        if (option == null) {
            option = new ArrayList<KeyValuePair>();
        }
        return this.option;
    }

}
