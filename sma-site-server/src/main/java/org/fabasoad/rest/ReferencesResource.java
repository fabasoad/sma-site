package org.fabasoad.rest;

import com.google.common.collect.ImmutableMap;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.pojo.BasePojo;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.ReferencePojo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/24/2016.
 */
@Path("references")
public class ReferencesResource extends BaseResource<ReferencePojo> {

    @Override
    public Class<ReferencePojo> getPojoClass() {
        return ReferencePojo.class;
    }

    @Override
    public Function<String, Optional<String>> fromDto() {
        return PojoProperties.References::fromDto;
    }

    @Override
    public Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.References.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @Override
    String getDisplayName() {
        return "Reference";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReferences() {
        return getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReference(@PathParam("id") int id) {
        return get(id);
    }

    @POST
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReference(@FormDataParam("reference") InputStream fileInputStream,
                                    @FormDataParam("reference") FormDataContentDisposition fileMetaData) {
        String fileName = generateNewFileName(fileMetaData.getFileName());
        JSONObject json;
        try {
            json = buildObject(ImmutableMap.of(PojoProperties.References.FILE_NAME.DTO, fileName));
            uploadFile(fileInputStream, fileName);
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(buildError(e.getMessage()).toJSONString())
                    .build();
        }
        return create(json);
    }

    @PUT
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @SuppressWarnings("unchecked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReference(@PathParam("id") int id, String input) {
        JSONObject json;
        try {
            json = (JSONObject) new JSONParser().parse(input);
        } catch (ParseException ignored) {
            String title;
            try {
                title = URLDecoder.decode(input.split("=")[1], StandardCharsets.UTF_8.displayName());
            } catch (UnsupportedEncodingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(buildError(e.getMessage()).toJSONString())
                        .build();
            }

            json = new JSONObject();
            json.put(PojoProperties.References.TITLE.DTO, title);
        }
        json.put(PojoProperties.References.ID.DTO, id);
        return update(json);
    }

    @Override
    Object getJSONObjectProperty(BasePojo pojo, String propertyName) {
        return (Objects.equals(propertyName, PojoProperties.References.FILE_NAME.DB)
                ? webPath() : "") + pojo.getProperty(propertyName);
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    public Response deleteReference(@PathParam("id") int id) {
        try {
            deleteFile(id, PojoProperties.References.FILE_NAME.DB);
        } catch (AccessDeniedException e) {
            getLogger().error(getClass(), String.format("Access denied for %s file", e.getMessage()));
        } catch (IOException e) {
            getLogger().error(getClass(), e.getMessage());
        }
        return delete(id);
    }
}