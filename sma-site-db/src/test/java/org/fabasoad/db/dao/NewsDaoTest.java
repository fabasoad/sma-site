package org.fabasoad.db.dao;

import org.fabasoad.db.pojo.NewsPojo;
import org.fabasoad.db.pojo.PojoProperties;

import java.util.EnumSet;

import static org.fabasoad.db.pojo.PojoProperties.News.ID;
import static org.fabasoad.db.pojo.PojoProperties.News.BODY;
import static org.fabasoad.db.pojo.PojoProperties.News.CREATION_DATE;
import static org.fabasoad.db.pojo.PojoProperties.News.TITLE;

/**
 * @author efabizhevsky
 * @date 12/21/2016.
 */
public class NewsDaoTest extends BaseDaoTest<NewsPojo, PojoProperties.News> {

    @Override
    NewsPojo createPojo() {
        NewsPojo result = new NewsPojo();
        result.setProperty(TITLE.DB, "News test title");
        result.setProperty(BODY.DB, "Test Body");
        return result;
    }

    @Override
    Class<PojoProperties.News> getEnumClazz() {
        return PojoProperties.News.class;
    }

    @Override
    EnumSet<PojoProperties.News> excludedEnumFields() {
        return EnumSet.of(ID, CREATION_DATE);
    }

    @Override
    PojoProperties.News getEnumId() {
        return ID;
    }

    @Override
    String getColumnForUpdate() {
        return TITLE.DB;
    }
}
