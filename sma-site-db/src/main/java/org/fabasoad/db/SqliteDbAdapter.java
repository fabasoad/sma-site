package org.fabasoad.db;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
class SqliteDbAdapter extends DbAdapter {

    private static final String DB_NAME = "sma-db.s3db";

    private String url;

    @Override
    SqlType getType() {
        return SqlType.SQLITE;
    }

    @Override
    String getUrl() {
        if (url == null) {
            url = "jdbc:sqlite:" + ClassLoader.getSystemResource(FOLDER_PATH_MAIN + DB_NAME).getPath();
        }
        return url;
    }
}
