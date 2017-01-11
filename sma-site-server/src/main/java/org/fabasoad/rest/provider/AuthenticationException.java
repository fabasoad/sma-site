package org.fabasoad.rest.provider;

import javax.ws.rs.core.Response;

/**
 * @author efabizhevsky
 * @date 1/11/2017.
 */
class AuthenticationException extends Exception {

    private Response.Status status;

    AuthenticationException(Response.Status status) {
        this.status = status;
    }

    Response.Status getStatus() {
        return status;
    }
}
