package org.fabasoad.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public abstract class DbAdapter {

    static Path DB_PATH;
    private static Path FOLDER_PATH_SQL;

    DbAdapter(String dbPath) {
        DB_PATH = Paths.get(dbPath);
        FOLDER_PATH_SQL = Paths.get("db", getType().getFolderName(), "sql");
    }

    abstract String getUrl();

    abstract SqlType getType();

    private Connection connect() {
        Connection result = null;
        try {
            result = DriverManager.getConnection(getUrl());
            getLogger().flow(this.getClass(), "Database connected successfully");
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
        }
        return result;
    }

    public void setUp() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
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

    public void run(String sql, Consumer<ResultSet> callback) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            callback.accept(rs);
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }

    public void run(String sql) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }
}
