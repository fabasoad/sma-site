package io.github.fabasoad.rest;

import io.github.fabasoad.db.dao.BaseDao;
import io.github.fabasoad.db.dao.DaoFactory;
import io.github.fabasoad.db.dao.context.DaoContextImpl;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.BasePojo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Slf4j
abstract class BaseResource<T extends BasePojo> {

    static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static final String DATE_FORMAT = "yyyy-MM-dd";

    String webPath() {
        String folder = getClass().getAnnotation(javax.ws.rs.Path.class).value();
        return "/public/data/" + folder + "/";
    }

    private Path localPath() {
        String folder = getClass().getAnnotation(javax.ws.rs.Path.class).value();
        return Paths.get(System.getProperty("user.dir"), "sma-site-webapp", "src", "main", "webapp", "public", "data", folder);
    }

    abstract Class<T> getPojoClass();

    abstract Function<String, Optional<String>> fromDto();

    abstract Map<String, String> getPojoProperties();

    abstract String getDisplayName();

    Response getAll() {
        BaseDao<T> dao = DaoFactory.create(DaoContextImpl.class, getPojoClass());
        Collection<T> elements = dao.getAll();
        JSONObject json = buildObjects(elements, elements.size());
        return Response.ok(json.toJSONString()).build();
    }

    <C> Response get(C id) {
        BaseDao<T> dao = DaoFactory.create(DaoContextImpl.class, getPojoClass());
        BasePojo pojo = dao.get(id);
        if (pojo == null) {
            final String message = String.format("There is no %s with id = %s", getDisplayName(), id);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(buildError(message).toJSONString())
                    .build();
        }
        JSONObject json = buildObject(pojo);
        return Response.ok(json.toJSONString()).build();
    }

    Response delete(int id) {
        BaseDao<T> dao = DaoFactory.create(DaoContextImpl.class, getPojoClass());
        try {
            dao.delete(id);
        } catch (ValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        log.info("{} with ID = {} deleted successfully", getDisplayName(), id);
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
            log.error("Failed to upload {} file.", fileName, e);
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

    Response runAction(String input, Function<JSONObject, Response> action) {
        return runAction(input, action, getPojoProperties().values());
    }

    Response runAction(String input, Function<JSONObject, Response> action, Collection<String> keys) {
        JSONObject json;
        try {
            json = (JSONObject) new JSONParser().parse(input);
        } catch (ParseException ignored) {
            try {
                json = parseInput(input, keys);
            } catch (UnsupportedEncodingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(buildError(e.getMessage()).toJSONString())
                        .build();
            }
        }
        return action.apply(json);
    }

    @SuppressWarnings("unchecked")
    private JSONObject parseInput(String input, Collection<String> keys) throws UnsupportedEncodingException {
        JSONObject json = new JSONObject();
        for (String element : input.split("&")) {
            String[] pair = element.split("=");
            if (pair.length > 0 && pair.length < 3) {
                String value = pair.length == 2 ? URLDecoder.decode(pair[1], StandardCharsets.UTF_8.displayName()) : "";
                for (String key : keys) {
                    if (Objects.equals(key, pair[0])) {
                        json.put(key, value);
                    }
                }
            }
        }
        return json;
    }

    Response create(String input) {
        return runAction(input, this::create);
    }

    Response create(JSONObject json) {
        BaseDao<T> dao = DaoFactory.create(DaoContextImpl.class, getPojoClass());
        int id;
        try {
            id = dao.create(buildPojo(json));
        } catch (ValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
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
        BaseDao<T> dao = DaoFactory.create(DaoContextImpl.class, getPojoClass());
        try {
            dao.update(buildPojo(json));
        } catch (ValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        String message = getDisplayName() + " updated successfully";
        return Response.ok(buildSuccess(message).toJSONString()).build();
    }

    @SuppressWarnings("unchecked")
    JSONObject buildObjects(Collection<? extends BasePojo> pojos, int totalCount) {
        JSONArray array = new JSONArray();
        pojos.stream().map(this::buildObject).forEach(array::add);

        final JSONObject result = new JSONObject();
        result.put("total-count", totalCount);
        result.put("data", array);
        return result;
    }

    Object getJSONObjectProperty(BasePojo pojo, String propertyName) {
        return pojo.getProperty(propertyName);
    }

    void deleteFile(int id, String propertyName) throws IOException {
        String fileName = (String) DaoFactory.create(DaoContextImpl.class, getPojoClass()).get(id).getProperty(propertyName);
        Files.delete(localPath().resolve(fileName));
    }

    @SuppressWarnings("unchecked")
    JSONObject buildObject(BasePojo pojo) {
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
    void fillPojo(final T pojo, JSONObject json) {
        json.forEach((k,v) -> fromDto().apply(String.valueOf(k)).ifPresent(p -> pojo.setProperty(p, v)));
    }

    @SuppressWarnings("unchecked")
    private T buildPojo(JSONObject json) {
        Object pojo = new Object();
        try {
            pojo = getPojoClass().newInstance();
            fillPojo((T) pojo, json);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Failed to create a new instance of class.", e);
        }
        return (T) pojo;
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
    JSONObject buildSuccess(String message) {
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
