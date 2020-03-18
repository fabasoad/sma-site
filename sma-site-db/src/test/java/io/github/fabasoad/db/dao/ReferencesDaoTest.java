package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.pojo.PojoProperties;
import io.github.fabasoad.db.pojo.ReferencePojo;

import java.util.EnumSet;

import static io.github.fabasoad.db.pojo.PojoProperties.References.ID;
import static io.github.fabasoad.db.pojo.PojoProperties.References.FILE_NAME;
import static io.github.fabasoad.db.pojo.PojoProperties.References.TITLE;

public class ReferencesDaoTest extends BaseDaoTest<ReferencePojo, PojoProperties.References> {

    @Override
    ReferencePojo createPojo() {
        ReferencePojo pojo = new ReferencePojo();
        pojo.setProperty(TITLE.DB, "Test reference title");
        pojo.setProperty(FILE_NAME.DB, "TestReferenceFileName.JPG");
        return pojo;
    }

    @Override
    Class<PojoProperties.References> getEnumClazz() {
        return PojoProperties.References.class;
    }

    @Override
    EnumSet<PojoProperties.References> excludedEnumFields() {
        return EnumSet.of(ID, FILE_NAME);
    }

    @Override
    PojoProperties.References getEnumId() {
        return ID;
    }

    @Override
    String getColumnForUpdate() {
        return TITLE.DB;
    }
}
