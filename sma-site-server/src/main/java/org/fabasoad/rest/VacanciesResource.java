package org.fabasoad.rest;

import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.VacanciesPojo;
import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
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

    @SuppressWarnings("unchecked")
    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVacancy(@FormParam("rank") String rank,
                                  @FormParam("vessel-type") String vesselType,
                                  @FormParam("joining-date") String joiningDate,
                                  @FormParam("contract-duration") String contractDuration,
                                  @FormParam("nationality") String nationality,
                                  @FormParam("wage") String wage,
                                  @FormParam("description") String description) {
        JSONObject jsonVacancy = new JSONObject();
        jsonVacancy.put("rank", rank);
        jsonVacancy.put("vessel-type", vesselType);
        jsonVacancy.put("joining-date", joiningDate);
        jsonVacancy.put("contract-duration", contractDuration);
        jsonVacancy.put("nationality", nationality);
        jsonVacancy.put("wage", wage);
        jsonVacancy.put("description", description);
        return create(jsonVacancy);
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("admin")
    public Response deleteVacancy(@PathParam("id") int id) {
        return delete(id);
    }
}
