package io.github.fabasoad.rest;

import io.github.fabasoad.db.pojo.ApplicationFormPojo;
import io.github.fabasoad.db.pojo.BasePojo;
import io.github.fabasoad.db.pojo.PojoProperties;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jakarta.annotation.security.RolesAllowed;
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
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static io.github.fabasoad.db.pojo.PojoProperties.ApplicationForms;

@Slf4j
@Path("application-forms")
public class ApplicationFormsResource extends BaseResource<ApplicationFormPojo> {

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

    @Override
    String getDisplayName() {
        return "Application form";
    }

    @GET
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationForms() {
        return getAll();
    }

    @GET
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationForm(@PathParam("id") int id) {
        return get(id);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadApplicationForm(@FormDataParam("application-form") InputStream fileInputStream,
                                          @FormDataParam("application-form") FormDataContentDisposition fileMetaData) {
        String fileName = generateNewFileName(fileMetaData.getFileName());
        JSONObject json;
        try {
            json = buildObject(Map.of(ApplicationForms.FILE_NAME.DTO, fileName));
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
    @SuppressWarnings("unchecked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateApplicationForm(@PathParam("id") int id, String input) {
        JSONObject json;
        try {
            json = (JSONObject) new JSONParser().parse(input);
        } catch (ParseException ignored) {
            String senderName;
            try {
                senderName = URLDecoder.decode(input.split("=")[1], StandardCharsets.UTF_8.displayName());
            } catch (UnsupportedEncodingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(buildError(e.getMessage()).toJSONString())
                        .build();
            }
            json = new JSONObject();
            json.put(ApplicationForms.SENDER_NAME.DTO, senderName);
        }
        json.put(ApplicationForms.ID.DTO, id);
        return update(json);
    }

    @Override
    Object getJSONObjectProperty(BasePojo pojo, String propertyName) {
        if (Objects.equals(propertyName, ApplicationForms.FILE_NAME.DB)) {
            return webPath() + pojo.getProperty(propertyName);
        } else if (Objects.equals(propertyName, ApplicationForms.SENDER_NAME.DB)
                && (isNull(pojo.getProperty(propertyName))
                    || Objects.equals(pojo.getProperty(propertyName), "null"))) {
            return "Unknown";
        } else {
            return pojo.getProperty(propertyName);
        }
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    public Response deleteApplicationForm(@PathParam("id") int id) {
        try {
            deleteFile(id, ApplicationForms.FILE_NAME.DB);
        } catch (AccessDeniedException e) {
            log.error("Access denied to {} file.", ApplicationForms.FILE_NAME.DB, e);
        } catch (IOException e) {
            log.error("Failed to delete {} file.", ApplicationForms.FILE_NAME.DB, e);
        }
        return delete(id);
    }
}
