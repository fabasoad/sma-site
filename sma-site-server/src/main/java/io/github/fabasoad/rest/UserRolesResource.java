package io.github.fabasoad.rest;

import io.github.fabasoad.db.pojo.PojoProperties;
import io.github.fabasoad.db.pojo.UserRolePojo;

import jakarta.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("user-roles")
public class UserRolesResource extends BaseResource<UserRolePojo> {

    @Override
    Class<UserRolePojo> getPojoClass() {
        return UserRolePojo.class;
    }

    @Override
    Function<String, Optional<String>> fromDto() {
        return PojoProperties.UserRoles::fromDto;
    }

    @Override
    Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.UserRoles.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @Override
    String getDisplayName() {
        return "User role";
    }

    @GET
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRoles() {
        return getAll();
    }

    @GET
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRole(@PathParam("id") int id) {
        return get(id);
    }
}
