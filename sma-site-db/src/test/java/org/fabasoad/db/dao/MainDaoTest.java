package org.fabasoad.db.dao;

import org.fabasoad.db.pojo.MainPojo;

import static org.fabasoad.db.pojo.PojoProperties.Main.BODY_KEY;
import static org.fabasoad.db.pojo.PojoProperties.Main.PROP_NAME;
import static org.fabasoad.db.pojo.PojoProperties.Main.PROP_VALUE;

/**
 * @author efabizhevsky
 * @date 1/18/2017.
 */
public class MainDaoTest extends BaseParamsAwareDaoTest<MainPojo> {

    public MainDaoTest() {
        super(MainPojo.class, PROP_NAME.DB, PROP_VALUE.DB, BODY_KEY);
    }
}
