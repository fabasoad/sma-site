package org.fabasoad.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(
                FOLDER_PATH_SQL + "init.sql"))).lines().collect(Collectors.joining());

        try (Connection conn = DriverManager.getConnection(getUrl()); Statement stmt = conn.createStatement()) {
            System.out.println("Database connected successfully");
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Database closed");
    }
}
