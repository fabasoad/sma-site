package org.fabasoad.rest.provider;

import org.fabasoad.api.Logger;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.fabasoad.rest.provider.AuthenticationUtils.SMA_SESSION_COOKIE_NAME;

/**
 * @author efabizhevsky
 * @date 12/2/2016.
 */
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                abort(requestContext, Response.Status.FORBIDDEN);
                return;
            }

            if (!method.isAnnotationPresent(RolesAllowed.class)) {
                return;
            }

            //Get request headers
            Cookie cookie = requestContext.getCookies().get(SMA_SESSION_COOKIE_NAME);

            //If no authorization information present; block access
            if (cookie == null || cookie.getValue().isEmpty()) {
                abort(requestContext, Response.Status.UNAUTHORIZED);
                return;
            }

            //Get encoded username and password
            String encodedValue = cookie.getValue();

            try {
                AuthenticationUtils.validateUser(encodedValue, method.getAnnotation(RolesAllowed.class).value());
            } catch (AuthenticationException e) {
                abort(requestContext, e.getStatus());
            }
        }
    }

    private static void abort(ContainerRequestContext requestContext, Response.Status status) {
        String message;
        switch (status) {
            case UNAUTHORIZED:
                message = "You are not authorized to access this resource";
                break;
            case FORBIDDEN:
                message = "You have no permissions to access this resource";
                break;
            default:
                message = "Server error";
                break;
        }
        Logger.getLogger().warning(AuthenticationFilter.class, message);
        requestContext.abortWith(Response.status(status).entity(message).build());
    }
}
