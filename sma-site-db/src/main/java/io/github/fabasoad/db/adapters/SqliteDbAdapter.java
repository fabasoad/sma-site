package io.github.fabasoad.db.adapters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import io.github.fabasoad.annotations.UsedViaReflection;
import io.github.fabasoad.db.base.DbType;
import io.github.fabasoad.db.base.DbTypeFactory;
import io.github.fabasoad.db.exceptions.FieldUniqueException;
import io.github.fabasoad.db.runner.ParameterName;
import org.sqlite.SQLiteException;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class SqliteDbAdapter extends DbAdapter {

    private String url;
    private static final int SQLITE_CONSTRAINT_UNIQUE_CODE = 2067;
    private static final String DB_FILE_NAME = "sma-db.s3db";
    private static final String CONNECTION_PATH_PARAM_NAME = "connection-path";

    private Path connectionPath;

    @UsedViaReflection
    @ParameterName(CONNECTION_PATH_PARAM_NAME)
    public String getConnectionPath() {
        return connectionPath.toString();
    }

    @Override
    void initialize(Properties properties) {
        this.connectionPath = Paths.get(properties.getProperty(CONNECTION_PATH_PARAM_NAME));
    }

    @Override
    void initialize(String[] args) {
        this.connectionPath = FileSystems.getDefault().getPath(args[0], DB_FILE_NAME).normalize().toAbsolutePath();
    }

    @Override
    void deployDb() {
        final String from = Paths.get("db", "sqlite", DB_FILE_NAME).toString();
        try {
            FileUtils.copyURLToFile(ClassLoader.getSystemResource(from), this.connectionPath.getFileName().toFile());
        } catch (IOException e) {
            log.error("Failed to deploy Database.", e);
        }
    }

    @Override
    @ParameterName(DbAdapterFactory.DB_TYPE_PARAM_NAME)
    public DbType getDbType() {
        return DbTypeFactory.getDbType("sqlite");
    }

    @Override
    String getUrl() {
        if (url == null) {
            url = "jdbc:sqlite:" + this.connectionPath.toString();
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
