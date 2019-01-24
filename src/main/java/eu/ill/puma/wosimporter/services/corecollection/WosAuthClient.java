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
package eu.ill.puma.wosimporter.services.corecollection;

import com.thomsonreuters.wokmws.cxf.auth.WOKMWSAuthenticate;
import com.thomsonreuters.wokmws.cxf.auth.WOKMWSAuthenticateService;
import com.thomsonreuters.wokmws.v3.woksearch.WokSearch;
import com.thomsonreuters.wokmws.v3.woksearch.WokSearchService;
import eu.ill.puma.wosimporter.services.corecollection.exception.WosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.Cookie;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WosAuthClient {

    private static final Logger log = LoggerFactory.getLogger(WosAuthClient.class);

    private WOKMWSAuthenticateService service;

    private WOKMWSAuthenticate authPort;

    private WokSearch port;

    private String sessionIdentifier;

    private String user;

    private String password;

    public WosAuthClient(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * connect to the web service after object creation
     *
     * @throws Exception
     */
    public void openSession() throws WosException {
        if (user == null || password == null) {
            throw new RuntimeException("wos login or password not set, importer halted");
        }

        this.createAuthPort();
        String sid = this.getNewSessionIdentifier();

        this.createPort();
        this.setSessionCookie(sid);
    }

    /**
     * Close the session: make a call to the closeSession operation
     *
     * @throws Exception
     */
    public void closeSession() {
        try {
            authPort.closeSession();
        } catch (Exception e) {
            log.error("error, can't close wos session " + e.getMessage());
        }
    }

    public WokSearch getPort() {
        return port;
    }

    /**
     * Close the session: make a call to the closeSession operation
     *
     * @throws Exception
     */
    public void renewSession() throws WosException {
        String sid = this.getNewSessionIdentifier();
        this.setSessionCookie(sid);
    }


    protected String getSessionIdentifier() {
        return sessionIdentifier;
    }


    /**
     * Set the session cookie
     *
     * @param sid session identifier
     */
    private void setSessionCookie(String sid) {
        //get context
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();

        //create auth cookie
        context.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        Cookie cookie = new Cookie("SID", sid);

        //set auth cookie in the header
        Map<String, List<String>> httpHeaders = new HashMap<String, List<String>>();
        httpHeaders.put("Content-Encoding", Collections.singletonList("UTF-8"));
        httpHeaders.put("Cookie", Collections.singletonList("SID=" + sid));
        context.put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
    }


    /**
     * return a new session identifier SID
     *
     * @return the session identifier
     * @throws Exception
     */
    private String getNewSessionIdentifier() throws WosException {
        try {
            BindingProvider bp = (BindingProvider) authPort;
            Map<String, Object> context = bp.getRequestContext();
            context.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.valueOf(true));

            //comment to use ip auth
            if (user != null && password != null) {
                context.put(BindingProvider.USERNAME_PROPERTY, user);
                context.put(BindingProvider.PASSWORD_PROPERTY, password);
            }

            sessionIdentifier = authPort.authenticate();

        } catch (Exception e) {
            throw new WosException("Can't get a new wos session identifier", e);
        }
        return sessionIdentifier;
    }

    /**
     * create the connection authPort
     *
     * @throws Exception
     */
    private void createAuthPort() {
        try {

            service = new WOKMWSAuthenticateService();
            authPort = service.getWOKMWSAuthenticatePort();

            BindingProvider bp = (BindingProvider) authPort;
            Map<String, Object> context = bp.getRequestContext();
            context.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.valueOf(true));
        } catch (Exception e) {
            log.error("wos authPort creation error", e);
        }
    }

    /**
     * @return the port (e.g. instance of the WokSearchLite interface) for the
     * WokSearchLite Web service
     * @throws Exception
     */
    private void createPort() {
        try {
            WokSearchService ss = new WokSearchService();
            port = ss.getWokSearchPort();
        } catch (Exception e) {
            log.error("error, can not connect to wos web service", e);
        }
    }
}
