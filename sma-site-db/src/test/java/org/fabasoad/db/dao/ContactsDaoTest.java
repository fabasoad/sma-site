package org.fabasoad.db.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.fabasoad.db.pojo.ContactsPojo;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fabasoad.db.pojo.PojoProperties.Contacts.PROP_NAME;
import static org.fabasoad.db.pojo.PojoProperties.Contacts.PROP_VALUE;
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
        ContactsPojo pojo = dao.get("SMA_CONTACTS_BODY");
        assertNotNull(pojo);
        assertEquals("SMA_CONTACTS_BODY", pojo.getProperty(PROP_NAME.DB));
    }

    @Test
    public void testUpdate() {
        Object oldValue = dao.get("SMA_CONTACTS_BODY").getProperty(PROP_VALUE.DB);

        ContactsPojo pojo = new ContactsPojo();
        pojo.setProperty(PROP_NAME.DB, "SMA_CONTACTS_BODY");
        pojo.setProperty(PROP_VALUE.DB, RandomStringUtils.randomAlphabetic(10));

        dao.update(pojo);
        ContactsPojo actual = dao.get("SMA_CONTACTS_BODY");
        assertNotNull(actual);
        assertEquals(pojo.getProperty(PROP_VALUE.DB), actual.getProperty(PROP_VALUE.DB));
        assertNotEquals(oldValue, actual.getProperty(PROP_VALUE.DB));
    }
}
