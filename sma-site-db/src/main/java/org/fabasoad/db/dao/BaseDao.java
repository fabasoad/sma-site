package org.fabasoad.db.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.fabasoad.db.adapters.DbAdapter;
import org.fabasoad.db.exceptions.FieldUniqueException;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.BasePojo;
import org.fabasoad.db.pojo.PojoProperties;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public abstract class BaseDao<T extends BasePojo> {

    DbAdapter adapter;

    abstract String getTableName();

    abstract String getIdColumn();

    abstract String[] getColumns();

    String[] getColumnsForInsert() {
        return Stream.of(getColumns()).filter(c -> !Objects.equals(c, getIdColumn())).toArray(String[]::new);
    }

    abstract String[] getColumnsForUpdate();

    abstract void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException;

    void validateBeforeUpdate(Object id, String dbColumnName, Object value) throws ValidationException {
    }

    void validateBeforeDelete(Object id) throws ValidationException {
    }

    private Object[] getValuesForInsert(T obj) throws ValidationException {
        String[] columns = getColumnsForInsert();
        Object[] properties = new Object[columns.length];
        for (int i = 0; i < columns.length; i++) {
            validateBeforeCreate(columns[i], obj.getProperty(columns[i]));
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
        return sqlSelect("");
    }

    String sqlSelect(String whereClause) {
        return String.format("SELECT %s FROM %s %s ORDER BY %s %s", String.join(",", getColumns()),
                getTableName(), whereClause, getOrderByColumn().getLeft(), getOrderByColumn().getRight());
    }

    Pair<String, String> getOrderByColumn() {
        return Pair.of(getIdColumn(), "ASC");
    }

    public Collection<T> getAll() {
        final String sql = sqlSelect();
        return getElements(sql);
    }

    public int getLimit(String limit, Collection<T> elements) {
        final String sql = String.format("%s LIMIT %s", sqlSelect(), limit);
        elements.addAll(getElements(sql));
        return getCount();
    }

    private int getCount() {
        final String COLUMN_LABEL = "C";
        final String sql = String.format("SELECT COUNT(%s) AS %s FROM %s", getIdColumn(), COLUMN_LABEL, getTableName());
        final int[] result = { -1 };
        adapter.run(sql, rs -> {
            try {
                result[0] = rs.getInt(COLUMN_LABEL);
            } catch (Exception e) {
                getLogger().error(getClass(), e.getMessage());
            }
        });
        if (result[0] == -1) {
            getLogger().error(getClass(),
                    String.format("There is an error while retrieving count of %s elements", getPojoClass().getName()));
        }
        return result[0];
    }

    private Collection<T> getElements(String sql) {
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
        final String sql = sqlSelect(String.format("WHERE %s = ?", getIdColumn()));
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

    int postInsert(T obj) throws FieldUniqueException {
        return (Integer) obj.getProperty(PojoProperties.Users.ID.DB);
    }

    public int create(T obj) throws ValidationException {
        final Object[] params = getValuesForInsert(obj);
        final String sql = String.format(
                "INSERT INTO %s (%s) VALUES (%s)",
                getTableName(),
                String.join(",", getColumnsForInsert()),
                StringUtils.join(Collections.nCopies(params.length, "?"), ",")
        );
        try {
            adapter.runInsert(sql, params, arg -> obj.setProperty(PojoProperties.Users.ID.DB, arg));
            return postInsert(obj);
        } catch (FieldUniqueException e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }

    void postUpdate(T obj) {
    }

    public void update(T obj) throws ValidationException {
        String[] columns = getColumnsForUpdate();
        if (columns.length > 0) {
            Object[] params = new Object[columns.length + 1];
            for (int i = 0; i < columns.length; i++) {
                validateBeforeUpdate(obj.getProperty(getIdColumn()), columns[i], obj.getProperty(columns[i]));
                params[i] = obj.getProperty(columns[i]);
            }
            final String sql = Stream.of(columns)
                    .map(c -> c + " = ?")
                    .collect(Collectors.joining(
                            ",",
                            String.format("UPDATE %s SET ", getTableName()),
                            String.format(" WHERE %s = ?", getIdColumn())
                    ));
            params[params.length - 1] = obj.getProperty(getIdColumn());
            adapter.run(sql, params);
            postUpdate(obj);
        }
    }

    public <C> void delete(C id) throws ValidationException {
        validateBeforeDelete(id);
        final String sql = String.format("DELETE FROM %s WHERE %s = ?", getTableName(), getIdColumn());
        adapter.run(sql, new Object[] { id });
    }
}
