package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.UserRolePojo;

import static io.github.fabasoad.db.pojo.PojoProperties.UserRoles.TABLE_NAME;
import static io.github.fabasoad.db.pojo.PojoProperties.UserRoles.ID;
import static io.github.fabasoad.db.pojo.PojoProperties.UserRoles.NAME;

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
