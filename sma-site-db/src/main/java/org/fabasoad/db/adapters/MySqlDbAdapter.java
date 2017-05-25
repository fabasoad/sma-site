package org.fabasoad.db.adapters;

import org.fabasoad.db.base.DbType;
import org.fabasoad.db.base.DbTypeFactory;
import org.fabasoad.db.runner.ParameterName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 5/24/2017.
 */
public class MySqlDbAdapter extends DbAdapter {

    private static final String DB_NAME = "SMA_DB";
    private static final String HOST_PARAM_NAME = "host";
    private static final String PORT_PARAM_NAME = "port";
    private static final String USER_PARAM_NAME = "user";
    private static final String PASSWORD_PARAM_NAME = "password";

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
            properties.getProperty(PORT_PARAM_NAME)
        });
    }

    @Override
    void initialize(String[] args) {
        host = args[0];
        port = args[1];
        user = args[2];
        password = args[3];
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
            getLogger().flow(this.getClass(), "Database connected successfully");
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
        }
        return result;
    }

    @Override
    void deployDb() {
        try (Connection conn = connectForDeploy(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE DATABASE " + DB_NAME);
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }

    @Override
    void handleSQLException(SQLException e) {
    }
}
