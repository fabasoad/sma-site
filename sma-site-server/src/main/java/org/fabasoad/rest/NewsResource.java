package org.fabasoad.rest;

import org.fabasoad.db.pojo.NewsPojo;
import org.fabasoad.db.pojo.PojoProperties;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response getNews() {
        return getAll();
    }
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews(@PathParam("id") int id) {
        return get(id);
    }
	
    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("unchecked")
    public Response createNews(@FormParam("title") String title,
                               @FormParam("body") String body) {
        
	String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	JSONObject jsonNew = new JSONObject();
        jsonNew.put("title", title);
        jsonNew.put("body", body);
	jsonNew.put("current-date", currentDate);
        return create(jsonNew);
    }

    @PUT
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("unchecked")
    public Response updateNews(@FormParam("id") int id, @FormParam("title") String title,
                               @FormParam("body") String body) {
        
	String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	JSONObject jsonNew = new JSONObject();
        jsonNew.put("id", id);	
        jsonNew.put("title", title);
        jsonNew.put("body", body);
	jsonNew.put("current-date", currentDate);
        return create(jsonNew);
    }

	@DELETE
    @Path("{id}")
    @RolesAllowed("admin")
    public Response deleteNews(@PathParam("id") int id) {
        return delete(id);
    }


}
