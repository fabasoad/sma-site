package io.github.fabasoad.rest.provider;

import javax.ws.rs.core.Response;

class AuthenticationException extends Exception {

    private Response.Status status;

    AuthenticationException(Response.Status status) {
        this.status = status;
    }

    Response.Status getStatus() {
        return status;
    }
}
