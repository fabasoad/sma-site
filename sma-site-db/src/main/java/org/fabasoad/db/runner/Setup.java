package org.fabasoad.db.runner;

import org.apache.commons.lang3.StringUtils;
import org.fabasoad.db.ParametersAware;
import org.fabasoad.db.adapters.DbAdapter;
import org.fabasoad.db.adapters.DbAdapterFactory;
import org.fabasoad.db.base.DbType;
import org.fabasoad.db.base.DbTypeFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class Setup extends ParametersAware {

    public static void main(final String[] args) {
        long startTime = System.currentTimeMillis();
        getLogger().flow(Setup.class, "Starting database creation...");
        readParameters();

        DbType dbType;
        String connectionPath;
        DbAdapter dbAdapter;

        if (args.length == 0) {
            dbType = DbTypeFactory.getDbType(properties.getProperty(DB_TYPE_PARAM_NAME));
            connectionPath = properties.getProperty(CONNECTION_PATH_PARAM_NAME);
            dbAdapter = DbAdapterFactory.create(dbType, connectionPath);
        } else if (args.length > 0) {
            dbType = DbTypeFactory.getDbType(args[0]);

            String[] dbAdapterArgs = IntStream.range(1, args.length).mapToObj(i -> args[i]).toArray(String[]::new);
            dbAdapter = DbAdapterFactory.create(dbType, dbAdapterArgs);
            connectionPath = dbAdapter.getConnectionPath().toString();

            if (!StringUtils.equalsIgnoreCase(properties.getProperty(DB_TYPE_PARAM_NAME), dbType.getDbTypeName())
                    || !StringUtils.equalsIgnoreCase(properties.getProperty(CONNECTION_PATH_PARAM_NAME), connectionPath)) {
                writeParameters(dbType.getDbTypeName().toLowerCase(), connectionPath);
            }
        } else {
            String message = "Arguments are missing";
            getLogger().error(Setup.class, message);
            throw new RuntimeException(message);
        }
        getLogger().flow(Setup.class, "Database type: " + dbType.getDbTypeName());
        getLogger().flow(Setup.class, "Connection path: " + connectionPath);

        if (StringUtils.isEmpty(connectionPath)) {
            getLogger().error(Setup.class, "Connection path is empty");
        } else {
            dbAdapter.setUp();
        }
        long endTime = System.currentTimeMillis();
        getLogger().flow(Setup.class, "Database created successfully. Time: " + timeFormat(endTime - startTime));
    }

    private static String timeFormat(long time) {
        return new SimpleDateFormat("ss:S").format(new Date(time));
    }

}
