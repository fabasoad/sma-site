package io.github.fabasoad.db;

import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.dao.context.DaoContext;
import io.github.fabasoad.db.runner.ParameterName;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
public class ParametersAware<T extends DaoContext> {

    protected static Properties properties = new Properties();

    protected static <TContext extends DaoContext> void readParameters(Class<TContext> daoContextClazz) {
        Path propertiesFile;
        try {
            propertiesFile = daoContextClazz.getDeclaredConstructor().newInstance().getPropertiesFilePath(Paths.get(System.getProperty("user.dir")));
            log.info("Trying to read property file from the following path: {}...", propertiesFile);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return;
        }
        if (propertiesFile.toFile().exists() && !propertiesFile.toFile().isDirectory() && Files.isReadable(propertiesFile)) {
            log.info("Read parameters from {} file", propertiesFile.toAbsolutePath());
            try (InputStream input = new FileInputStream(propertiesFile.toFile())) {
                properties.load(input);
            } catch (IOException e) {
                log.error("Failed to read {} file.", propertiesFile.toAbsolutePath(), e);
            }
        }
    }

    protected static <TContext extends DaoContext> void writeParameters(
            DbAdapter dbAdapter, Class<TContext> daoContextClazz) {
        Path propertiesFile;
        try {
            propertiesFile = daoContextClazz.getDeclaredConstructor().newInstance().getPropertiesFilePath(
                Paths.get(System.getProperty("user.dir"), "sma-site-db"));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return;
        }

        for (Method method : dbAdapter.getClass().getMethods()) {
            if (method.isAnnotationPresent(ParameterName.class)) {
                String parameterName = method.getAnnotation(ParameterName.class).value();
                try {
                    properties.setProperty(parameterName, String.valueOf(method.invoke(dbAdapter)));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.warn("Could not save parameter '{}'", parameterName);
                }
            }
        }

        try (OutputStream output = new FileOutputStream(propertiesFile.toFile())) {
            properties.store(output, null);
            log.info("Properties file has been created here: {}.", propertiesFile);
        } catch (IOException e) {
            log.error("Failed to create '{}' file.", propertiesFile);
        }
    }
}
