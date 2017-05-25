package org.fabasoad.db.dao;

import org.fabasoad.db.adapters.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.UserRolePojo;

import static org.fabasoad.db.pojo.PojoProperties.UserRoles.TABLE_NAME;
import static org.fabasoad.db.pojo.PojoProperties.UserRoles.ID;
import static org.fabasoad.db.pojo.PojoProperties.UserRoles.NAME;

/**
 * @author efabizhevsky
 * @date 1/24/2017.
 */
class UserRolesDao extends BaseDao<UserRolePojo> {

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
}
