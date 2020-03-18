package io.github.fabasoad.auth;

import io.github.fabasoad.crypto.CryptoUtils;
import io.github.fabasoad.db.pojo.PojoProperties;
import io.github.fabasoad.db.pojo.UserPojo;
import io.github.fabasoad.rest.config.ConfigUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

import static io.github.fabasoad.rest.provider.AuthenticationUtils.SMA_SESSION_COOKIE_NAME;
import static io.github.fabasoad.rest.provider.AuthenticationUtils.getUser;

@Path("/")
public class AuthenticateResource {

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
        final String encryptedPassword = CryptoUtils.BCrypt.encrypt(password, ConfigUtils.getCryptoSalt());
        final Optional<UserPojo> user = getUser(email, encryptedPassword);

        if (!user.isPresent()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        NewCookie cookie = new NewCookie(
            SMA_SESSION_COOKIE_NAME,
            new String(Base64.getEncoder().encode(String.format("%s:%s", email, encryptedPassword).getBytes()))
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
