package io.github.fabasoad.db.adapters;

import io.github.fabasoad.db.base.DbType;
import io.github.fabasoad.db.exceptions.FieldUniqueException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public abstract class DbAdapter {

    abstract void initialize(Properties properties);

    abstract void initialize(String[] args);

    abstract String getUrl();

    public abstract DbType getDbType();

    protected Connection connect() {
        Connection result = null;
        try {
            result = DriverManager.getConnection(getUrl());
            log.info("Database connected successfully.");
        } catch (SQLException e) {
            log.error("Failed to connect to the Database.", e);
        }
        return result;
    }

    abstract void deployDb();

    public void setUp() {
        deployDb();
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            final InputStream stream = getClass().getClassLoader().getResourceAsStream(
                Paths.get("db", getDbType().getDbTypeName(), "scripts", "init.sql").toString());
            if (stream == null) {
                log.error("Failed to read init.sql file.");
            } else {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                    final String sqls = reader.lines().collect(Collectors.joining());
                    for (String sql : sqls.split(";")) {
                        stmt.execute(sql);
                    }
                }
            }
        } catch (SQLException | IOException e) {
            log.error("Failed to set up Database.", e);
        } finally {
            log.debug("Database connection closed.");
        }
    }

    public void run(String sql, Consumer<ResultSet> callback) {
        log.debug("[SQL] {}", sql);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            callback.accept(rs);
        } catch (SQLException e) {
            log.error("Failed to run SQL query.", e);
            handleSQLException(e);
        } finally {
            log.debug("Database connection closed.");
        }
    }

    public void run(String sql, Object[] params, Consumer<ResultSet> callback) {
        log.debug("[SQL] {}", sql);
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            callback.accept(stmt.executeQuery());
        } catch (SQLException e) {
            log.error("Failed to run SQL query.", e);
            handleSQLException(e);
        } finally {
            log.debug("Database connection closed.");
        }
    }

    public void run(String sql, Object[] params) {
        log.debug("[SQL] {}", sql);
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.execute();
        } catch (SQLException e) {
            log.error("Failed to run SQL query.", e);
            handleSQLException(e);
        } finally {
            log.debug("Database connection closed.");
        }
    }

    public int runUpdate(String sql, Object[] params) {
        log.debug("[SQL] {}", sql);
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to run SQL query.", e);
            return -1;
        } finally {
            log.debug("Database connection closed.");
        }
    }

    public void run(String sql) {
        log.debug("[SQL] {}", sql);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            log.error("Failed to run SQL query.", e);
            handleSQLException(e);
        } finally {
            log.debug("Database connection closed.");
        }
    }

    public void runInsert(String sql, Object[] params, Consumer<Integer> callback) throws FieldUniqueException {
        log.debug("[SQL] {}", sql);
        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] == null) {
                    stmt.setString(i + 1, "");
                } else {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                log.warn("0 rows have been inserted");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    callback.accept(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Inserting rows is failed. No ID obtained");
                }
            }
        } catch (SQLException e) {
            log.error("Failed to run SQL query.", e);
            handleSQLException(e);
        } finally {
            log.debug("Database connection closed.");
        }
    }

    abstract void handleSQLException(SQLException e);
}
