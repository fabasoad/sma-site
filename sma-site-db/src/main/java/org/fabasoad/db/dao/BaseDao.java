package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.BasePojo;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
abstract class BaseDao<T extends BasePojo> {

    private DbAdapter adapter;

    abstract String getTableName();

    abstract String getIdColumn();

    abstract String[] getColumns();

    private String[] getColumnsForInsert() {
        return Stream.of(getColumns()).filter(c -> !Objects.equals(c, getIdColumn())).toArray(String[]::new);
    }

    abstract T buildObject(ResultSet rs) throws SQLException;

    public BaseDao(DbAdapter adapter) {
        this.adapter = adapter;
    }

    public Collection<T> getAll() {
        final String sql = String.format("SELECT %s FROM %s", String.join(",", getColumns()), getTableName());
        Collection<T> result = new ArrayList<T>();
        adapter.run(sql, rs -> {
            try {
                while (rs.next()) {
                    result.add(buildObject(rs));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        });
        return result;
    }

    public <C> T get(C id) {
        final String sql = String.format(
                "SELECT %s FROM %s WHERE %s = %s", String.join(",", getColumns()), getTableName(), getIdColumn(), id);
        @SuppressWarnings("unchecked")
        final T[] result = (T[]) Array.newInstance(Object.class, 1);
        adapter.run(sql, rs -> {
            try {
                result[0] = buildObject(rs);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        });
        return result[0];
    }

    public void create(T obj) {
        final String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                getTableName(), String.join(",", getColumnsForInsert()), "");
        adapter.run(sql, rs -> {

        });
    }
}
