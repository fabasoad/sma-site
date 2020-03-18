package io.github.fabasoad.rest;

import io.github.fabasoad.crypto.CryptoUtils;
import io.github.fabasoad.db.dao.DaoFactory;
import io.github.fabasoad.db.dao.UsersDao;
import io.github.fabasoad.db.dao.context.DaoContextImpl;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.PojoProperties;
import io.github.fabasoad.db.pojo.UserPojo;
import io.github.fabasoad.rest.config.ConfigUtils;
import io.github.fabasoad.ws.rs.PATCH;
import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("users")
public class UsersResource extends BaseResource<UserPojo> {

    private final static String CHANGE_PASSWORD_PROPERTY_OLD = "old-password";
    private final static String CHANGE_PASSWORD_PROPERTY_NEW = "new-password";
    private final static String CHANGE_PASSWORD_PROPERTY_REPEATED = "repeated-password";

    @Override
    Class<UserPojo> getPojoClass() {
        return UserPojo.class;
    }

    @Override
    Function<String, Optional<String>> fromDto() {
        return PojoProperties.Users::fromDto;
    }

    @Override
    void fillPojo(UserPojo pojo, JSONObject json) {
        super.fillPojo(pojo, json);
        if (json.containsKey(PojoProperties.UserRoles.NAME.DTO)) {
            pojo.setProperty(PojoProperties.UserRoles.NAME.DB, json.get(PojoProperties.UserRoles.NAME.DTO));
        }
    }

    @Override
    Map<String, String> getPojoProperties() {
        Map<String, String> result = Stream.of(PojoProperties.Users.values())
                .filter(v -> v != PojoProperties.Users.PASSWORD)
                .collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
        result.put(PojoProperties.UserRoles.NAME.DB, PojoProperties.UserRoles.NAME.DTO);
        return result;
    }

    @Override
    String getDisplayName() {
        return "User";
    }

    @GET
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        return get(id);
    }

    @POST
    @SuppressWarnings("unchecked")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String input) {
        return runAction(input, json -> {
            json.put(PojoProperties.Users.PASSWORD.DTO,
                    CryptoUtils.BCrypt.encrypt("", ConfigUtils.getCryptoSalt()));
            return create(json);
        });
    }

    @PUT
    @Path("{id}")
    @SuppressWarnings("unchecked")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") final int id, String input) {
        return runAction(input, json -> {
            json.put(PojoProperties.Vacancies.ID.DTO, id);
            return update(json);
        });
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(String input) {
        final List<String> keys = Arrays.asList(
                PojoProperties.Users.EMAIL.DTO,
                CHANGE_PASSWORD_PROPERTY_OLD,
                CHANGE_PASSWORD_PROPERTY_NEW,
                CHANGE_PASSWORD_PROPERTY_REPEATED);

        return runAction(input, json -> {
            final Function<Integer, String> prop =
                    i -> CryptoUtils.BCrypt.encrypt((String) json.get(keys.get(i)), ConfigUtils.getCryptoSalt());

            UsersDao dao = (UsersDao) DaoFactory.create(DaoContextImpl.class, getPojoClass());
            try {
                dao.changePassword(
                    (String) json.get(keys.get(0)),
                    prop.apply(1),
                    prop.apply(2),
                    prop.apply(3)
                );
            } catch (ValidationException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
            return Response.ok(buildSuccess("Password changed successfully").toJSONString()).build();
        }, keys);
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    public Response deleteUser(@PathParam("id") int id) {
        return delete(id);
    }
}
