package org.fabasoad.rest;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author efabizhevsky
 * @date 11/22/2016.
 */
@Path("application-forms")
public class ApplicationFormsResource extends BaseResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationForms() {
        return Response.ok(buildBulkExampleJson().toJSONString()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationForm(@PathParam("id") int id) {
        return Response.ok(buildExampleJson(id).toJSONString()).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadApplicationForm(@FormDataParam("file") InputStream fileInputStream,
                                          @FormDataParam("file") FormDataContentDisposition fileMetaData) {
        final String UPLOAD_PATH = "C:/temp/";
        int generatedId = 999;
        try (OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()))) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            JSONObject entity = buildError("Error while uploading file");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entity.toJSONString()).build();
        }
        return Response.ok(buildExampleJson(generatedId).toJSONString()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteApplicationForm(@PathParam("id") int id) {
        String message = String.format("Application form 'application-form-%s.csv' has been deleted successfully", id);
        return Response.ok(buildOk(message)).build();
    }

    @SuppressWarnings("unchecked")
    private static JSONObject buildExampleJson(int id) {
        final JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("url", String.format("public/data/application-form-%s.csv", id));
        return json;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject buildBulkExampleJson() {
        JSONArray data = new JSONArray();
        data.add(buildExampleJson(1));
        data.add(buildExampleJson(2));
        data.add(buildExampleJson(3));
        return buildArray(data);
    }
}
