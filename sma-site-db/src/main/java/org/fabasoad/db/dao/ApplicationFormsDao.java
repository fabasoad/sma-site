package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.ApplicationFormPojo;

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
    String getTableName() {
        return ApplicationForms.TABLE_NAME;
    }

    @Override
    String getIdColumn() {
        return ApplicationForms.ID.DB;
    }

    @Override
    String[] getColumns() {
        return new String[] { ApplicationForms.ID.DB, ApplicationForms.FILE_NAME.DB };
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { ApplicationForms.FILE_NAME.DB };
    }
}
