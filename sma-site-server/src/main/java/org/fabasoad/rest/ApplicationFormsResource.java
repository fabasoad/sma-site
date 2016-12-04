package org.fabasoad.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author efabizhevsky
 * @date 11/22/2016.
 */
@Path("application-forms")
public class ApplicationFormsResource implements BaseResource {

    @Override
    public Class getPojoClass() {
        return null;
    }

    @Override
    public Function<String, Optional<String>> fromDto() {
        return null;
    }

    @Override
    public Map<String, String> getPojoProperties() {
        return new HashMap<>();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationForms() {
        return getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationForm(@PathParam("id") int id) {
        return get(id);
    }

//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response uploadApplicationForm(@FormDataParam("file") InputStream fileInputStream,
//                                          @FormDataParam("file") FormDataContentDisposition fileMetaData) {
//        int generatedId = 999;
//        try (OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()))) {
//            int read;
//            byte[] bytes = new byte[1024];
//
//            while ((read = fileInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//        } catch (IOException e) {
//            JSONObject entity = buildError("Error while uploading file");
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entity.toJSONString()).build();
//        }
//        return Response.status(Response.Status.CREATED).entity(buildObject(generatedId).toJSONString()).build();
//    }

    @DELETE
    @Path("{id}")
    public Response deleteApplicationForm(@PathParam("id") int id) {
        return delete(id);
    }
}
