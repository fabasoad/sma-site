package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.ReferencePojo;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Override
    ReferencePojo buildObject(ResultSet rs) throws SQLException {
        return new ReferencePojo(
            rs.getInt(getColumns()[0]),
            rs.getString(getColumns()[1]),
            rs.getString(getColumns()[2])
        );
    }
}
