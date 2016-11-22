package org.fabasoad.rest;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
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
    @Produces("text/plain")
    public String getApplicationForms() {
        return "Not implemented yet";
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadApplicationForm(@FormDataParam("file") InputStream fileInputStream,
                                          @FormDataParam("file") FormDataContentDisposition fileMetaData) {
        String entity = buildOk(String.format("File '%s' has been uploaded successfully", fileMetaData.getFileName()));
        final String UPLOAD_PATH = "C:/temp/";
        try (OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()))) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            entity = buildError("Error while uploading file");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entity).build();
        }
        return Response.ok(entity).build();
    }
}
