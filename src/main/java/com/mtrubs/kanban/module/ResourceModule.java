package com.mtrubs.kanban.module;

import com.google.inject.AbstractModule;
import com.mtrubs.kanban.resource.ResourceSerializer;

/**
 * @author mrubino
 * @since 2014-07-13
 */
public class ResourceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ResourceSerializer.class).asEagerSingleton();
    }
}
