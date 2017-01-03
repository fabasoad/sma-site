package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.ApplicationFormPojo;
import org.fabasoad.db.pojo.PojoProperties;

import static org.fabasoad.db.pojo.PojoProperties.ApplicationForms;

/**
 * @author efabizhevsky
 * @date 12/6/2016.
 */
class ApplicationFormsDao extends BaseDao<ApplicationFormPojo> {

    ApplicationFormsDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    void validate(String dbColumnName, Object value) throws ValidationException {
        ApplicationForms enumObject = ApplicationForms.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)));
        if (!enumObject.isValid((String) value)) {
            throw new ValidationException(String.format("Field '%s' is not valid", enumObject.DTO));
        }
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
