package org.fabasoad.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/30/2016.
 */
public class ParametersAware {

    protected static String DB_TYPE_PARAM_NAME = "db-type";
    protected static String DEPLOY_PATH_PARAM_NAME = "deploy-path";

    protected static Properties properties = new Properties();
    private static Path PROPERTIES_FILE;

    static {
        try {
            String path = new File(ParametersAware.class
                    .getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath();
            PROPERTIES_FILE = Paths.get(path, "sma-db-setup.properties");
        } catch (URISyntaxException e) {
            getLogger().error(getClazz(), e.getMessage());
        }
    }


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

    protected static void writeParameters(String dbType, String deployPath) {
        properties.setProperty(DB_TYPE_PARAM_NAME, dbType);
        properties.setProperty(DEPLOY_PATH_PARAM_NAME, deployPath);

        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE.toFile())) {
            properties.store(output, null);
        } catch (IOException e) {
            getLogger().error(getClazz(), e.getMessage());
        }
    }
}
