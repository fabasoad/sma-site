package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.UserPojo;

import java.util.Collection;

import static org.fabasoad.db.pojo.PojoProperties.Users.ID;
import static org.fabasoad.db.pojo.PojoProperties.Users.EMAIL;
import static org.fabasoad.db.pojo.PojoProperties.Users.PASSWORD;
import static org.fabasoad.db.pojo.PojoProperties.Users.TABLE_NAME;

/**
 * @author Yevhen Fabizhevskyi
 * @date 04.12.2016.
 */
public class UsersDao extends BaseDao<UserPojo> {

    UsersDao(DbAdapter adapter) {
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
        return new String[] { ID.DB, EMAIL.DB, PASSWORD.DB, PojoProperties.SecuritySchemas.NAME.DB };
    }

    @Override
    String sqlSelect() {
        return String.format("SELECT %1$s FROM %2$s JOIN %3$s ON %2$s.%4$s = %3$s.%5$s",
                String.join(",", getColumns()),
                getTableName(),
                PojoProperties.SecuritySchemas.TABLE_NAME,
                PojoProperties.Users.SECURITY_SCHEMA_ID,
                PojoProperties.SecuritySchemas.ID.DB);
    }
}
