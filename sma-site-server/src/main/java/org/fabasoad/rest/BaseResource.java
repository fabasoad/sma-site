package org.fabasoad.rest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author efabizhevsky
 * @date 11/22/2016.
 */
abstract class BaseResource {

    final String UPLOAD_PATH = "C:/temp/";

    @SuppressWarnings("unchecked")
    static JSONObject buildOk(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "success");
        json.put("message", message);
        return json;
    }

    @SuppressWarnings("unchecked")
    static JSONObject buildError(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "error");
        json.put("message", message);
        return json;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject buildArray(JSONArray array) {
        final JSONObject json = new JSONObject();
        json.put("total-count", array.size());
        json.put("data", array);
        return json;
    }

    abstract JSONObject buildObject(int id);

    @SuppressWarnings("unchecked")
    JSONObject buildObjects() {
        JSONArray array = new JSONArray();
        array.add(buildObject(1));
        array.add(buildObject(2));
        array.add(buildObject(3));
        return buildArray(array);
    }
}
