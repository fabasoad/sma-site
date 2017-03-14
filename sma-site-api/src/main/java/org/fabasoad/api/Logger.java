package org.fabasoad.api;

import org.fabasoad.log.Configuration;
import org.fabasoad.log.LogLevel;
import org.fabasoad.log.LoggerImpl;
import org.fabasoad.log.LoggerType;

import java.util.EnumSet;

/**
 * @author efabizhevsky
 * @date 11/30/2016.
 */
public class Logger {

    public static org.fabasoad.log.Logger getLogger() {
        return LoggerImpl.getInstance(buildConfiguration());
    }

    private static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setLoggerTypes(EnumSet.of(LoggerType.CONSOLE));
        configuration.setLogLevels(EnumSet.allOf(LogLevel.class));
        return configuration;
    }
}
