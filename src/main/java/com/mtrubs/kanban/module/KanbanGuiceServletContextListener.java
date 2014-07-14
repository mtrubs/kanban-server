package com.mtrubs.kanban.module;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

import java.util.Collection;

/**
 * @author mrubino
 * @since 2014-07-13
 */
public class KanbanGuiceServletContextListener extends GuiceServletContextListener {

    private Injector injector = null;

    @Override
    protected Injector getInjector() {
        Injector injector = this.injector;
        if (injector == null) {
            injector = Guice.createInjector(modules());
            this.injector = injector;
        }
        return injector;
    }

    private Collection<Module> modules() {
        return ImmutableList.<Module>builder()
                .add(new KanbanServletModule())
                .add(new ResourceModule())
                .build();
    }
}
