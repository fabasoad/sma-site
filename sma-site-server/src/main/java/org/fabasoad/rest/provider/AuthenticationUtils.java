package org.fabasoad.rest.provider;

import org.apache.commons.lang3.StringUtils;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.dao.context.DaoContextImpl;
import org.fabasoad.db.pojo.UserPojo;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Base64;
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

    public static final String SMA_SESSION_COOKIE_NAME = "SMA_SESSION";

    static void validateUser(final String encodedValue, final String[] roles) throws AuthenticationException {
        //Decode username and password
        String emailAndPassword = new String(Base64.getDecoder().decode(encodedValue.getBytes()));

        //Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(emailAndPassword, ":");
        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        // Verify user access
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
        final Predicate<UserPojo> matches = u -> StringUtils.equalsIgnoreCase(email, (String) u.getProperty(EMAIL.DB))
                && Objects.equals(password, u.getProperty(PASSWORD.DB));

        return DaoFactory.create(DaoContextImpl.class, UserPojo.class).getAll().stream().filter(matches).findAny();
    }
}
