package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.DbAdapterFactory;
import org.fabasoad.db.ParametersAware;
import org.fabasoad.db.SqlType;
import org.fabasoad.db.pojo.*;

/**
 * @author Yevhen Fabizhevskyi
 * @date 26.12.2016.
 */
public class DaoFactory extends ParametersAware {

    @SuppressWarnings("unchecked")
    public static <T extends BasePojo> BaseDao<T> create(Class<T> pojoClazz) {
        readParameters();

        DbAdapter adapter = DbAdapterFactory.create(SqlType.SQLITE, properties.getProperty(DEPLOY_PATH_PARAM_NAME));
        if (pojoClazz == ReferencePojo.class) {
            return (BaseDao<T>) new ReferencesDao(adapter);
        } else if (pojoClazz == UserPojo.class) {
            return (BaseDao<T>) new UsersDao(adapter);
        } else if (pojoClazz == ApplicationFormPojo.class) {
            return (BaseDao<T>) new ApplicationFormsDao(adapter);
        } else if (pojoClazz == NewsPojo.class) {
            return (BaseDao<T>) new NewsDao(adapter);
        } else if (pojoClazz == VacanciesPojo.class) {
        return (BaseDao<T>) new VacanciesDao(adapter);
        } else if (pojoClazz == ContactsPojo.class) {
            return (BaseDao<T>) new ContactsDao(adapter);
        } else {
            throw new RuntimeException(String.format("Unknown type '%s'", pojoClazz.getSimpleName()));
        }
    }
}
