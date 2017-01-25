package org.fabasoad.db.dao;

import org.apache.commons.lang3.StringUtils;
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
public class UsersDao extends BaseDao<UserPojo> {

    UsersDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
        Users.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validateBeforeCreate((String) value);
    }

    @Override
    void validateBeforeUpdate(Object id, String dbColumnName, Object value) throws ValidationException {
        Users.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validateBeforeUpdate(id, (String) value);
    }

    @Override
    void validateBeforeDelete(Object id) throws ValidationException {
        Users.ID.validateBeforeDelete(id);
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
        return new String[] { Users.ID.DB, Users.EMAIL.DB, Users.PASSWORD.DB, UserRoles.NAME.DB };
    }

    @Override
    String[] getColumnsForInsert() {
        return new String[] { Users.EMAIL.DB, Users.PASSWORD.DB };
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { Users.EMAIL.DB };
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

    @Override
    int postInsert(UserPojo obj) {
        final String sql = String.format("INSERT INTO %s SELECT ?, SUR_ID FROM SMA_USER_ROLES WHERE SUR_NAME = ?",
                UsersRolesRelations.TABLE_NAME);

        final Object[] params = {obj.getProperty(Users.ID.DB), obj.getProperty(UserRoles.NAME.DB)};
        adapter.runInsert(sql, params, i -> {});
        return (Integer) obj.getProperty(Users.ID.DB);
    }

    @Override
    void postUpdate(UserPojo obj) {
        final String sql = String.format("UPDATE %s SET %s = (SELECT %s FROM %s WHERE %s = ?) WHERE %s = ?",
                UsersRolesRelations.TABLE_NAME, UsersRolesRelations.ROLE_ID.DB, UserRoles.ID.DB, UserRoles.TABLE_NAME,
                UserRoles.NAME.DB, UsersRolesRelations.USER_ID.DB);
        adapter.run(sql, new Object[] { obj.getProperty(UserRoles.NAME.DB), obj.getProperty(Users.ID.DB) });
    }

    public void changePassword(
            final String email, final String oldPassword, final String newPassword, final String repeatedPassword)
            throws ValidationException {
        if (!StringUtils.equals(newPassword, repeatedPassword)) {
            throw new ValidationException("New password doesn't match to the repeated one");
        }

        final String sql = String.format("UPDATE %1$s SET %2$s = ? WHERE %3$s = ? AND ? = ? AND %2$s = ?",
                Users.TABLE_NAME, Users.PASSWORD.DB, Users.EMAIL.DB);
        final Object[] params = { newPassword, email, newPassword, repeatedPassword, oldPassword };
        int rows = adapter.runUpdate(sql, params);
        if (rows == 0) {
            throw new ValidationException("Email or password is incorrect");
        } else if (rows == -1) {
            throw new RuntimeException("There was a problem while changing the password. Please contact administrator");
        } else if (rows > 1) {
            throw new RuntimeException("More than 1 user has been changed. Please contact administrator");
        }
    }
}
