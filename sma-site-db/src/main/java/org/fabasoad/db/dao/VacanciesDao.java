package org.fabasoad.db.dao;

import org.fabasoad.db.DbAdapter;
import org.fabasoad.db.pojo.VacanciesPojo;

import static org.fabasoad.db.pojo.PojoProperties.Vacancies.ID;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.RANK;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.VESSEL_TYPE;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.JOINING_DATE;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.CONTRACT_DURATION;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.NATIONALITY;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.WAGE;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.DESCRIPTION;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.TABLE_NAME;

class VacanciesDao extends BaseDao<VacanciesPojo> {

    VacanciesDao(DbAdapter adapter) { super(adapter); }

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @Override
    String getIdColumn() {
        return ID.DB;
    }

    @Override
    String[] getColumns() {
        return new String[] { ID.DB, RANK.DB, VESSEL_TYPE.DB, JOINING_DATE.DB,
                CONTRACT_DURATION.DB, NATIONALITY.DB, WAGE.DB, DESCRIPTION.DB };
    }
}
