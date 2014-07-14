package com.mtrubs.kanban.resource;

/**
 * @author mrubino
 * @since 2014-07-13
 */
public abstract class BaseResource {

    private ResourceSerializer serializer;

    protected BaseResource(ResourceSerializer serializer) {
        this.serializer = serializer;
    }

    protected String toJson(Object data) {
        return this.serializer.toJson(data);
    }

    protected <T> T fromJson(String data, Class<T> type) {
        return this.serializer.fromJson(data, type);
    }
}
