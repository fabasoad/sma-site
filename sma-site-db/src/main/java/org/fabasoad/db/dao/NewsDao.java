package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.NewsPojo;
import org.fabasoad.db.pojo.PojoProperties;

import static org.fabasoad.db.pojo.PojoProperties.News;
import static org.fabasoad.db.pojo.PojoProperties.News.ID;
import static org.fabasoad.db.pojo.PojoProperties.News.TITLE;
import static org.fabasoad.db.pojo.PojoProperties.News.BODY;
import static org.fabasoad.db.pojo.PojoProperties.News.CREATION_DATE;
import static org.fabasoad.db.pojo.PojoProperties.News.TABLE_NAME;

class NewsDao extends BaseDao<NewsPojo> {

    NewsDao(DbAdapter adapter) { super(adapter); }

    @Override
    void validate(String dbColumnName, Object value) throws ValidationException {
        News enumObject = News.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)));
        if (!enumObject.isValid((String) value)) {
            throw new ValidationException(String.format("Field '%s' is not valid", enumObject.DTO));
        }
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
        return new String[] { ID.DB, TITLE.DB, BODY.DB, CREATION_DATE.DB };
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { TITLE.DB, BODY.DB };
    }
}
