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
    private static final String AUTHENTICATION_SCHEME = "Basic";

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
            if (!encodedValue.startsWith(AUTHENTICATION_SCHEME)) {
                abort(requestContext, Response.Status.UNAUTHORIZED);
                return;
            }
            final String encodedUserPassword = encodedValue.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String email = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            String message = String.format("User '%s' trying to accessed '%s' resource",
                    email, resourceInfo.getResourceClass().getAnnotation(Path.class).value());
            Logger.getLogger().flow(getClass(), message);

            //Verify user access
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);

            //Is user valid?
            if (!isUserAllowed(email, password, rolesAnnotation.value())) {
                abort(requestContext, Response.Status.FORBIDDEN);
            }
        }
    }

    private boolean isUserAllowed(final String email, final String password, final String[] roles) {
        final Predicate<UserPojo> matches = u -> Objects.equals(email, u.getProperty(EMAIL.DB))
                && Objects.equals(encode(email, password), u.getProperty(PASSWORD.DB));

        return DaoFactory.create(UserPojo.class).getAll().stream()
                .filter(matches)
                .map(u -> u.getProperty(NAME.DB))
                .map(String::valueOf)
                .anyMatch(Arrays.asList(roles)::contains);
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

    private static String encode(String email, String password) {
        return new String(Base64.encode(String.format("%s:%s", email, password).getBytes()));
    }
}
