package org.fabasoad.db.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.fabasoad.db.exceptions.ValidationException;
import org.fabasoad.db.pojo.ParamPojo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.fabasoad.db.pojo.PojoProperties.Params.PROP_NAME;
import static org.fabasoad.db.pojo.PojoProperties.Params.PROP_VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author efabizhevsky
 * @date 1/18/2017.
 */
@RunWith(Parameterized.class)
public class ParamsDaoTest {

    private BaseDao<ParamPojo> dao;
    private String bodyKey;

    @Before
    public void setUp() {
        dao = DaoFactory.create(ParamPojo.class);
    }

    public ParamsDaoTest(String bodyKey) {
        this.bodyKey = bodyKey;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{"SMA_MAIN_BODY"},
                new Object[]{"SMA_CONTACTS_BODY"},
                new Object[]{"SMA_COMPANY_NAME"},
                new Object[]{"SMA_FOOTER_YEAR"}
        );
    }

    @Test
    public void testSelect() {
        ParamPojo pojo = dao.get(bodyKey);
        assertNotNull(pojo);
        assertEquals(bodyKey, pojo.getProperty(PROP_NAME.DB));
    }

    @Test
    public void testUpdate() throws IllegalAccessException, InstantiationException, ValidationException {
        Object initialValue = dao.get(bodyKey).getProperty(PROP_VALUE.DB);

        ParamPojo pojo = new ParamPojo();
        pojo.setProperty(PROP_NAME.DB, bodyKey);
        pojo.setProperty(PROP_VALUE.DB, RandomStringUtils.randomAlphabetic(10));

        dao.update(pojo);
        ParamPojo actual = dao.get(bodyKey);
        assertNotNull(actual);
        assertEquals(pojo.getProperty(PROP_VALUE.DB), actual.getProperty(PROP_VALUE.DB));
        assertNotEquals(initialValue, actual.getProperty(PROP_VALUE.DB));

        // return to initial state
        pojo.setProperty(PROP_VALUE.DB, initialValue);
        dao.update(pojo);
        ParamPojo initial = dao.get(bodyKey);
        assertNotNull(initial);
        assertEquals(pojo.getProperty(PROP_VALUE.DB), initial.getProperty(PROP_VALUE.DB));
    }
}
