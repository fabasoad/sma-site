package org.fabasoad.db.adapters;

import org.fabasoad.db.base.DbType;
import org.fabasoad.db.base.DbTypeFactory;

import java.sql.SQLException;

/**
 * @author efabizhevsky
 * @date 5/24/2017.
 */
public class MySqlDbAdapter extends DbAdapter {

    @Override
    void initialize(String connectionPath) {
    }

    @Override
    void initialize(String[] args) {
    }

    @Override
    String getUrl() {
        return null;
    }

    @Override
    DbType getType() {
        return DbTypeFactory.getDbType("mysql");
    }

    @Override
    public void setUp() {

    }

    @Override
    void handleSQLException(SQLException e) {

    }
}
