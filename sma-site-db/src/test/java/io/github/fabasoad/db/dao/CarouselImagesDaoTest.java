package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.pojo.CarouselImagesPojo;
import io.github.fabasoad.db.pojo.PojoProperties;

import java.util.EnumSet;

import static io.github.fabasoad.db.pojo.PojoProperties.CarouselImages.ID;
import static io.github.fabasoad.db.pojo.PojoProperties.CarouselImages.FILE_NAME;
import static io.github.fabasoad.db.pojo.PojoProperties.CarouselImages.TITLE;

public class CarouselImagesDaoTest extends BaseDaoTest<CarouselImagesPojo, PojoProperties.CarouselImages> {

    @Override
    CarouselImagesPojo createPojo() {
        CarouselImagesPojo pojo = new CarouselImagesPojo();
        pojo.setProperty(TITLE.DB, "Test carousel image title");
        pojo.setProperty(FILE_NAME.DB, "TestFileName.JPG");
        return pojo;
    }

    @Override
    Class<PojoProperties.CarouselImages> getEnumClazz() {
        return PojoProperties.CarouselImages.class;
    }

    @Override
    EnumSet<PojoProperties.CarouselImages> excludedEnumFields() {
        return EnumSet.of(ID, FILE_NAME);
    }

    @Override
    PojoProperties.CarouselImages getEnumId() {
        return ID;
    }

    @Override
    String getColumnForUpdate() {
        return TITLE.DB;
    }
}
