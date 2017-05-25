package org.fabasoad.db;

import org.fabasoad.db.adapters.DbAdapter;
import org.fabasoad.db.runner.ParameterName;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/30/2016.
 */
public class ParametersAware {

    protected static Properties properties = new Properties();
    private static Path PROPERTIES_FILE =
            FileSystems.getDefault().getPath("sma-db-setup.properties").normalize().toAbsolutePath();

    @SuppressWarnings("unchecked")
    private static <T extends ParametersAware> Class<T> getClazz() {
        try {
            // 0 - Thread, 1 & 2 - ParametersAware, 3 - derived class
            return (Class<T>) Class.forName(Thread.currentThread().getStackTrace()[3].getClassName());
        } catch (ClassNotFoundException e) {
            return (Class<T>) ParametersAware.class;
        }
    }

    protected static void readParameters() {
        if (PROPERTIES_FILE.toFile().exists() && !PROPERTIES_FILE.toFile().isDirectory() && Files.isReadable(PROPERTIES_FILE)) {
            getLogger().flow(getClazz(), String.format("Read parameters from %s file", PROPERTIES_FILE.toAbsolutePath()));
            try (InputStream input = new FileInputStream(PROPERTIES_FILE.toFile())) {
                properties.load(input);
            } catch (IOException e) {
                getLogger().error(getClazz(), e.getMessage());
            }
        }
    }

    protected static void writeParameters(DbAdapter dbAdapter) {
        for (Method method : dbAdapter.getClass().getMethods()) {
            if (method.isAnnotationPresent(ParameterName.class)) {
                String parameterName = method.getAnnotation(ParameterName.class).value();
                try {
                    properties.setProperty(parameterName, String.valueOf(method.invoke(dbAdapter)));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    getLogger().warning(getClazz(), String.format("Could not save parameter '%s'", parameterName));
                }
            }
        }

        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE.toFile())) {
            properties.store(output, null);
        } catch (IOException e) {
            getLogger().error(getClazz(), e.getMessage());
        }
    }
}
