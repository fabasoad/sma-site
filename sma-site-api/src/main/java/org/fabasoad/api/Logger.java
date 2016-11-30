package org.fabasoad.api;

import org.fabasoad.log.LoggerImpl;
import org.fabasoad.log.LoggerType;

/**
 * @author efabizhevsky
 * @date 11/30/2016.
 */
public class Logger {

    public static org.fabasoad.log.Logger getLogger() {
        return LoggerImpl.getInstance(LoggerType.CONSOLE);
    }
}
