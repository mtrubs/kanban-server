package com.mtrubs.kanban.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author mrubino
 * @since 2014-07-13
 */
public class ResourceSerializer {

    private Gson gson = new GsonBuilder().create();

    public String toJson(Object data) {
        return this.gson.toJson(data);
    }

    public <T> T fromJson(String data, Class<T> type) {
        return this.gson.fromJson(data, type);
    }
}
