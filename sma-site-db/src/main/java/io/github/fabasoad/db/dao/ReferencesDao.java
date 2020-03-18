package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.ReferencePojo;

import static io.github.fabasoad.db.pojo.PojoProperties.References;
import static io.github.fabasoad.db.pojo.PojoProperties.References.ID;
import static io.github.fabasoad.db.pojo.PojoProperties.References.TITLE;
import static io.github.fabasoad.db.pojo.PojoProperties.References.FILE_NAME;
import static io.github.fabasoad.db.pojo.PojoProperties.References.TABLE_NAME;

class ReferencesDao extends BaseDao<ReferencePojo> {

    ReferencesDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
        References.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validateBeforeCreate((String) value);
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
        return new String[] { ID.DB, TITLE.DB, FILE_NAME.DB };
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { TITLE.DB };
    }
}
