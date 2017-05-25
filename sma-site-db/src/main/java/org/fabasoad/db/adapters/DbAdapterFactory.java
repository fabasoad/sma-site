package org.fabasoad.db.adapters;

import org.fabasoad.db.base.DbType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class DbAdapterFactory {

    private static Collection<DbAdapter> adapters = Arrays.asList(new MySqlDbAdapter(), new SqliteDbAdapter());

    public static DbAdapter create(DbType dbType, String connectionPath) {
        DbAdapter adapter = adapters.stream()
                .filter(a -> Objects.equals(a.getType(), dbType))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("Unknown '%s' DB type", dbType.getDbTypeName())));
        adapter.initialize(connectionPath);
        return adapter;
    }

    public static DbAdapter create(final DbType dbType, String[] args) {
        DbAdapter adapter = adapters.stream()
                .filter(a -> Objects.equals(a.getType(), dbType))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("Unknown '%s' DB type", dbType.getDbTypeName())));
        adapter.initialize(args);
        return adapter;
    }
}
