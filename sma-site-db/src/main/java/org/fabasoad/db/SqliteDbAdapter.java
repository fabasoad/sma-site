package org.fabasoad.db;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
class SqliteDbAdapter extends DbAdapter {

    public static final String DB_NAME = "sma-db.s3db";

    private String url;

    SqliteDbAdapter(String dbPath) {
        super(dbPath);
    }

    @Override
    SqlType getType() {
        return SqlType.SQLITE;
    }

    @Override
    String getUrl() {
        if (url == null) {
            url = "jdbc:sqlite:" + DB_PATH;
        }
        return url;
    }
}
