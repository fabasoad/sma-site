package io.github.fabasoad.rest;

import io.github.fabasoad.db.pojo.ParamPojo;
import io.github.fabasoad.db.pojo.PojoProperties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

@Path("params")
public class ParamsResource extends BaseResource<ParamPojo> {

    @Override
    Class<ParamPojo> getPojoClass() {
        return ParamPojo.class;
    }

    @Override
    Function<String, Optional<String>> fromDto() {
        return PojoProperties.Params::fromDto;
    }

    @Override
    Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.Params.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @Override
    String getDisplayName() {
        return "Param";
    }

    @GET
    @Path("{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParam(@PathParam("key") String key) {
        return get(key);
    }

    @PUT
    @Path("{key}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @SuppressWarnings("unchecked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateParam(@PathParam("key") String key, String input) {
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
            json.put(PojoProperties.Params.PROP_VALUE.DTO, body);
        }
        json.put(PojoProperties.Params.PROP_NAME.DTO, key);
        return update(json);
    }
}
