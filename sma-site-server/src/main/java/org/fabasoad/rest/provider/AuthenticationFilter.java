package org.fabasoad.rest.provider;

import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
import java.util.StringTokenizer;

/**
 * @author efabizhevsky
 * @date 12/2/2016.
 */
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    private final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You are not authorized to access this resource").build();
    private final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
            .entity("You have no permissions to access this resource").build();

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
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
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }

            //Get encoded username and password
            String encodedValue = authorization.get(0);
            if (!encodedValue.startsWith(AUTHENTICATION_SCHEME)) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
            final String encodedUserPassword = encodedValue.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            //Verifying Username and password
            //Logger.getLogger().flow(getClass(), String.format("User '%s' accessing ", username));

            //Verify user access
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);

            //Is user valid?
            if (!isUserAllowed(username, password, rolesAnnotation.value())) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
            }
        }
    }

    private boolean isUserAllowed(final String username, final String password, final String[] roles) {
        boolean isAllowed = false;

        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);

        if (username.equals("howtodoinjava") && password.equals("password")) {
            String userRole = "ADMIN";

            //Step 2. Verify user role
            if (Arrays.asList(roles).contains(userRole)) {
                isAllowed = true;
            }
        }
        return isAllowed;
    }
}
