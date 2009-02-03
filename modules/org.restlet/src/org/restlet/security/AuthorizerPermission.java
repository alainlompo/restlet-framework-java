/**
 * Copyright 2005-2009 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of the following open
 * source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.sun.com/cddl/cddl.html
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.security;

import java.security.BasicPermission;

/**
 * Permission that gives access rights beyond a specific authorizer for a list
 * of uniform methods.
 * 
 * @author Jerome Louvel
 */
public class AuthorizerPermission extends BasicPermission {

    private static final long serialVersionUID = 1L;

    /** The list of authorized method names. */
    private volatile String actions;

    /**
     * Constructor.
     * 
     * @param name
     *            The {@link Authorizer} identifier.
     * @param actions
     *            The list of authorized method names.
     */
    public AuthorizerPermission(String name, String actions) {
        super(name);
        this.actions = actions;
    }

    @Override
    public String getActions() {
        return this.actions;
    }

}
