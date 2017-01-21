package org.fabasoad.rest;

import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.dao.UsersDao;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.UserPojo;
import org.fabasoad.ws.rs.PATCH;

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

/**
 * @author efabizhevsky
 * @date 1/20/2017.
 */
@Path("users")
public class UsersResource extends BaseResource<UserPojo> {

    private static String CHANGE_PASSWORD_PROPERTY_OLD = "old-password";
    private static String CHANGE_PASSWORD_PROPERTY_NEW = "new-password";
    private static String CHANGE_PASSWORD_PROPERTY_REPEATED = "repeated-password";

    @Override
    Class<UserPojo> getPojoClass() {
        return UserPojo.class;
    }

    @Override
    Function<String, Optional<String>> fromDto() {
        return PojoProperties.Users::fromDto;
    }

    @Override
    Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.Users.values())
                .filter(v -> v != PojoProperties.Users.PASSWORD)
                .collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
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
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String input) {
        return create(input);
    }

    @PUT
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @SuppressWarnings("unchecked")
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
            UsersDao dao = (UsersDao) DaoFactory.create(getPojoClass());
            dao.changePassword(
                (String) json.get(keys.get(0)),
                (String) json.get(keys.get(1)),
                (String) json.get(keys.get(2)),
                (String) json.get(keys.get(3))
            );
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
