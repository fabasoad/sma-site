package org.fabasoad.rest;

import org.apache.commons.lang3.StringUtils;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.VacanciesPojo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("vacancies")
public class VacanciesResource extends BaseResource<VacanciesPojo> {

    private final Pattern PARSE_INPUT_PATTERN = Pattern.compile("([^\\&=]*)");

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
    @RolesAllowed(Roles.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVacancy(String input) {
        JSONObject jsonVacancy;
        try {
            jsonVacancy = (JSONObject) new JSONParser().parse(input);
        } catch (ParseException ignored) {
            final Matcher matcher = PARSE_INPUT_PATTERN.matcher(input);
            jsonVacancy = new JSONObject();
            final List<String> values = new LinkedList<>();
            while (matcher.find()) {
                String value = matcher.group(0);
                if (StringUtils.isNotEmpty(value)) {
                    values.add(value);
                }
            }
            try {
                for (int i = 0; i < values.size(); i += 2) {
                    jsonVacancy.put(values.get(i), URLDecoder.decode(values.get(i + 1), StandardCharsets.UTF_8.displayName()));
                }
            } catch (UnsupportedEncodingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(buildError(e.getMessage()).toJSONString())
                        .build();
            }
        }
        return create(jsonVacancy);
    }

    @PUT
    @Path("{id}")
    @RolesAllowed(Roles.ADMIN)
    @SuppressWarnings("unchecked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVacancy(@PathParam("id") int id, String input) {
        JSONObject json;
        try {
            json = (JSONObject) new JSONParser().parse(input);
        } catch (ParseException ignored) {
            try {
                json = parseInput(input, Arrays.stream(
                        PojoProperties.Vacancies.values()).map(v -> v.DTO).collect(Collectors.toList()));
            } catch (UnsupportedEncodingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(buildError(e.getMessage()).toJSONString())
                        .build();
            }
        }
        json.put(PojoProperties.Vacancies.ID.DTO, id);
        return update(json);
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed(Roles.ADMIN)
    public Response deleteVacancy(@PathParam("id") int id) {
        return delete(id);
    }
}
