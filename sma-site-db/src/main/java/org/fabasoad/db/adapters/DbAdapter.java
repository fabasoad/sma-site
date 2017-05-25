package org.fabasoad.db.adapters;

import org.fabasoad.db.base.DbType;
import org.fabasoad.db.exceptions.FieldUniqueException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public Path CONNECTION_PATH;

    abstract void initialize(String connectionPath);

    abstract void initialize(String[] args);

    abstract String getUrl();

    abstract DbType getType();

    protected Connection connect() {
        Connection result = null;
        try {
            result = DriverManager.getConnection(getUrl());
            getLogger().flow(this.getClass(), "Database connected successfully");
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
        }
        return result;
    }

    public abstract void setUp();

    public void run(String sql, Consumer<ResultSet> callback) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            callback.accept(rs);
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
            handleSQLException(e);
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }

    public void run(String sql, Object[] params, Consumer<ResultSet> callback) {
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            callback.accept(stmt.executeQuery());
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
            handleSQLException(e);
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }

    public void run(String sql, Object[] params) {
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.execute();
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
            handleSQLException(e);
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }

    public int runUpdate(String sql, Object[] params) {
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
            return -1;
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
            handleSQLException(e);
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }

    public void runInsert(String sql, Object[] params, Consumer<Integer> callback) throws FieldUniqueException {
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
                getLogger().error(this.getClass(), "0 rows have been inserted");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    callback.accept(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Inserting rows is failed. No ID obtained");
                }
            }
        } catch (SQLException e) {
            getLogger().error(this.getClass(), e.getMessage());
            handleSQLException(e);
        } finally {
            getLogger().flow(this.getClass(), "Database connection closed");
        }
    }

    abstract void handleSQLException(SQLException e);
}
