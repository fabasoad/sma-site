package org.fabasoad.db;

import org.fabasoad.db.exceptions.FieldUniqueException;
import org.sqlite.SQLiteException;

import java.sql.SQLException;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
class SqliteDbAdapter extends DbAdapter {

    static final String DB_NAME = "sma-db.s3db";

    private String url;
    private static final int SQLITE_CONSTRAINT_UNIQUE_CODE = 2067;

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

    @Override
    void handleSQLException(SQLException e) {
        SQLiteException exception = (SQLiteException) e;
        if (SQLITE_CONSTRAINT_UNIQUE_CODE == exception.getResultCode().code) {
            throw new FieldUniqueException();
        }
    }
}
