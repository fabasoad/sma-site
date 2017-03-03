package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.ParamPojo;
import org.fabasoad.db.pojo.PojoProperties;

import java.util.Arrays;

/**
 * @author efabizhevsky
 * @date 3/2/2017.
 */
class ParamsDao extends BaseDao<ParamPojo> {

    ParamsDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    String getTableName() {
        return PojoProperties.Params.TABLE_NAME;
    }

    @Override
    String getIdColumn() {
        return PojoProperties.Params.PROP_NAME.DB;
    }

    @Override
    String[] getColumns() {
        return Arrays.stream(PojoProperties.Params.values()).map(p -> p.DB).toArray(String[]::new);
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { PojoProperties.Params.PROP_VALUE.DB };
    }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
        PojoProperties.Params.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validateBeforeCreate((String) value);
    }
}
