package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.BasePojo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public abstract class BaseDao<T extends BasePojo> {

    private DbAdapter adapter;

    abstract String getTableName();

    abstract String getIdColumn();

    abstract String[] getColumns();

    private String[] getColumnsForInsert() {
        return Stream.of(getColumns()).filter(c -> !Objects.equals(c, getIdColumn())).toArray(String[]::new);
    }

    private String getValuesForInsert(T obj) {
        return Stream.of(getColumnsForInsert())
                .map(obj::getProperty)
                .map(v -> String.format("'%s'", v))
                .collect(Collectors.joining(","));
    }

    @SuppressWarnings("unchecked")
    private Class<T> getPojoClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<T>) paramType.getActualTypeArguments()[0];
    }

    private T buildObject(ResultSet rs) throws Exception {
        T result = getPojoClass().newInstance();
        for (String column : getColumns()) {
            result.setProperty(column, rs.getObject(column));
        }
        return result;
    }

    BaseDao(DbAdapter adapter) {
        this.adapter = adapter;
    }

    String sqlSelect() {
        return String.format("SELECT %s FROM %s", String.join(",", getColumns()), getTableName());
    }

    public Collection<T> getAll() {
        final String sql = sqlSelect();
        Collection<T> result = new ArrayList<>();
        adapter.run(sql, rs -> {
            try {
                while (rs.next()) {
                    result.add(buildObject(rs));
                }
            } catch (Exception e) {
                getLogger().error(getClass(), e.getMessage());
            }
        });
        return result;
    }

    @SuppressWarnings("unchecked")
    public <C> T get(C id) {
        final String sql = String.format(
                "SELECT %s FROM %s WHERE %s = %s", String.join(",", getColumns()), getTableName(), getIdColumn(), id);
        final Object[] result = new Object[1];
        adapter.run(sql, rs -> {
            try {
                result[0] = buildObject(rs);
            } catch (Exception e) {
                getLogger().error(getClass(), e.getMessage());
            }
        });
        return (T) result[0];
    }

    public void create(T obj) {
        final String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                getTableName(), String.join(",", getColumnsForInsert()), getValuesForInsert(obj));
        adapter.run(sql);
    }

    public <C> void delete(C id) {
        final String sql = String.format("DELETE FROM %s WHERE %s = %s", getTableName(), getIdColumn(), id);
        adapter.run(sql);
    }
}
