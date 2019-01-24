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
 * <p>Classe Java pour SupportingWebServiceException complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SupportingWebServiceException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteNamespace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remoteOperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remoteCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remoteReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handshakeCauseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handshakeCause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportingWebServiceException", propOrder = {
    "remoteNamespace",
    "remoteOperation",
    "remoteCode",
    "remoteReason",
    "handshakeCauseId",
    "handshakeCause"
})
public class SupportingWebServiceException {

    protected String remoteNamespace;
    protected String remoteOperation;
    protected String remoteCode;
    protected String remoteReason;
    protected String handshakeCauseId;
    protected String handshakeCause;

    /**
     * Obtient la valeur de la propriété remoteNamespace.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteNamespace() {
        return remoteNamespace;
    }

    /**
     * Définit la valeur de la propriété remoteNamespace.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteNamespace(String value) {
        this.remoteNamespace = value;
    }

    /**
     * Obtient la valeur de la propriété remoteOperation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteOperation() {
        return remoteOperation;
    }

    /**
     * Définit la valeur de la propriété remoteOperation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteOperation(String value) {
        this.remoteOperation = value;
    }

    /**
     * Obtient la valeur de la propriété remoteCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteCode() {
        return remoteCode;
    }

    /**
     * Définit la valeur de la propriété remoteCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteCode(String value) {
        this.remoteCode = value;
    }

    /**
     * Obtient la valeur de la propriété remoteReason.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteReason() {
        return remoteReason;
    }

    /**
     * Définit la valeur de la propriété remoteReason.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteReason(String value) {
        this.remoteReason = value;
    }

    /**
     * Obtient la valeur de la propriété handshakeCauseId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandshakeCauseId() {
        return handshakeCauseId;
    }

    /**
     * Définit la valeur de la propriété handshakeCauseId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandshakeCauseId(String value) {
        this.handshakeCauseId = value;
    }

    /**
     * Obtient la valeur de la propriété handshakeCause.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandshakeCause() {
        return handshakeCause;
    }

    /**
     * Définit la valeur de la propriété handshakeCause.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandshakeCause(String value) {
        this.handshakeCause = value;
    }

}
