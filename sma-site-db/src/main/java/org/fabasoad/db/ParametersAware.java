package org.fabasoad.db;

import org.fabasoad.db.adapters.DbAdapter;
import org.fabasoad.db.dao.context.DaoContext;
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
public class ParametersAware<T extends DaoContext> {

    protected static Properties properties = new Properties();

    @SuppressWarnings("unchecked")
    private static <T extends ParametersAware> Class<T> getClazz() {
        try {
            // 0 - Thread, 1 & 2 - ParametersAware, 3 - derived class
            return (Class<T>) Class.forName(Thread.currentThread().getStackTrace()[3].getClassName());
        } catch (ClassNotFoundException e) {
            return (Class<T>) ParametersAware.class;
        }
    }

    protected static <TContext extends DaoContext> void readParameters(Class<TContext> daoContextClazz) {
        Path propertiesFile;
        try {
            propertiesFile = daoContextClazz.getDeclaredConstructor().newInstance().getPropertiesFilePath();
            getLogger().flow(getClazz(), String.format("Trying to read property file from the following path: %s...", propertiesFile));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return;
        }
        if (propertiesFile.toFile().exists() && !propertiesFile.toFile().isDirectory() && Files.isReadable(propertiesFile)) {
            getLogger().flow(getClazz(), String.format("Read parameters from %s file", propertiesFile.toAbsolutePath()));
            try (InputStream input = new FileInputStream(propertiesFile.toFile())) {
                properties.load(input);
            } catch (IOException e) {
                getLogger().error(getClazz(), e.getMessage());
            }
        }
    }

    protected static <TContext extends DaoContext> void writeParameters(
            DbAdapter dbAdapter, Class<TContext> daoContextClazz) {
        Path propertiesFile;
        try {
            propertiesFile = daoContextClazz.getDeclaredConstructor().newInstance().getPropertiesFilePath();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return;
        }

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

        try (OutputStream output = new FileOutputStream(propertiesFile.toFile())) {
            properties.store(output, null);
            getLogger().flow(getClazz(), "Properties file has been created here: " + propertiesFile);
        } catch (IOException e) {
            getLogger().error(getClazz(), e.getMessage());
        }
    }
}
