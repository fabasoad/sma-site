package org.fabasoad.db.dao;

import org.apache.commons.lang3.tuple.Pair;
import org.fabasoad.db.adapters.DbAdapter;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.NewsPojo;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.fabasoad.db.pojo.PojoProperties.News;
import static org.fabasoad.db.pojo.PojoProperties.News.ID;
import static org.fabasoad.db.pojo.PojoProperties.News.TITLE;
import static org.fabasoad.db.pojo.PojoProperties.News.BODY;
import static org.fabasoad.db.pojo.PojoProperties.News.CREATION_DATE;
import static org.fabasoad.db.pojo.PojoProperties.News.TABLE_NAME;

class NewsDao extends BaseDao<NewsPojo> {

    NewsDao(DbAdapter adapter) { super(adapter); }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
        News.fromDb(dbColumnName)
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
        return new String[] { ID.DB, TITLE.DB, BODY.DB, CREATION_DATE.DB };
    }

    String[] getColumnsForInsert() {
        return Stream.of(getColumns())
                .filter(c -> !Arrays.asList(getIdColumn(), CREATION_DATE.DB).contains(c))
                .toArray(String[]::new);
    }

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { TITLE.DB, BODY.DB };
    }

    @Override
    Pair<String, String> getOrderByColumn() {
        return Pair.of(CREATION_DATE.DB, "DESC");
    }
}
