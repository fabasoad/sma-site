package io.github.fabasoad.rest.provider;

import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;

import static io.github.fabasoad.rest.provider.AuthenticationUtils.SMA_SESSION_COOKIE_NAME;

@Slf4j
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
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
        final String message = switch (status) {
            case UNAUTHORIZED -> "You are not authorized to access this resource";
            case FORBIDDEN -> "You have no permissions to access this resource";
            default -> "Server error";
        };
        log.warn(message);
        requestContext.abortWith(Response.status(status).entity(message).build());
    }
}
