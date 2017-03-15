package org.fabasoad.db.dao;

import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.ReferencePojo;

import java.util.EnumSet;

import static org.fabasoad.db.pojo.PojoProperties.References.ID;
import static org.fabasoad.db.pojo.PojoProperties.References.FILE_NAME;
import static org.fabasoad.db.pojo.PojoProperties.References.TITLE;

/**
 * @author efabizhevsky
 * @date 3/15/2017.
 */
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
