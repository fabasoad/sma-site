package org.fabasoad.db;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class DbAdapterFactory {

    public static DbAdapter create(SqlType type, String dbPath) {
        if (type == SqlType.SQLITE) {
            return new SqliteDbAdapter(dbPath);
        }
        throw new RuntimeException(String.format("Unknown '%s' DB type", type.name()));
    }

    public static String getDbName(SqlType type) {
        if (type == SqlType.SQLITE) {
            return SqliteDbAdapter.DB_NAME;
        }
        throw new RuntimeException(String.format("Unknown '%s' DB type", type.name()));
    }
}
