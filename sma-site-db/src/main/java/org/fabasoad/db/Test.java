package org.fabasoad.db;

import org.fabasoad.db.dao.ReferencesDao;
import org.fabasoad.db.pojo.ReferencePojo;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class Test {

    public static void main(String[] args) {
        DbAdapter adapter = DbAdapterFactory.create(SqlType.SQLITE);
        ReferencesDao dao = new ReferencesDao(adapter);
        //dao.create(createPojo());
        for (ReferencePojo pojo : dao.getAll()) {
            System.out.println(pojo.getId());
        }
    }

    private static ReferencePojo createPojo() {
        ReferencePojo pojo = new ReferencePojo();
        pojo.setTitle("Title 01");
        pojo.setFileName("file-name-01.png");
        return pojo;
    }
}
