package org.fabasoad.db.adapters;

import org.fabasoad.db.base.DbType;
import org.fabasoad.db.base.DbTypeFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class DbAdapterFactory {

    final static String DB_TYPE_PARAM_NAME = "db-type";

    private static Collection<DbAdapter> adapters = Arrays.asList(new MySqlDbAdapter(), new SqliteDbAdapter());

    public static DbAdapter create(Properties properties) {
        DbType dbType = DbTypeFactory.getDbType(properties.getProperty(DB_TYPE_PARAM_NAME));
        DbAdapter adapter = adapters.stream()
                .filter(a -> Objects.equals(a.getDbType(), dbType))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("Unknown '%s' DB type", dbType.getDbTypeName())));
        adapter.initialize(properties);
        return adapter;
    }

    public static DbAdapter create(final DbType dbType, String[] args) {
        DbAdapter adapter = adapters.stream()
                .filter(a -> Objects.equals(a.getDbType(), dbType))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("Unknown '%s' DB type", dbType.getDbTypeName())));
        adapter.initialize(args);
        return adapter;
    }
}
