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
 * 
 *       The RawFaultInformation is consists of the static message text of the faultstring, message, reason, cause and remedy elements
 *       along with the message data used to instantiate the message parameters. Message parameters are of the form {0}, {1}, etc. 
 *       and conform to the Java 5 java.text.MessageFormat API.  
 *        
 *       This information did not exist in WokSearch version 2. However the Fault did exist.  
 *       
 * 
 * <p>Classe Java pour RawFaultInformation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RawFaultInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rawFaultstring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rawMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rawReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rawCause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rawRemedy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messageData" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RawFaultInformation", propOrder = {
    "rawFaultstring",
    "rawMessage",
    "rawReason",
    "rawCause",
    "rawRemedy",
    "messageData"
})
public class RawFaultInformation {

    protected String rawFaultstring;
    protected String rawMessage;
    protected String rawReason;
    protected String rawCause;
    protected String rawRemedy;
    protected List<String> messageData;

    /**
     * Obtient la valeur de la propriété rawFaultstring.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRawFaultstring() {
        return rawFaultstring;
    }

    /**
     * Définit la valeur de la propriété rawFaultstring.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRawFaultstring(String value) {
        this.rawFaultstring = value;
    }

    /**
     * Obtient la valeur de la propriété rawMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRawMessage() {
        return rawMessage;
    }

    /**
     * Définit la valeur de la propriété rawMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRawMessage(String value) {
        this.rawMessage = value;
    }

    /**
     * Obtient la valeur de la propriété rawReason.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRawReason() {
        return rawReason;
    }

    /**
     * Définit la valeur de la propriété rawReason.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRawReason(String value) {
        this.rawReason = value;
    }

    /**
     * Obtient la valeur de la propriété rawCause.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRawCause() {
        return rawCause;
    }

    /**
     * Définit la valeur de la propriété rawCause.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRawCause(String value) {
        this.rawCause = value;
    }

    /**
     * Obtient la valeur de la propriété rawRemedy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRawRemedy() {
        return rawRemedy;
    }

    /**
     * Définit la valeur de la propriété rawRemedy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRawRemedy(String value) {
        this.rawRemedy = value;
    }

    /**
     * Gets the value of the messageData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMessageData() {
        if (messageData == null) {
            messageData = new ArrayList<String>();
        }
        return this.messageData;
    }

}
