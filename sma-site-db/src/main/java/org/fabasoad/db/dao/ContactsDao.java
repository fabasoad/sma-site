package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.ContactsPojo;

import static org.fabasoad.db.pojo.PojoProperties.Contacts;
import static org.fabasoad.db.pojo.PojoProperties.Contacts.PROP_NAME;
import static org.fabasoad.db.pojo.PojoProperties.Contacts.PROP_VALUE;
import static org.fabasoad.db.pojo.PojoProperties.Contacts.TABLE_NAME;

class ContactsDao extends BaseDao<ContactsPojo> {

    ContactsDao(DbAdapter adapter) { super(adapter); }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
        Contacts.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validateBeforeCreate((String) value);
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
