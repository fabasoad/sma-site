package org.fabasoad.rest;

import org.fabasoad.db.dao.BaseDao;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.dao.DaoType;
import org.fabasoad.db.pojo.BasePojo;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/22/2016.
 */
abstract class BaseResource extends ResourceConfig {

    Path getUploadPath() {
        return null;
    }

    abstract DaoType getDaoType();

    abstract <T extends BasePojo> T createEmptyPojo();

    abstract Function<String, Optional<String>> fromDto();

    abstract Map<String, String> getPojoProperties();

    BaseResource() {
        register(RolesAllowedDynamicFeature.class);
    }

    Response getAll() {
        BaseDao<? extends BasePojo> dao = DaoFactory.create(getDaoType());
        JSONObject json = buildObjects(dao.getAll());
        return Response.ok(json.toJSONString()).build();
    }

    Response get(int id) {
        BaseDao<? extends BasePojo> dao = DaoFactory.create(getDaoType());
        BasePojo pojo = dao.get(id);
        if (pojo == null) {
            final String message = "There is no entity with id = " + id;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildError(message).toJSONString())
                    .build();
        }
        JSONObject json = buildObject(pojo);
        return Response.ok(json.toJSONString()).build();
    }

    Response delete(SecurityContext context, int id) {
        if (context.isUserInRole("admin")) {
            BaseDao<? extends BasePojo> dao = DaoFactory.create(getDaoType());
            dao.delete(id);
            String message = "Entity with id = " + id + " deleted successfully";
            return Response.ok(buildSuccess(message).toJSONString()).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    void upload(SecurityContext context, InputStream fileInputStream, String fileName) {
        if (context.isUserInRole("admin")) {
            try (OutputStream out = new FileOutputStream(new File(getUploadPath().toString(), fileName))) {
                int read;
                byte[] bytes = new byte[1024];

                while ((read = fileInputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            } catch (IOException e) {
                getLogger().error(getClass(), String.format("Error while uploading '%s' file", fileName));
            }
        }
    }

    Response create(SecurityContext context, JSONObject json) {
        if (context.isUserInRole("admin")) {
            BaseDao<? extends BasePojo> dao = DaoFactory.create(DaoType.REFERENCES);
            dao.create(buildPojo(json));
            String message = "Entity created successfully";
            return Response.ok(buildSuccess(message).toJSONString()).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @SuppressWarnings("unchecked")
    private static JSONObject buildSuccess(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "success");
        json.put("message", message);
        return json;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject buildError(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "error");
        json.put("message", message);
        return json;
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildObjects(Collection<? extends BasePojo> pojos) {
        JSONArray array = new JSONArray();
        pojos.stream().map(this::buildObject).forEach(array::add);

        final JSONObject result = new JSONObject();
        result.put("total-count", pojos.size());
        result.put("data", array);
        return result;
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildObject(BasePojo pojo) {
        final JSONObject result = new JSONObject();
        for (Map.Entry<String, String> entry : getPojoProperties().entrySet()) {
            result.put(entry.getValue(), pojo.getProperty(entry.getKey()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    <T extends BasePojo> T buildPojo(JSONObject json) {
        T pojo = createEmptyPojo();
        json.forEach((k,v) -> fromDto().apply(String.valueOf(k)).ifPresent(p -> pojo.setProperty(p, v)));
        return pojo;
    }
}
