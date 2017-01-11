package org.fabasoad.rest.provider;

import org.apache.commons.lang3.tuple.Pair;
import org.fabasoad.api.Logger;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.pojo.UserPojo;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Predicate;

import static org.fabasoad.db.pojo.PojoProperties.UserRoles.NAME;
import static org.fabasoad.db.pojo.PojoProperties.Users.EMAIL;
import static org.fabasoad.db.pojo.PojoProperties.Users.PASSWORD;

/**
 * @author efabizhevsky
 * @date 1/11/2017.
 */
public class AuthenticationUtils {

    public static final String AUTHENTICATION_SCHEME = "Basic";

    static void validateUser(final String encodedValue, final String[] roles) throws AuthenticationException {
        if (!encodedValue.startsWith(AUTHENTICATION_SCHEME)) {
            throw new AuthenticationException(Response.Status.UNAUTHORIZED);
        }
        final String encodedUserPassword = encodedValue.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password
        String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

        //Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        //Verify user access
        if (!isUserAllowedInternal(email, password, roles)) {
            throw new AuthenticationException(Response.Status.FORBIDDEN);
        }
    }

    private static boolean isUserAllowedInternal(final String email, final String password, final String[] roles) {
        return getUser(email, password)
                .map(u -> u.getProperty(NAME.DB))
                .map(String::valueOf)
                .filter(Arrays.asList(roles)::contains)
                .isPresent();
    }

    public static Optional<UserPojo> getUser(final String email, final String password) {
        final Predicate<UserPojo> matches = u -> Objects.equals(email, u.getProperty(EMAIL.DB))
                && Objects.equals(encode(password), u.getProperty(PASSWORD.DB));

        return DaoFactory.create(UserPojo.class).getAll().stream().filter(matches).findAny();
    }

    private static String encode(String password) {
        return new String(Base64.encode(password.getBytes()));
    }
}
