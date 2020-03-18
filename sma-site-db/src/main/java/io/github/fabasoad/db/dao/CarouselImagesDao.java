package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.ReferencePojo;

import static io.github.fabasoad.db.pojo.PojoProperties.CarouselImages;
import static io.github.fabasoad.db.pojo.PojoProperties.CarouselImages.ID;
import static io.github.fabasoad.db.pojo.PojoProperties.CarouselImages.TITLE;
import static io.github.fabasoad.db.pojo.PojoProperties.CarouselImages.FILE_NAME;
import static io.github.fabasoad.db.pojo.PojoProperties.CarouselImages.TABLE_NAME;

class CarouselImagesDao extends BaseDao<ReferencePojo> {

    CarouselImagesDao(DbAdapter adapter) {
        super(adapter);
    }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
        CarouselImages.fromDb(dbColumnName)
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
