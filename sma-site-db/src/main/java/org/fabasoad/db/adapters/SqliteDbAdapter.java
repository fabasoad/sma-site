package org.fabasoad.db.adapters;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.fabasoad.db.base.DbType;
import org.fabasoad.db.base.DbTypeFactory;
import org.fabasoad.db.exceptions.FieldUniqueException;
import org.sqlite.SQLiteException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
class SqliteDbAdapter extends DbAdapter {

    private String url;
    private static final int SQLITE_CONSTRAINT_UNIQUE_CODE = 2067;
    private static final String DB_FILE_NAME = "sma-db.s3db";

    @Override
    void initialize(String connectionPath) {
        initialize(Paths.get(connectionPath));
    }

    @Override
    void initialize(String[] args) {
        initialize(FileSystems.getDefault().getPath(args[0], DB_FILE_NAME).normalize().toAbsolutePath());
    }

    private void initialize(Path connectionPath) {
        CONNECTION_PATH = connectionPath;
        final String from = Paths.get("db", "sqlite", DB_FILE_NAME).toString();
        try {
            FileUtils.copyURLToFile(ClassLoader.getSystemResource(from), CONNECTION_PATH.getFileName().toFile());
        } catch (IOException e) {
            getLogger().error(this.getClass(), e.getMessage());
        }
    }

    @Override
    DbType getType() {
        return DbTypeFactory.getDbType("sqlite");
    }

    @Override
    String getUrl() {
        if (url == null) {
            url = "jdbc:sqlite:" + CONNECTION_PATH.toString();
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

    @Override
    public void setUp() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            final Path FOLDER_PATH_SQL = Paths.get("db", getType().getDbTypeName(), "scripts");
            final InputStream stream = ClassLoader.getSystemResourceAsStream(Paths.get(FOLDER_PATH_SQL.toString(), "init.sql").toString());
            final String sqls = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining());
            for (String sql : sqls.split(";")) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }
}
