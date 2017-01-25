package org.fabasoad.rest;

import org.fabasoad.db.dao.BaseDao;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.pojo.NewsPojo;
import org.fabasoad.db.pojo.PojoProperties;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sasha.dev on 11.12.2016.
 */
@Path("news")
public class NewsResource extends BaseResource<NewsPojo> {

    @Override
    public Class<NewsPojo> getPojoClass() {
        return NewsPojo.class;
    }

    @Override
    public Function<String, Optional<String>> fromDto() {
        return PojoProperties.News::fromDto;
    }

    @Override
    public Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.News.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @Override
    String getDisplayName() {
        return "News";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews(@QueryParam("limit") String limit) {
        if (limit == null) {
            return getAll();
        }

        BaseDao<NewsPojo> dao = DaoFactory.create(getPojoClass());
        JSONObject json = buildObjects(dao.getLimit(limit));
        return Response.ok(json.toJSONString()).build();
    }
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews(@PathParam("id") int id) {
        return get(id);
    }
	
    @POST
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNews(String input) {
        return create(input);
    }

    @PUT
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @SuppressWarnings("unchecked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNews(@PathParam("id") final int id, String input) {
        return runAction(input, json -> {
            json.put(PojoProperties.News.ID.DTO, id);
            return update(json);
        });
    }

	@DELETE
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    public Response deleteNews(@PathParam("id") int id) {
        return delete(id);
    }
}
