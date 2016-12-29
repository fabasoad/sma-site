package org.fabasoad.db.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.fabasoad.db.pojo.ContactsPojo;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fabasoad.db.pojo.PojoProperties.Contacts.PROP_NAME;
import static org.fabasoad.db.pojo.PojoProperties.Contacts.PROP_VALUE;
import static org.fabasoad.db.pojo.PojoProperties.Contacts.BODY_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author efabizhevsky
 * @date 12/29/2016.
 */
public class ContactsDaoTest {

    private static BaseDao<ContactsPojo> dao;

    @BeforeClass
    public static void setUp() {
        dao = DaoFactory.create(ContactsPojo.class);
    }

    @Test
    public void testSelect() {
        ContactsPojo pojo = dao.get(BODY_KEY);
        assertNotNull(pojo);
        assertEquals(BODY_KEY, pojo.getProperty(PROP_NAME.DB));
    }

    @Test
    public void testUpdate() {
        Object initialValue = dao.get(BODY_KEY).getProperty(PROP_VALUE.DB);

        ContactsPojo pojo = new ContactsPojo();
        pojo.setProperty(PROP_NAME.DB, BODY_KEY);
        pojo.setProperty(PROP_VALUE.DB, RandomStringUtils.randomAlphabetic(10));

        dao.update(pojo);
        ContactsPojo actual = dao.get(BODY_KEY);
        assertNotNull(actual);
        assertEquals(pojo.getProperty(PROP_VALUE.DB), actual.getProperty(PROP_VALUE.DB));
        assertNotEquals(initialValue, actual.getProperty(PROP_VALUE.DB));

        // return to initial state
        pojo.setProperty(PROP_VALUE.DB, initialValue);
        dao.update(pojo);
        ContactsPojo initial = dao.get(BODY_KEY);
        assertNotNull(initial);
        assertEquals(pojo.getProperty(PROP_VALUE.DB), initial.getProperty(PROP_VALUE.DB));
    }
}
