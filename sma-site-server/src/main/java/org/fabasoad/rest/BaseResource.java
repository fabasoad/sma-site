package org.fabasoad.rest;

import org.fabasoad.api.Logger;
import org.fabasoad.db.dao.BaseDao;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.pojo.BasePojo;
import org.fabasoad.db.pojo.PojoProperties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/22/2016.
 */
abstract class BaseResource<T extends BasePojo> {

    String webPath() {
        String folder = getClass().getAnnotation(javax.ws.rs.Path.class).value();
        return "/public/data/" + folder + "/";
    }

    Path localPath() {
        String folder = getClass().getAnnotation(javax.ws.rs.Path.class).value();
        return Paths.get(System.getProperty("user.dir"), "sma-site-webapp", "src", "main", "webapp", "public", "data", folder);
    }

    abstract Class<T> getPojoClass();

    abstract Function<String, Optional<String>> fromDto();

    abstract Map<String, String> getPojoProperties();

    abstract String getDisplayName();

    Response getAll() {
        BaseDao<T> dao = DaoFactory.create(getPojoClass());
        JSONObject json = buildObjects(dao.getAll());
        return Response.ok(json.toJSONString()).build();
    }

    Response get(int id) {
        BaseDao<T> dao = DaoFactory.create(getPojoClass());
        BasePojo pojo = dao.get(id);
        if (pojo == null) {
            final String message = String.format("There is no %s with id = %s", getDisplayName(), id);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildError(message).toJSONString())
                    .build();
        }
        JSONObject json = buildObject(pojo);
        return Response.ok(json.toJSONString()).build();
    }

    Response delete(int id) {
        BaseDao<T> dao = DaoFactory.create(getPojoClass());
        dao.delete(id);
        getLogger().flow(getClass(), getDisplayName() + " with id = " + id + " deleted successfully");
        return Response.ok(buildSuccess(getDisplayName() + " deleted successfully").toJSONString()).build();
    }

    void uploadFile(InputStream fileInputStream, String fileName) throws IOException {
        File file = new File(localPath().toString(), fileName);
        file.getParentFile().mkdirs();
        try (OutputStream out = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            getLogger().error(getClass(), String.format("Error while uploading '%s' file", fileName));
            throw e;
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    static String generateNewFileName(String oldFileName) {
        return String.format("%s.%s", randomAlphabetic(10), getExtension(oldFileName));
    }

    Response create(JSONObject json) {
        BaseDao<T> dao = DaoFactory.create(getPojoClass());
        int id = dao.create(buildPojo(json));
        String message;
        if (id == -1) {
            message = getDisplayName() + " is not created";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        } else {
            message = getDisplayName() + " created successfully";
            return Response.status(Response.Status.CREATED).entity(buildSuccess(id, message).toJSONString()).build();
        }
    }

    Response update(JSONObject json) {
        BaseDao<T> dao = DaoFactory.create(getPojoClass());
        dao.update(buildPojo(json));
        String message = getDisplayName() + " updated successfully";
        return Response.ok(buildSuccess(message).toJSONString()).build();
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

    Object getJSONObjectProperty(BasePojo pojo, String propertyName) {
        return pojo.getProperty(propertyName);
    }

    void deleteFile(int id, String propertyName) throws IOException {
        String fileName = (String) DaoFactory.create(getPojoClass()).get(id).getProperty(propertyName);
        Files.delete(localPath().resolve(fileName));
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildObject(BasePojo pojo) {
        final JSONObject result = new JSONObject();
        for (Map.Entry<String, String> entry : getPojoProperties().entrySet()) {
            result.put(entry.getValue(), getJSONObjectProperty(pojo, entry.getKey()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    JSONObject buildObject(Map<String, String> properties) {
        final JSONObject result = new JSONObject();
        properties.forEach(result::put);
        return result;
    }

    @SuppressWarnings("unchecked")
    private T buildPojo(JSONObject json) {
        Object[] pojo = new Object[1];
        try {
            pojo[0] = getPojoClass().newInstance();
            json.forEach((k,v) -> fromDto().apply(String.valueOf(k)).ifPresent(p -> ((T) pojo[0]).setProperty(p, v)));
        } catch (InstantiationException | IllegalAccessException e) {
            Logger.getLogger().error(getClass(), e.getMessage());
        }
        return (T) pojo[0];
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildSuccess(int id, String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "success");
        json.put("message", message);
        json.put("id", id);
        return json;
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildSuccess(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "success");
        json.put("message", message);
        return json;
    }

    @SuppressWarnings("unchecked")
    JSONObject buildError(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "error");
        json.put("message", message);
        return json;
    }
}
