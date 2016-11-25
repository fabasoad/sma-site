package org.fabasoad.db;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class DbAdapterFactory {

    public static DbAdapter create(SqlType type) {
        if (type == SqlType.SQLITE) {
            return new SqliteDbAdapter(type);
        }
        throw new RuntimeException(String.format("DB type '%s' is not implemented yet", type.name()));
    }
}
