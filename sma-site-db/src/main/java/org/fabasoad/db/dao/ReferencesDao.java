package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.ReferencePojo;

import static org.fabasoad.db.pojo.PojoProperties.References.ID;
import static org.fabasoad.db.pojo.PojoProperties.References.TITLE;
import static org.fabasoad.db.pojo.PojoProperties.References.FILE_NAME;
import static org.fabasoad.db.pojo.PojoProperties.References.TABLE_NAME;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
class ReferencesDao extends BaseDao<ReferencePojo> {

    ReferencesDao(DbAdapter adapter) {
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
        return new String[] { ID.DB, TITLE.DB, FILE_NAME.DB };
    }
}
