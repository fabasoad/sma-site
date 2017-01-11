package org.fabasoad.rest.provider;

import org.fabasoad.api.Logger;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.pojo.UserPojo;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Predicate;

import static org.fabasoad.db.pojo.PojoProperties.Users.EMAIL;
import static org.fabasoad.db.pojo.PojoProperties.Users.PASSWORD;
import static org.fabasoad.db.pojo.PojoProperties.UserRoles.NAME;

/**
 * @author efabizhevsky
 * @date 12/2/2016.
 */
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";

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
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            //If no authorization information present; block access
            if (authorization == null || authorization.isEmpty()) {
                abort(requestContext, Response.Status.UNAUTHORIZED);
                return;
            }

            //Get encoded username and password
            String encodedValue = authorization.get(0);

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
