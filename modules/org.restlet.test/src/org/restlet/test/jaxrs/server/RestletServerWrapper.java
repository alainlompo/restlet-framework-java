/*
 * Copyright 2005-2008 Noelios Consulting.
 * 
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the "License"). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL HEADER in each file and
 * include the License file at http://www.opensource.org/licenses/cddl1.txt If
 * applicable, add the following below this CDDL HEADER, with the fields
 * enclosed by brackets "[]" replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */
package org.restlet.test.jaxrs.server;

import org.restlet.Application;
import org.restlet.Client;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.data.Request;
import org.restlet.data.Response;

/**
 * This class allows easy testing of JAX-RS implementations by starting a server
 * for a given class and access the server for a given sub pass relativ to the
 * pass of the root resource class.
 * 
 * @author Stephan Koops
 */
public class RestletServerWrapper implements ServerWrapper {

    /**
     * @author Stephan
     *
     */
    private final class ClientConnector extends Client {
        /**
         * @param protocol
         */
        private ClientConnector(Protocol protocol) {
            super(protocol);
        }

        @Override
        public void handle(Request request, Response response) {
            request.setOriginalRef(request.getResourceRef());
            super.handle(request, response);
        }
    }

    private Component component;

    public RestletServerWrapper() {
    }

    /**
     * @see org.restlet.test.jaxrs.server.ServerWrapper#getClientConnector()
     */
    public Restlet getClientConnector() {
        return new ClientConnector(Protocol.HTTP);
    }

    public int getServerPort() {
        if (this.component == null) {
            throw new IllegalStateException("the server is not started yet.");
        }
        final Server server = this.component.getServers().get(0);
        int port = server.getPort();
        if (port > 0) {
            return port;
        }
        port = server.getEphemeralPort();
        if (port > 0) {
            return port;
        }
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(20);
            } catch (final InterruptedException e) {
                // 
            }
            port = server.getEphemeralPort();
            if (port > 0) {
                return port;
            }
        }
        throw new IllegalStateException("Sorry, the port is not available");
    }

    /**
     * Starts the server with the given protocol on the given port with the
     * given Collection of root resource classes. The method {@link #setUp()}
     * will do this on every test start up.
     * 
     * @param appConfig
     * @throws Exception
     */
    public void startServer(Application application, Protocol protocol)
            throws Exception {

        final Component comp = new Component();
        comp.getServers().add(protocol, 0);

        // Attach the application to the component and start it
        comp.getDefaultHost().attach(application);
        comp.start();
        this.component = comp;
        System.out.println("listening on port " + getServerPort());
    }

    /**
     * Stops the component. The method {@link #tearDown()} do this after every
     * test.
     * 
     * @param component
     * @throws Exception
     */
    public void stopServer() throws Exception {
        if (this.component != null) {
            this.component.stop();
        }
    }
}