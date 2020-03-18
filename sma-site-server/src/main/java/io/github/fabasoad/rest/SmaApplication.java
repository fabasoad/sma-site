package io.github.fabasoad.rest;

import io.github.fabasoad.rest.provider.AdministrationFilter;
import io.github.fabasoad.rest.provider.AuthenticationFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class SmaApplication extends ResourceConfig {

    public SmaApplication() {
        packages("io.github.fabasoad.rest");
        register(AuthenticationFilter.class);
        register(AdministrationFilter.class);
    }
}
