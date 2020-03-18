package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.ApplicationFormPojo;

import static io.github.fabasoad.db.pojo.PojoProperties.ApplicationForms;

class ApplicationFormsDao extends BaseDao<ApplicationFormPojo> {

    ApplicationFormsDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
        ApplicationForms.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validateBeforeCreate((String) value);
    }

    @Override
    String getTableName() {
        return ApplicationForms.TABLE_NAME;
    }

    @Override
    String getIdColumn() {
        return ApplicationForms.ID.DB;
    }

    @Override
    String[] getColumns() {
        return new String[] { ApplicationForms.ID.DB, ApplicationForms.SENDER_NAME.DB, ApplicationForms.FILE_NAME.DB };
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { ApplicationForms.SENDER_NAME.DB };
    }
}
