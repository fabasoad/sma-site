package org.fabasoad.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
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

    private void initialize() {
        try (Connection conn = DriverManager.getConnection(getUrl()); Statement stmt = conn.createStatement()) {
            System.out.println("Database connected successfully");
            final InputStream stream = ClassLoader.getSystemResourceAsStream(FOLDER_PATH_SQL + "init.sql");
            final String sqls = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining());
            for (String sql : sqls.split(";")) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Database closed");
    }

    public void run(String sql, Consumer<ResultSet> callback) {
        try (Connection conn = DriverManager.getConnection(getUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            callback.accept(rs);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public <C> Optional<C> run(String sql, Function<ResultSet, C> callback) {
        try (Connection conn = DriverManager.getConnection(getUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return Optional.of(callback.apply(rs));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }
}
