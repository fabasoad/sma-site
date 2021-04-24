package io.github.fabasoad.rest;

import io.github.fabasoad.db.pojo.BasePojo;
import io.github.fabasoad.db.pojo.CarouselImagesPojo;
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

@Slf4j
@Path("carousel-images")
public class CarouselImagesResource extends BaseResource<CarouselImagesPojo> {
    @Override
    Class<CarouselImagesPojo> getPojoClass() {
        return CarouselImagesPojo.class;
    }

    @Override
    Function<String, Optional<String>> fromDto() {
        return PojoProperties.CarouselImages::fromDto;
    }

    @Override
    Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.CarouselImages.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @Override
    String getDisplayName() {
        return "Carousel image";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarouselImages() {
        return getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarouselImage(@PathParam("id") int id) {
        return get(id);
    }

    @POST
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCarouselImage(@FormDataParam("carousel-image") InputStream fileInputStream,
                                        @FormDataParam("carousel-image") FormDataContentDisposition fileMetaData) {
        String fileName = generateNewFileName(fileMetaData.getFileName());
        JSONObject json;
        try {
            json = buildObject(Map.of(PojoProperties.CarouselImages.FILE_NAME.DTO, fileName));
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
    public Response updateCarouselImage(@PathParam("id") int id, String input) {
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
            json.put(PojoProperties.CarouselImages.TITLE.DTO, title);
        }
        json.put(PojoProperties.CarouselImages.ID.DTO, id);
        return update(json);
    }

    @Override
    Object getJSONObjectProperty(BasePojo pojo, String propertyName) {
        return (Objects.equals(propertyName, PojoProperties.CarouselImages.FILE_NAME.DB)
            ? webPath() : "") + pojo.getProperty(propertyName);
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed(PojoProperties.UserRoles.Values.ADMIN)
    public Response deleteCarouselImage(@PathParam("id") int id) {
        try {
            deleteFile(id, PojoProperties.CarouselImages.FILE_NAME.DB);
        } catch (AccessDeniedException e) {
            log.error("Access denied to {} file.", PojoProperties.CarouselImages.FILE_NAME.DB, e);
        } catch (IOException e) {
            log.error("Failed to delete {} file.", PojoProperties.CarouselImages.FILE_NAME.DB, e);
        }
        return delete(id);
    }
}
