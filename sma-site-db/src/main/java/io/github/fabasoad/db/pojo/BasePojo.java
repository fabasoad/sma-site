package io.github.fabasoad.db.pojo;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePojo {

    private Map<String, Object> properties = new HashMap<>();

    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }
}
