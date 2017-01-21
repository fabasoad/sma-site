package org.fabasoad.rest;

import org.fabasoad.db.pojo.BasePojo;
import org.fabasoad.db.pojo.MainPojo;
import org.fabasoad.db.pojo.PojoProperties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("main")
public class MainResource extends BaseResource<MainPojo> {
    @Override
    public Class<MainPojo> getPojoClass() {
        return MainPojo.class;
    }

    @Override
    public Function<String, Optional<String>> fromDto() {
        return PojoProperties.Main::fromDto;
    }

    @Override
    public Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.Main.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @Override
    String getDisplayName() {
        return "Main";
    }

    @Override
    @SuppressWarnings("unchecked")
    JSONObject buildObject(BasePojo pojo) {
        JSONObject json = new JSONObject();
        json.put(PojoProperties.Main.PROP_VALUE.DTO, pojo.getProperty(PojoProperties.Main.PROP_VALUE.DB));
        return json;
    }

    @Override
    void fillPojo(MainPojo pojo, JSONObject json) {
        pojo.setProperty(PojoProperties.Main.PROP_NAME.DB, PojoProperties.Main.BODY_KEY);
        pojo.setProperty(PojoProperties.Main.PROP_VALUE.DB, json.get(PojoProperties.Main.PROP_VALUE.DTO));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMain() {
        return get(PojoProperties.Main.BODY_KEY);
    }

    @PUT
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @SuppressWarnings("unchecked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMain(String input) {
        JSONObject json;
        try {
            json = (JSONObject) new JSONParser().parse(input);
        } catch (ParseException ignored) {
            String body;
            try {
                body = URLDecoder.decode(input.split("=")[1], StandardCharsets.UTF_8.displayName());
            } catch (UnsupportedEncodingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(buildError(e.getMessage()).toJSONString())
                        .build();
            }

            json = new JSONObject();
            json.put(PojoProperties.Main.PROP_VALUE.DTO, body);
        }
        return update(json);
    }
}
