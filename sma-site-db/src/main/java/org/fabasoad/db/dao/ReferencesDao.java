package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.ReferencePojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class ReferencesDao extends BaseDao<ReferencePojo> {

    public ReferencesDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    String getTableName() {
        return "SMA_REFERENCES";
    }

    @Override
    String getIdColumn() {
        return "SR_ID";
    }

    @Override
    String[] getColumns() {
        return new String[] { "SR_ID", "SR_TITLE", "SR_FILE_NAME" };
    }
}
