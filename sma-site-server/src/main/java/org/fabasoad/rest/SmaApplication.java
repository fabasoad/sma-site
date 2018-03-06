package org.fabasoad.rest;

import org.fabasoad.rest.provider.AdministrationFilter;
import org.fabasoad.rest.provider.AuthenticationFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author efabizhevsky
 * @date 12/2/2016.
 */
public class SmaApplication extends ResourceConfig {

    public SmaApplication() {
        packages("org.fabasoad.rest");
        //register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.OFF, LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
        register(AuthenticationFilter.class);
        register(AdministrationFilter.class);
    }
}
