package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.ParamPojo;
import io.github.fabasoad.db.pojo.PojoProperties;

import java.util.Arrays;

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
