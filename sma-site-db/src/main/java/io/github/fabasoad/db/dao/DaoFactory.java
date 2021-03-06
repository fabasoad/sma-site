package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.adapters.DbAdapterFactory;
import io.github.fabasoad.db.ParametersAware;
import io.github.fabasoad.db.dao.context.DaoContext;
import io.github.fabasoad.db.pojo.*;

public class DaoFactory extends ParametersAware {

    @SuppressWarnings("unchecked")
    public static <TPojo extends BasePojo, TContext extends DaoContext> BaseDao<TPojo> create(
            Class<TContext> contextClazz, Class<TPojo> pojoClazz) {
        readParameters(contextClazz);

        DbAdapter adapter = DbAdapterFactory.create(properties);
        if (pojoClazz == ReferencePojo.class) {
            return (BaseDao<TPojo>) new ReferencesDao(adapter);
        } else if (pojoClazz == UserPojo.class) {
            return (BaseDao<TPojo>) new UsersDao(adapter);
        } else if (pojoClazz == ApplicationFormPojo.class) {
            return (BaseDao<TPojo>) new ApplicationFormsDao(adapter);
        } else if (pojoClazz == NewsPojo.class) {
            return (BaseDao<TPojo>) new NewsDao(adapter);
        } else if (pojoClazz == VacanciesPojo.class) {
        return (BaseDao<TPojo>) new VacanciesDao(adapter);
        } else if (pojoClazz == ParamPojo.class) {
            return (BaseDao<TPojo>) new ParamsDao(adapter);
        } else if (pojoClazz == UserRolePojo.class) {
            return (BaseDao<TPojo>) new UserRolesDao(adapter);
        } else if (pojoClazz == CarouselImagesPojo.class) {
            return (BaseDao<TPojo>) new CarouselImagesDao(adapter);
        } else {
            throw new RuntimeException(String.format("Unknown type '%s'", pojoClazz.getSimpleName()));
        }
    }
}
