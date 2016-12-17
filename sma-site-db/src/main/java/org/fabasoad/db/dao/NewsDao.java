package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.NewsPojo;

import static org.fabasoad.db.pojo.PojoProperties.News.ID;
import static org.fabasoad.db.pojo.PojoProperties.News.TITLE;
import static org.fabasoad.db.pojo.PojoProperties.News.BODY;
import static org.fabasoad.db.pojo.PojoProperties.News.CREATION_DATE;
import static org.fabasoad.db.pojo.PojoProperties.News.TABLE_NAME;

public class NewsDao extends BaseDao<NewsPojo> {

    NewsDao(DbAdapter adapter) { super(adapter); }

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
}
