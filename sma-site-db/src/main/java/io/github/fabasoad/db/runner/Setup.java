package io.github.fabasoad.db.runner;

import io.github.fabasoad.db.ParametersAware;
import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.adapters.DbAdapterFactory;
import io.github.fabasoad.db.base.DbType;
import io.github.fabasoad.db.base.DbTypeFactory;
import io.github.fabasoad.db.dao.context.DaoContextTest;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Slf4j
public class Setup extends ParametersAware {

    public static void main(final String[] args) {
        long startTime = System.currentTimeMillis();
        log.info("Starting database creation...");
        readParameters(DaoContextTest.class);

        DbType dbType;
        DbAdapter dbAdapter;

        if (args.length == 0) {
            dbAdapter = DbAdapterFactory.create(properties);
            dbType = dbAdapter.getDbType();
        } else if (args.length > 0) {
            dbType = DbTypeFactory.getDbType(args[0]);

            String[] dbAdapterArgs = Arrays.stream(args, 1, args.length).toArray(String[]::new);
            dbAdapter = DbAdapterFactory.create(dbType, dbAdapterArgs);

            writeParameters(dbAdapter, DaoContextTest.class);
        } else {
            String message = "Arguments are missing";
            log.error(message);
            throw new RuntimeException(message);
        }
        log.info("Database type: {}.", dbType.getDbTypeName());
        dbAdapter.setUp();

        long endTime = System.currentTimeMillis();
        log.info("Database created successfully. Time: {}.", timeFormat(endTime - startTime));
    }

    private static String timeFormat(long time) {
        return new SimpleDateFormat("ss:S").format(new Date(time));
    }

}
