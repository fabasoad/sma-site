package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.MainPojo;

import static org.fabasoad.db.pojo.PojoProperties.Main;
import static org.fabasoad.db.pojo.PojoProperties.Main.PROP_NAME;
import static org.fabasoad.db.pojo.PojoProperties.Main.PROP_VALUE;
import static org.fabasoad.db.pojo.PojoProperties.Main.TABLE_NAME;

class MainDao extends BaseDao<MainPojo> {

    MainDao(DbAdapter adapter) { super(adapter); }

    @Override
    void validate(String dbColumnName, Object value) throws ValidationException {
        Main.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validate((String) value);
    }

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @Override
    String getIdColumn() {
        return PROP_NAME.DB;
    }

    @Override
    String[] getColumns() {
        return new String[] { PROP_NAME.DB, PROP_VALUE.DB };
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { PROP_VALUE.DB };
    }
}
