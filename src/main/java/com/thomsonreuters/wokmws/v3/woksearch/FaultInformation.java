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
 * 
 *       The FaultInformation is detail for the SOAP fault. This information did not exist in WokSearch version 2. However the Fault did 
 *       exist.  
 *       
 * 
 * <p>Classe Java pour FaultInformation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FaultInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="causeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supportingWebServiceException" type="{http://woksearch.v3.wokmws.thomsonreuters.com}SupportingWebServiceException" minOccurs="0"/>
 *         &lt;element name="remedy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaultInformation", propOrder = {
    "code",
    "message",
    "reason",
    "causeType",
    "cause",
    "supportingWebServiceException",
    "remedy"
})
public class FaultInformation {

    @XmlElement(required = true)
    protected String code;
    protected String message;
    protected String reason;
    protected String causeType;
    protected String cause;
    protected SupportingWebServiceException supportingWebServiceException;
    protected String remedy;

    /**
     * Obtient la valeur de la propriété code.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Définit la valeur de la propriété code.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Obtient la valeur de la propriété message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Définit la valeur de la propriété message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Obtient la valeur de la propriété reason.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Définit la valeur de la propriété reason.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Obtient la valeur de la propriété causeType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCauseType() {
        return causeType;
    }

    /**
     * Définit la valeur de la propriété causeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCauseType(String value) {
        this.causeType = value;
    }

    /**
     * Obtient la valeur de la propriété cause.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCause() {
        return cause;
    }

    /**
     * Définit la valeur de la propriété cause.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCause(String value) {
        this.cause = value;
    }

    /**
     * Obtient la valeur de la propriété supportingWebServiceException.
     * 
     * @return
     *     possible object is
     *     {@link SupportingWebServiceException }
     *     
     */
    public SupportingWebServiceException getSupportingWebServiceException() {
        return supportingWebServiceException;
    }

    /**
     * Définit la valeur de la propriété supportingWebServiceException.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportingWebServiceException }
     *     
     */
    public void setSupportingWebServiceException(SupportingWebServiceException value) {
        this.supportingWebServiceException = value;
    }

    /**
     * Obtient la valeur de la propriété remedy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemedy() {
        return remedy;
    }

    /**
     * Définit la valeur de la propriété remedy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemedy(String value) {
        this.remedy = value;
    }

}
