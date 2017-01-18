package org.fabasoad.db.dao;

import org.apache.commons.lang3.StringUtils;
import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.BasePojo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    String[] getColumnsForInsert() {
        return Stream.of(getColumns()).filter(c -> !Objects.equals(c, getIdColumn())).toArray(String[]::new);
    }

    abstract String[] getColumnsForUpdate();

    abstract void validate(String dbColumnName, Object value) throws ValidationException;

    private Object[] getValuesForInsert(T obj) throws ValidationException {
        String[] columns = getColumnsForInsert();
        Object[] properties = new Object[columns.length];
        for (int i = 0; i < columns.length; i++) {
            validate(columns[i], obj.getProperty(columns[i]));
            properties[i] = obj.getProperty(columns[i]);
        }
        return properties;
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
                "SELECT %s FROM %s WHERE %s = ?", String.join(",", getColumns()), getTableName(), getIdColumn());
        final Object[] result = new Object[1];
        adapter.run(sql, new Object[] { id }, rs -> {
            try {
                result[0] = buildObject(rs);
            } catch (Exception e) {
                getLogger().error(getClass(), e.getMessage());
            }
        });
        return (T) result[0];
    }

    public int create(T obj) throws ValidationException {
        final Object[] params = getValuesForInsert(obj);
        final String sql = String.format(
                "INSERT INTO %s (%s) VALUES (%s)",
                getTableName(),
                String.join(",", getColumnsForInsert()),
                StringUtils.join(Collections.nCopies(params.length, "?"), ",")
        );
        final int[] id = new int[] { -1 };
        adapter.runInsert(sql, params, arg -> id[0] = arg);
        return id[0];
    }

    public void update(T obj) {
        String[] columns = getColumnsForUpdate();
        if (columns.length > 0) {
            Object[] params = new Object[columns.length + 1];
            IntStream.range(0, columns.length).forEach(i -> params[i] = obj.getProperty(columns[i]));
            final String sql = Stream.of(columns)
                    .map(c -> c + " = ?")
                    .collect(Collectors.joining(
                            ",",
                            String.format("UPDATE %s SET ", getTableName()),
                            String.format(" WHERE %s = ?", getIdColumn())
                    ));
            params[params.length - 1] = obj.getProperty(getIdColumn());
            adapter.run(sql, params);
        }
    }

    public <C> void delete(C id) {
        final String sql = String.format("DELETE FROM %s WHERE %s = ?", getTableName(), getIdColumn());
        adapter.run(sql, new Object[] { id });
    }
}
