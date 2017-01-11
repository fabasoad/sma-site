package org.fabasoad.auth;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.fabasoad.db.pojo.UserPojo;
import org.fabasoad.rest.provider.AdministrationFilter;
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
import java.util.Objects;
import java.util.Optional;

import static org.fabasoad.rest.provider.AuthenticationUtils.AUTHENTICATION_SCHEME;
import static org.fabasoad.rest.provider.AuthenticationUtils.getUser;

/**
 * @author efabizhevsky
 * @date 1/11/2017.
 */
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
                        if (Objects.equals("email", pair[0])) {
                            email = URLDecoder.decode(pair[1], StandardCharsets.UTF_8.displayName());
                        } else if (Objects.equals("password", pair[0])) {
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
        Optional<UserPojo> user = getUser(email, password);

        if (!user.isPresent()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        NewCookie cookie = new NewCookie(
            AdministrationFilter.SMA_SESSION_COOKIE_NAME,
            AUTHENTICATION_SCHEME + " " + Base64.encode(String.format("%s:%s", email, password).getBytes())
        );

        return Response.status(Response.Status.CREATED)
                .entity(buildSuccess("Login successful").toJSONString())
                .cookie(cookie)
                .build();
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildSuccess(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "success");
        json.put("message", message);
        return json;
    }

    @SuppressWarnings("unchecked")
    JSONObject buildError(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "error");
        json.put("message", message);
        return json;
    }
}
