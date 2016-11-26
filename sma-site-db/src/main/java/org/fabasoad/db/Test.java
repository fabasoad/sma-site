package org.fabasoad.db;

import org.fabasoad.db.dao.BaseDao;
import org.fabasoad.db.dao.DaoFactory;
import org.fabasoad.db.dao.DaoType;
import org.fabasoad.db.pojo.BasePojo;
import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.ReferencePojo;

import static org.fabasoad.db.pojo.PojoProperties.References.ID;
import static org.fabasoad.db.pojo.PojoProperties.References.TITLE;
import static org.fabasoad.db.pojo.PojoProperties.References.FILE_NAME;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class Test {

    public static void main(String[] args) {
        BaseDao<ReferencePojo> dao = (BaseDao<ReferencePojo>) DaoFactory.create(DaoType.REFERENCES);
        //dao.create(createPojo());
        for (BasePojo pojo : dao.getAll()) {
            System.out.format(
                    "id: %s, title: %s, file-name: %s",
                    pojo.getProperty(ID.DB), pojo.getProperty(TITLE.DB), pojo.getProperty(FILE_NAME.DB));
            System.out.println();
        }
    }

    private static ReferencePojo createPojo() {
        ReferencePojo pojo = new ReferencePojo();
        pojo.setProperty(PojoProperties.References.TITLE.DB, "Title 02");
        pojo.setProperty(PojoProperties.References.FILE_NAME.DB, "file-name-02.png");
//        pojo.setTitle("Title 01");
//        pojo.setFileName("file-name-01.png");
        return pojo;
    }
}
