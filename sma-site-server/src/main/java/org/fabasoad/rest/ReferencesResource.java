package org.fabasoad.rest;

import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.pojo.BasePojo;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.ReferencePojo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @RolesAllowed("admin")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReference(@FormDataParam("reference") InputStream fileInputStream,
                                    @FormDataParam("reference") FormDataContentDisposition fileMetaData,
                                    @FormDataParam("title") String title) {
        String fileName = generateNewFileName(fileMetaData.getFileName());
        JSONObject json;
        try {
            json = buildReferenceJsonObject(title, fileName);
            uploadFile(fileInputStream, fileName);
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(buildError(e.getMessage()).toJSONString())
                    .build();
        }
        return create(json);
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildReferenceJsonObject(String title, String fileName) throws IOException {
        JSONObject result = new JSONObject();
        result.put(PojoProperties.References.TITLE.DTO, title);
        result.put(PojoProperties.References.FILE_NAME.DTO, fileName);
        return result;
    }

    @Override
    Object getJSONObjectProperty(BasePojo pojo, String propertyName) {
        return (Objects.equals(propertyName, PojoProperties.References.FILE_NAME.DB)
                ? webPath() : "") + pojo.getProperty(propertyName);
    }

    private void deleteFile(int id) throws IOException {
        String fileName = (String) DaoFactory.create(getPojoClass())
                .get(id).getProperty(PojoProperties.References.FILE_NAME.DB);
        Files.delete(localPath().resolve(fileName));
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("admin")
    public Response deleteReference(@PathParam("id") int id) {
        try {
            deleteFile(id);
        } catch (AccessDeniedException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(buildError("Cannot delete file for selected reference").toJSONString())
                    .build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(buildError(e.getMessage()).toJSONString())
                    .build();
        }
        return delete(id);
    }
}