package org.fabasoad.db.dao;

import org.fabasoad.db.adapters.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.ReferencePojo;

import static org.fabasoad.db.pojo.PojoProperties.CarouselImages;
import static org.fabasoad.db.pojo.PojoProperties.CarouselImages.ID;
import static org.fabasoad.db.pojo.PojoProperties.CarouselImages.TITLE;
import static org.fabasoad.db.pojo.PojoProperties.CarouselImages.FILE_NAME;
import static org.fabasoad.db.pojo.PojoProperties.CarouselImages.TABLE_NAME;

/**
 * @author efabizhevsky
 * @date 03/10/2017.
 */
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
