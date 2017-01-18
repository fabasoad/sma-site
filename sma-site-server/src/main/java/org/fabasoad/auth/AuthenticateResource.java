package org.fabasoad.auth;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.fabasoad.crypto.CryptoUtils;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.UserPojo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import static org.fabasoad.api.Logger.getLogger;
import static org.fabasoad.rest.provider.AuthenticationUtils.SMA_SESSION_COOKIE_NAME;
import static org.fabasoad.rest.provider.AuthenticationUtils.getUser;

/**
 * @author efabizhevsky
 * @date 1/11/2017.
 */
@Path("/")
public class AuthenticateResource {

    private static String CRYPTO_SALT_PROPERTY_NAME = "crypto-salt";
    private static Properties config = new Properties();
    static {
        InputStream inputStream =
                AuthenticateResource.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            config.load(inputStream);
        } catch (IOException e) {
            getLogger().error(AuthenticateResource.class, e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @SuppressWarnings("unchecked")
    public Response login(String input) {
        String email = null;
        String password = null;
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(input);
            email = (String) json.get("email");
            password = (String) json.get("password");
        } catch (ParseException ignored) {
            for (String element : input.split("&")) {
                String[] pair = element.split("=");
                if (pair.length == 2) {
                    try {
                        if (Objects.equals(PojoProperties.Users.EMAIL.DTO, pair[0])) {
                            email = URLDecoder.decode(pair[1], StandardCharsets.UTF_8.displayName());
                        } else if (Objects.equals(PojoProperties.Users.PASSWORD.DTO, pair[0])) {
                            password = URLDecoder.decode(pair[1], StandardCharsets.UTF_8.displayName());
                        }
                    } catch (UnsupportedEncodingException e) {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(buildError(e.getMessage()).toJSONString())
                                .build();
                    }
                }
            }
        }
        final String encryptedPassword = CryptoUtils.BCrypt.encrypt(password, config.getProperty(CRYPTO_SALT_PROPERTY_NAME));
        final Optional<UserPojo> user = getUser(email, encryptedPassword);

        if (!user.isPresent()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        NewCookie cookie = new NewCookie(
            SMA_SESSION_COOKIE_NAME,
            Base64.encode(String.format("%s:%s", email, encryptedPassword).getBytes())
        );

        return Response.status(Response.Status.CREATED)
                .entity(buildSuccess().toJSONString())
                .cookie(cookie)
                .build();
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildSuccess() {
        final JSONObject json = new JSONObject();
        json.put("type", "success");
        json.put("message", "Login successful");
        return json;
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildError(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "error");
        json.put("message", message);
        return json;
    }
}
