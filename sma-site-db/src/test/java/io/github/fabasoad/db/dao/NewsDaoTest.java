package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.pojo.NewsPojo;
import io.github.fabasoad.db.pojo.PojoProperties;

import java.util.EnumSet;

import static io.github.fabasoad.db.pojo.PojoProperties.News.ID;
import static io.github.fabasoad.db.pojo.PojoProperties.News.BODY;
import static io.github.fabasoad.db.pojo.PojoProperties.News.CREATION_DATE;
import static io.github.fabasoad.db.pojo.PojoProperties.News.TITLE;

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
