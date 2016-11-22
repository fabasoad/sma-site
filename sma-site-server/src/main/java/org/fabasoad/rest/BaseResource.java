package org.fabasoad.rest;

import org.json.simple.JSONObject;

/**
 * @author efabizhevsky
 * @date 11/22/2016.
 */
abstract class BaseResource {

    @SuppressWarnings("unchecked")
    static String buildOk(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "success");
        json.put("message", message);
        return json.toJSONString();
    }

    @SuppressWarnings("unchecked")
    static String buildError(String message) {
        final JSONObject json = new JSONObject();
        json.put("type", "error");
        json.put("message", message);
        return json.toJSONString();
    }
}
