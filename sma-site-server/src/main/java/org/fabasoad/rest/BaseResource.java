package org.fabasoad.rest;

import org.fabasoad.db.dao.BaseDao;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.dao.DaoType;
import org.fabasoad.db.pojo.BasePojo;
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
interface BaseResource {

    default Optional<Path> getUploadPath() {
        return Optional.empty();
    }

    DaoType getDaoType();

    <T extends BasePojo> T createEmptyPojo();

    Function<String, Optional<String>> fromDto();

    Map<String, String> getPojoProperties();

    default Response getAll() {
        BaseDao<? extends BasePojo> dao = DaoFactory.create(getDaoType());
        JSONObject json = buildObjects(dao.getAll());
        return Response.ok(json.toJSONString()).build();
    }

    default Response get(int id) {
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

    default Response delete(SecurityContext context, int id) {
        if (context.isUserInRole("admin")) {
            BaseDao<? extends BasePojo> dao = DaoFactory.create(getDaoType());
            dao.delete(id);
            String message = "Entity with id = " + id + " deleted successfully";
            return Response.ok(buildSuccess(message).toJSONString()).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    default void upload(SecurityContext context, InputStream fileInputStream, String fileName) {
        getUploadPath().ifPresent(path -> {
            if (context.isUserInRole("admin")) {
                try (OutputStream out = new FileOutputStream(new File(path.toString(), fileName))) {
                    int read;
                    byte[] bytes = new byte[1024];

                    while ((read = fileInputStream.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                } catch (IOException e) {
                    getLogger().error(getClass(), String.format("Error while uploading '%s' file", fileName));
                }
            }
        });
    }

    default Response create(SecurityContext context, JSONObject json) {
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
    default JSONObject buildObjects(Collection<? extends BasePojo> pojos) {
        JSONArray array = new JSONArray();
        pojos.stream().map(this::buildObject).forEach(array::add);

        final JSONObject result = new JSONObject();
        result.put("total-count", pojos.size());
        result.put("data", array);
        return result;
    }

    @SuppressWarnings("unchecked")
    default JSONObject buildObject(BasePojo pojo) {
        final JSONObject result = new JSONObject();
        for (Map.Entry<String, String> entry : getPojoProperties().entrySet()) {
            result.put(entry.getValue(), pojo.getProperty(entry.getKey()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    default <T extends BasePojo> T buildPojo(JSONObject json) {
        T pojo = createEmptyPojo();
        json.forEach((k,v) -> fromDto().apply(String.valueOf(k)).ifPresent(p -> pojo.setProperty(p, v)));
        return pojo;
    }

    @SuppressWarnings("unchecked")
    default JSONObject buildSuccess(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "success");
        json.put("message", message);
        return json;
    }

    @SuppressWarnings("unchecked")
    default JSONObject buildError(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "error");
        json.put("message", message);
        return json;
    }
}
