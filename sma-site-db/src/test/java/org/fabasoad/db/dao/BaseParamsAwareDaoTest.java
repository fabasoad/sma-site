package org.fabasoad.db.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.fabasoad.db.pojo.BasePojo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author efabizhevsky
 * @date 1/18/2017.
 */
abstract class BaseParamsAwareDaoTest<T extends BasePojo> {

    private BaseDao<T> dao;
    private Class<T> pojoClazz;
    private String propName;
    private String propValue;
    private String bodyKey;

    BaseParamsAwareDaoTest(Class<T> pojoClazz, String propName, String propValue, String bodyKey) {
        this.pojoClazz = pojoClazz;
        this.propName = propName;
        this.propValue = propValue;
        this.bodyKey = bodyKey;
    }

    @Before
    public void setUp() {
        dao = DaoFactory.create(pojoClazz);
    }

    @Test
    public void testSelect() {
        T pojo = dao.get(bodyKey);
        assertNotNull(pojo);
        assertEquals(bodyKey, pojo.getProperty(propName));
    }

    @Test
    public void testUpdate() throws IllegalAccessException, InstantiationException {
        Object initialValue = dao.get(bodyKey).getProperty(propValue);

        T pojo = this.pojoClazz.newInstance();
        pojo.setProperty(propName, bodyKey);
        pojo.setProperty(propValue, RandomStringUtils.randomAlphabetic(10));

        dao.update(pojo);
        T actual = dao.get(bodyKey);
        assertNotNull(actual);
        assertEquals(pojo.getProperty(propValue), actual.getProperty(propValue));
        assertNotEquals(initialValue, actual.getProperty(propValue));

        // return to initial state
        pojo.setProperty(propValue, initialValue);
        dao.update(pojo);
        T initial = dao.get(bodyKey);
        assertNotNull(initial);
        assertEquals(pojo.getProperty(propValue), initial.getProperty(propValue));
    }
}
