package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.UserRolePojo;

import static org.fabasoad.api.Logger.getLogger;
import static org.fabasoad.db.pojo.PojoProperties.UserRoles.TABLE_NAME;
import static org.fabasoad.db.pojo.PojoProperties.UserRoles.ID;
import static org.fabasoad.db.pojo.PojoProperties.UserRoles.NAME;

/**
 * @author efabizhevsky
 * @date 1/24/2017.
 */
public class UserRolesDao extends BaseDao<UserRolePojo> {

    UserRolesDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @Override
    String getIdColumn() {
        return ID.DB;
    }

    @Override
    String[] getColumns() {
        return new String[] { ID.DB, NAME.DB };
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[0];
    }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
    }

    public <T> UserRolePojo getByUserId(T id) {
        final String sql = String.format("%s JOIN %s ON %s = %s WHERE %s = ?",
                sqlSelect(),
                PojoProperties.UsersRolesRelations.TABLE_NAME,
                getIdColumn(),
                PojoProperties.UsersRolesRelations.ROLE_ID.DB,
                PojoProperties.UsersRolesRelations.USER_ID.DB);
        final UserRolePojo[] result = new UserRolePojo[1];
        adapter.run(sql, new Object[] { id }, rs -> {
            try {
                result[0] = buildObject(rs);
            } catch (Exception e) {
                getLogger().error(getClass(), e.getMessage());
            }
        });
        return result[0];
    }
}
