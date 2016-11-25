package org.fabasoad.rest;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author efabizhevsky
 * @date 11/24/2016.
 */
@Path("references")
public class ReferencesResource extends BaseResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReferences() {
        return Response.ok(buildObjects()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReference(@PathParam("id") int id) {
        return Response.ok(buildObject(id)).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReference(@FormDataParam("file") InputStream fileInputStream,
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                    @FormDataParam("title") String title) {
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
        return Response.status(Response.Status.CREATED).entity(buildObject(generatedId).toJSONString()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteReference(@PathParam("id") int id) {
        String message = String.format("Reference 'ref-%s.png' has been deleted successfully", id);
        return Response.ok(buildOk(message)).build();
    }

    @Override
    @SuppressWarnings("unchecked")
    JSONObject buildObject(int id) {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("title", "Reference " + id);
        json.put("src", "public/data/references/ref-" + id + ".png");
        return json;
    }
}