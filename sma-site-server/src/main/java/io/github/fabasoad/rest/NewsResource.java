package io.github.fabasoad.rest;

import io.github.fabasoad.db.dao.BaseDao;
import io.github.fabasoad.db.dao.DaoFactory;
import io.github.fabasoad.db.dao.context.DaoContextImpl;
import io.github.fabasoad.db.pojo.BasePojo;
import io.github.fabasoad.db.pojo.NewsPojo;
import io.github.fabasoad.db.pojo.PojoProperties;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import jakarta.annotation.security.RolesAllowed;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
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

    @Override
    Object getJSONObjectProperty(BasePojo pojo, String propertyName) {
        if (Objects.equals(PojoProperties.News.CREATION_DATE.DB, propertyName)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            try {
                Date date = dateFormat.parse(super.getJSONObjectProperty(pojo, propertyName).toString());
                return dateFormat.format(date);
            } catch (ParseException e) {
                log.error("Failed to parse JSON.", e);
            }
        }
        return super.getJSONObjectProperty(pojo, propertyName);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews(@QueryParam("limit") String limit) {
        if (limit == null) {
            return getAll();
        }

        BaseDao<NewsPojo> dao = DaoFactory.create(DaoContextImpl.class, getPojoClass());
        Collection<NewsPojo> news = new ArrayList<>();
        int totalCount = dao.getLimit(limit, news);
        if (totalCount == -1) {
            String message = String.format(
                    "There is an error while retrieving count of %s. Please contact administrator", getDisplayName());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }
        JSONObject json = buildObjects(news, totalCount);
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
