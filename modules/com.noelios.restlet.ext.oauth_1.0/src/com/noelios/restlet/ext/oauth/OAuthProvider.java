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

package com.noelios.restlet.ext.oauth;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;

/**
 * Manages OAuth state and operations.
 * 
 * @author Adam Rosien
 */
public abstract class OAuthProvider {

    /**
     * Add a consumer.
     * 
     * @param key
     * @param consumer
     */
    abstract void addConsumer(String key, OAuthConsumer consumer);

    /**
     * Generate an access token.
     * 
     * @param accessor
     */
    abstract void generateAccessToken(OAuthAccessor accessor);

    /**
     * Generate a request token.
     * 
     * @param accessor
     */
    abstract void generateRequestToken(OAuthAccessor accessor);

    /**
     * Get the accessor from a request.
     * 
     * @param requestMessage
     * @return Accessor, null if none found.
     */
    abstract OAuthAccessor getAccessor(OAuthMessage requestMessage);

    /**
     * Get the consumer from a request.
     * 
     * @param requestMessage
     * @return Consumer, null if none found.
     */
    abstract OAuthConsumer getConsumer(OAuthMessage requestMessage);

    /**
     * Authorize a request token for a user.
     * 
     * @param accessor
     * @param userId
     */
    abstract void markAsAuthorized(OAuthAccessor accessor, String userId);

}
