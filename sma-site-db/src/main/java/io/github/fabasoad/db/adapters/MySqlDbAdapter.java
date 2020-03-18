package io.github.fabasoad.db.adapters;

import io.github.fabasoad.db.base.DbType;
import io.github.fabasoad.db.base.DbTypeFactory;
import io.github.fabasoad.db.runner.ParameterName;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Slf4j
public class MySqlDbAdapter extends DbAdapter {

    private static final String DB_NAME = "SMA_DB";
    private static final String HOST_PARAM_NAME = "host";
    private static final String PORT_PARAM_NAME = "port";
    private static final String USER_PARAM_NAME = "user";
    private static final String PASSWORD_PARAM_NAME = "password";

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "";

    private String host;
    private String port;
    private String user;
    private String password;

    @ParameterName(HOST_PARAM_NAME)
    public String getHost() {
        return host;
    }

    @ParameterName(PORT_PARAM_NAME)
    public String getPort() {
        return port;
    }

    @ParameterName(USER_PARAM_NAME)
    public String getUser() {
        return user;
    }

    @ParameterName(PASSWORD_PARAM_NAME)
    public String getPassword() {
        return password;
    }

    @Override
    void initialize(Properties properties) {
        initialize(new String[] {
            properties.getProperty(HOST_PARAM_NAME),
            properties.getProperty(PORT_PARAM_NAME),
            properties.getProperty(USER_PARAM_NAME),
            properties.getProperty(PASSWORD_PARAM_NAME)
        });
    }

    @Override
    void initialize(String[] args) {
        host = args.length > 0 ? args[0] : DEFAULT_HOST;
        port = args.length > 1 ? args[1] : DEFAULT_PORT;
        user = args.length > 2 ? args[2] : DEFAULT_USER;
        password = args.length > 3 ? args[3] : DEFAULT_PASSWORD;
    }

    @Override
    String getUrl() {
        return getUrl(true);
    }

    private String getUrl(boolean withDbName) {
        if (withDbName) {
            return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s", host, port, DB_NAME, user, password);
        } else {
            return String.format("jdbc:mysql://%s:%s?user=%s&password=%s", host, port, user, password);
        }
    }

    @Override
    @ParameterName(DbAdapterFactory.DB_TYPE_PARAM_NAME)
    public DbType getDbType() {
        return DbTypeFactory.getDbType("mysql");
    }

    private Connection connectForDeploy() {
        Connection result = null;
        try {
            result = DriverManager.getConnection(getUrl(false));
            log.info("Database connected successfully.");
        } catch (SQLException e) {
            log.error("Failed to connect to the Database.", e);
        }
        return result;
    }

    @Override
    void deployDb() {
        try (Connection conn = connectForDeploy(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE DATABASE " + DB_NAME);
        } catch (SQLException e) {
            log.error("Failed to create {} Database.", DB_NAME, e);
        } finally {
            log.debug("Database connection closed.");
        }
    }

    @Override
    void handleSQLException(SQLException e) {
    }
}
