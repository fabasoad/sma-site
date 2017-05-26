package org.fabasoad.rest;

import org.fabasoad.db.pojo.BasePojo;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.VacanciesPojo;

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
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("vacancies")
public class VacanciesResource extends BaseResource<VacanciesPojo> {

    @Override
    public Class<VacanciesPojo> getPojoClass() {
        return VacanciesPojo.class;
    }

    @Override
    public Function<String, Optional<String>> fromDto() {
        return PojoProperties.Vacancies::fromDto;
    }

    @Override
    public Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.Vacancies.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @Override
    String getDisplayName() {
        return "Vacancy";
    }

    @Override
    Object getJSONObjectProperty(BasePojo pojo, String propertyName) {
        if (Objects.equals(PojoProperties.Vacancies.JOINING_DATE.DB, propertyName)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.format(super.getJSONObjectProperty(pojo, propertyName));
        }
        return super.getJSONObjectProperty(pojo, propertyName);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancies() {
        return getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancy(@PathParam("id") int id) {
        return get(id);
    }

    @POST
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVacancy(String input) {
        return create(input);
    }

    @PUT
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @SuppressWarnings("unchecked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVacancy(@PathParam("id") final int id, String input) {
        return runAction(input, json -> {
            json.put(PojoProperties.Vacancies.ID.DTO, id);
            return update(json);
        });
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    public Response deleteVacancy(@PathParam("id") int id) {
        return delete(id);
    }
}
