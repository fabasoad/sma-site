package org.fabasoad.rest;

import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.ReferencePojo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author efabizhevsky
 * @date 11/24/2016.
 */
@Path("references")
public class ReferencesResource implements BaseResource<ReferencePojo> {

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

    @Override
    public Optional<java.nio.file.Path> getUploadPath() {
        return Optional.of(Paths.get(".", "sma-site-webapp", "src", "main", "webapp", "public", "data", "references"));
    }

    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReference(@FormDataParam("file") InputStream fileInputStream,
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                    @FormDataParam("title") String title) {
        upload(fileInputStream, fileMetaData.getFileName());
        //create();
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("admin")
    public Response deleteReference(@PathParam("id") int id) {
        return delete(id);
    }
}