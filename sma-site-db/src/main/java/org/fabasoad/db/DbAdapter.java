package org.fabasoad.db;

import org.fabasoad.log.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public abstract class DbAdapter {

    static String FOLDER_PATH_MAIN;
    private static String FOLDER_PATH_SQL;

    DbAdapter(SqlType type) {
        FOLDER_PATH_MAIN = String.format("db/%s/", type.getFolderName());
        FOLDER_PATH_SQL = FOLDER_PATH_MAIN + "sql/";
        initialize();
    }

    abstract String getUrl();

    private Connection connect() {
        Connection result = null;
        try {
            result = DriverManager.getConnection(getUrl());
            Logger.getInstance().flow(this.getClass(), "Database connected successfully");
        } catch (SQLException e) {
            Logger.getInstance().error(this.getClass(), e.getMessage());
        }
        return result;
    }

    private void initialize() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            final InputStream stream = ClassLoader.getSystemResourceAsStream(FOLDER_PATH_SQL + "init.sql");
            final String sqls = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining());
            for (String sql : sqls.split(";")) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            Logger.getInstance().error(this.getClass(), e.getMessage());
        } finally {
            Logger.getInstance().flow(this.getClass(), "Database connection closed");
        }
    }

    public void run(String sql, Consumer<ResultSet> callback) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            callback.accept(rs);
        } catch (SQLException e) {
            Logger.getInstance().error(this.getClass(), e.getMessage());
        } finally {
            Logger.getInstance().flow(this.getClass(), "Database connection closed");
        }
    }

    public void run(String sql) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            Logger.getInstance().error(this.getClass(), e.getMessage());
        } finally {
            Logger.getInstance().flow(this.getClass(), "Database connection closed");
        }
    }
}
