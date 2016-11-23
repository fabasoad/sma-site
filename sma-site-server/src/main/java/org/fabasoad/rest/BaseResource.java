package org.fabasoad.rest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author efabizhevsky
 * @date 11/22/2016.
 */
abstract class BaseResource {

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
    static JSONObject buildArray(JSONArray array) {
        final JSONObject json = new JSONObject();
        json.put("total-count", array.size());
        json.put("data", array);
        return json;
    }
}
