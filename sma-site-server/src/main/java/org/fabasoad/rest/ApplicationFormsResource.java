package org.fabasoad.rest;

import com.google.common.collect.ImmutableMap;
import org.fabasoad.db.pojo.ApplicationFormPojo;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.fabasoad.db.pojo.PojoProperties.ApplicationForms;

/**
 * @author efabizhevsky
 * @date 11/22/2016.
 */
@Path("application-forms")
public class ApplicationFormsResource implements BaseResource<ApplicationFormPojo> {

    @Override
    public Class<ApplicationFormPojo> getPojoClass() {
        return ApplicationFormPojo.class;
    }

    @Override
    public Function<String, Optional<String>> fromDto() {
        return ApplicationForms::fromDto;
    }

    @Override
    public Map<String, String> getPojoProperties() {
        return Stream.of(ApplicationForms.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationForms() {
        return getAll();
    }

    @GET
    @Path("{id}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationForm(@PathParam("id") int id) {
        return get(id);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadApplicationForm(@FormDataParam("application-form") InputStream fileInputStream,
                                          @FormDataParam("application-form") FormDataContentDisposition fileMetaData) {
        String filePath;
        try {
            upload(fileInputStream, fileMetaData.getFileName());
            filePath = getUploadPath().resolve(fileMetaData.getFileName()).toString();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(buildError(e.getMessage()).toJSONString())
                    .build();
        }
        return create(buildObject(ImmutableMap.of(ApplicationForms.FILE_NAME.DTO, filePath)));
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("admin")
    public Response deleteApplicationForm(@PathParam("id") int id) {
        return delete(id);
    }
}
