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
package com.thomsonreuters.wokmws.cxf.auth;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WOKMWSAuthenticate", targetNamespace = "http://auth.cxf.wokmws.thomsonreuters.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WOKMWSAuthenticate {


    /**
     * 
     * @return
     *     returns java.lang.String
     * @throws SessionException_Exception
     * @throws InvalidInputException_Exception
     * @throws ESTIWSException_Exception
     * @throws InternalServerException_Exception
     * @throws AuthenticationException_Exception
     * @throws QueryException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "authenticate", targetNamespace = "http://auth.cxf.wokmws.thomsonreuters.com", className = "com.thomsonreuters.wokmws.cxf.auth.Authenticate")
    @ResponseWrapper(localName = "authenticateResponse", targetNamespace = "http://auth.cxf.wokmws.thomsonreuters.com", className = "com.thomsonreuters.wokmws.cxf.auth.AuthenticateResponse")
    public String authenticate()
        throws AuthenticationException_Exception, ESTIWSException_Exception, InternalServerException_Exception, InvalidInputException_Exception, QueryException_Exception, SessionException_Exception
    ;

    /**
     * 
     * @throws SessionException_Exception
     * @throws InvalidInputException_Exception
     * @throws ESTIWSException_Exception
     * @throws InternalServerException_Exception
     * @throws AuthenticationException_Exception
     * @throws QueryException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "closeSession", targetNamespace = "http://auth.cxf.wokmws.thomsonreuters.com", className = "com.thomsonreuters.wokmws.cxf.auth.CloseSession")
    @ResponseWrapper(localName = "closeSessionResponse", targetNamespace = "http://auth.cxf.wokmws.thomsonreuters.com", className = "com.thomsonreuters.wokmws.cxf.auth.CloseSessionResponse")
    public void closeSession()
        throws AuthenticationException_Exception, ESTIWSException_Exception, InternalServerException_Exception, InvalidInputException_Exception, QueryException_Exception, SessionException_Exception
    ;

}
