package com.mtrubs.kanban.module;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES;

/**
 * @author mrubino
 * @since 2014-07-13
 */
public class KanbanServletModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> params = new HashMap<>();
        // Scan packages and sub packages for Jersey resources.
        params.put(PROPERTY_PACKAGES, "com.mtrubs.kanban.resource");

        // Route all requests through GuiceContainer
        serve("/*").with(GuiceContainer.class, params);
    }
}
