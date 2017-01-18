package org.fabasoad.db.dao;

import org.fabasoad.db.pojo.ContactsPojo;

import static org.fabasoad.db.pojo.PojoProperties.Contacts.BODY_KEY;
import static org.fabasoad.db.pojo.PojoProperties.Contacts.PROP_NAME;
import static org.fabasoad.db.pojo.PojoProperties.Contacts.PROP_VALUE;

/**
 * @author efabizhevsky
 * @date 12/29/2016.
 */
public class ContactsDaoTest extends BaseParamsAwareDaoTest<ContactsPojo> {

    public ContactsDaoTest() {
        super(ContactsPojo.class, PROP_NAME.DB, PROP_VALUE.DB, BODY_KEY);
    }
}
