package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.DbAdapterFactory;
import org.fabasoad.db.SqlType;
import org.fabasoad.db.pojo.BasePojo;

/**
 * @author Yevhen Fabizhevskyi
 * @date 26.11.2016.
 */
public class DaoFactory {

    public static BaseDao<? extends BasePojo> create(DaoType type) {
        DbAdapter adapter = DbAdapterFactory.create(SqlType.SQLITE);
        if (type == DaoType.REFERENCES) {
            return new ReferencesDao(adapter);
        }
        throw new RuntimeException(String.format("Unknown type '%s'", type.name()));
    }
}
