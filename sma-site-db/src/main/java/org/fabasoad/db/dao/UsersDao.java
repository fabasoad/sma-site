package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.UserPojo;

import static org.fabasoad.db.pojo.PojoProperties.Users;
import static org.fabasoad.db.pojo.PojoProperties.UserRoles;
import static org.fabasoad.db.pojo.PojoProperties.UsersRolesRelations;

/**
 * @author Yevhen Fabizhevskyi
 * @date 04.12.2016.
 */
class UsersDao extends BaseDao<UserPojo> {

    UsersDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    void validate(String dbColumnName, Object value) throws ValidationException {
        Users.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validate((String) value);
    }

    @Override
    String getTableName() {
        return Users.TABLE_NAME;
    }

    @Override
    String getIdColumn() {
        return Users.ID.DB;
    }

    @Override
    String[] getColumns() {
        return new String[] {
                Users.ID.DB, Users.EMAIL.DB, Users.PASSWORD.DB, UserRoles.NAME.DB
        };
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[0];
    }

    @Override
    String sqlSelect() {
        /*
        * SELECT SU_ID, SU_EMAIL, SU_PASSWORD, SUR_NAME
        * FROM SMA_USERS
        * JOIN SMA_USERS_ROLES_RELATIONS ON SMA_USERS.SU_ID = SMA_USERS_ROLES_RELATIONS.SURR_USER_ID
        * JOIN SMA_USER_ROLES ON SMA_USERS_ROLES_RELATIONS.SURR_ROLE_ID = SMA_USER_ROLES.SUR_ID;
        * */
        return String.format("SELECT %1$s FROM %2$s " +
                        "JOIN %3$s ON %2$s.%4$s = %3$s.%5$s " +
                        "JOIN %6$s ON %3$s.%7$s = %6$s.%8$s",
                String.join(",", getColumns()),
                getTableName(),
                UsersRolesRelations.TABLE_NAME,
                getIdColumn(),
                UsersRolesRelations.USER_ID.DB,
                UserRoles.TABLE_NAME,
                UsersRolesRelations.ROLE_ID.DB,
                UserRoles.ID.DB);
    }
}
