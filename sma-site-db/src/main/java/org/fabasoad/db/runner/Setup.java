package org.fabasoad.db.runner;

import org.fabasoad.db.DbAdapterFactory;
import org.fabasoad.db.SqlType;
import org.fabasoad.log.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class Setup {

    private static Properties properties = new Properties();
    private static String PROPERTIES_FILE_NAME = "sma-db-setup.properties";

    private static String DB_TYPE_PARAM_NAME = "db-type";
    private static String DEPLOY_PATH_PARAM_NAME = "deploy-path";

    public static void main(String[] args) {
        readParameters();

        String dbType;
        String deployPath;

        if (args.length == 0) {
            dbType = properties.getProperty(DB_TYPE_PARAM_NAME);
            deployPath = properties.getProperty(DEPLOY_PATH_PARAM_NAME);
        } else if (args.length == 2) {
            dbType = args[0];
            deployPath = args[1];

            if (!properties.getProperty(DB_TYPE_PARAM_NAME).equalsIgnoreCase(dbType)
                    || !properties.getProperty(DEPLOY_PATH_PARAM_NAME).equalsIgnoreCase(deployPath)) {
                writeParameters(dbType, deployPath);
            }
        } else {
            String message = "Arguments are missing";
            Logger.getInstance().error(Setup.class, message);
            throw new RuntimeException(message);
        }

        DbAdapterFactory.create(SqlType.valueOf(dbType.toUpperCase())).setUp(deployPath);
    }

    private static void readParameters() {
        final File file = new File(System.getProperty("user.home"), PROPERTIES_FILE_NAME);
        if (file.exists() && !file.isDirectory() && Files.isReadable(file.toPath())) {
            try (InputStream input = new FileInputStream(file)) {
                properties.load(input);
            } catch (IOException e) {
                Logger.getInstance().error(Setup.class, e.getMessage());
            }
        }
    }

    private static void writeParameters(String dbType, String deployPath) {
        properties.setProperty(DB_TYPE_PARAM_NAME, dbType);
        properties.setProperty(DEPLOY_PATH_PARAM_NAME, deployPath);

        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE_NAME)) {
            properties.store(output, null);
        } catch (IOException e) {
            Logger.getInstance().error(Setup.class, e.getMessage());
        }
    }
}
