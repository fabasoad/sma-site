package org.fabasoad.rest;

import org.fabasoad.rest.provider.AuthenticationFilter;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author efabizhevsky
 * @date 12/2/2016.
 */
public class SmaApplication extends ResourceConfig {

    public SmaApplication() {
        packages("org.fabasoad.rest");
        register(LoggingFilter.class);
        register(AuthenticationFilter.class);
    }
}
