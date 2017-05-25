package org.fabasoad.db.runner;

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
        DbAdapter dbAdapter;

        if (args.length == 0) {
            dbAdapter = DbAdapterFactory.create(properties);
            dbType = dbAdapter.getDbType();
        } else if (args.length > 0) {
            dbType = DbTypeFactory.getDbType(args[0]);

            String[] dbAdapterArgs = IntStream.range(1, args.length).mapToObj(i -> args[i]).toArray(String[]::new);
            dbAdapter = DbAdapterFactory.create(dbType, dbAdapterArgs);

            writeParameters(dbAdapter);
        } else {
            String message = "Arguments are missing";
            getLogger().error(Setup.class, message);
            throw new RuntimeException(message);
        }
        getLogger().flow(Setup.class, "Database type: " + dbType.getDbTypeName());
        dbAdapter.setUp();

        long endTime = System.currentTimeMillis();
        getLogger().flow(Setup.class, "Database created successfully. Time: " + timeFormat(endTime - startTime));
    }

    private static String timeFormat(long time) {
        return new SimpleDateFormat("ss:S").format(new Date(time));
    }

}
