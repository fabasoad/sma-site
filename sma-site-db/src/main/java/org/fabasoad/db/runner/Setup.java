package org.fabasoad.db.runner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.fabasoad.db.DbAdapterFactory;
import org.fabasoad.db.ParametersAware;
import org.fabasoad.db.SqlType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class Setup extends ParametersAware {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        getLogger().flow(Setup.class, "Starting database creation...");
        readParameters();

        SqlType dbType;
        String deployPath;

        if (args.length == 0) {
            dbType = SqlType.valueOfIgnoreCase(properties.getProperty(DB_TYPE_PARAM_NAME));
            deployPath = properties.getProperty(DEPLOY_PATH_PARAM_NAME);
        } else if (args.length == 2) {
            dbType = SqlType.valueOfIgnoreCase(args[0]);
            deployPath = Paths.get(args[1], DbAdapterFactory.getDbName(dbType)).toString();

            if (!StringUtils.equalsIgnoreCase(properties.getProperty(DB_TYPE_PARAM_NAME), dbType.name())
                    || !StringUtils.equalsIgnoreCase(properties.getProperty(DEPLOY_PATH_PARAM_NAME), deployPath)) {
                writeParameters(dbType.name().toLowerCase(), deployPath);
            }
        } else {
            String message = "Arguments are missing";
            getLogger().error(Setup.class, message);
            throw new RuntimeException(message);
        }
        getLogger().flow(Setup.class, "Database type: " + dbType.name());
        getLogger().flow(Setup.class, "Deployment path: " + deployPath);

        if (!StringUtils.isEmpty(deployPath)) {
            if (deployDb(dbType, deployPath)) {
                DbAdapterFactory.create(dbType, deployPath).setUp();
            }
        } else {
            getLogger().error(Setup.class, "Deploy path is empty");
        }
        long endTime = System.currentTimeMillis();
        getLogger().flow(Setup.class, "Database created successfully. Time: " + timeFormat(endTime - startTime));
    }

    private static String timeFormat(long time) {
        return new SimpleDateFormat("ss:S").format(new Date(time));
    }

    private static boolean deployDb(SqlType dbType, String deployPath) {
        String from = Paths.get("db", dbType.name().toLowerCase(), DbAdapterFactory.getDbName(dbType)).toString();
        try {
            FileUtils.copyURLToFile(ClassLoader.getSystemResource(from), new File(deployPath));
            return true;
        } catch (IOException e) {
            getLogger().error(Setup.class, e.getMessage());
            return false;
        }
    }
}
