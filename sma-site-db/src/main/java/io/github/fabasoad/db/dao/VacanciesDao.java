package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.adapters.DbAdapter;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.VacanciesPojo;

import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.ID;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.RANK;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.VESSEL_TYPE;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.JOINING_DATE;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.CONTRACT_DURATION;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.NATIONALITY;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.WAGE;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.DESCRIPTION;
import static io.github.fabasoad.db.pojo.PojoProperties.Vacancies.TABLE_NAME;

class VacanciesDao extends BaseDao<VacanciesPojo> {

    VacanciesDao(DbAdapter adapter) { super(adapter); }

    @Override
    void validateBeforeCreate(String dbColumnName, Object value) throws ValidationException {
        Vacancies.fromDb(dbColumnName)
                .orElseThrow(() -> new ValidationException(String.format("Unknown column with name '%s'", dbColumnName)))
                .validateBeforeCreate((String) value);
    }

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

    @Override
    String[] getColumnsForUpdate() {
        return new String[] { RANK.DB, VESSEL_TYPE.DB, JOINING_DATE.DB,
                CONTRACT_DURATION.DB, NATIONALITY.DB, WAGE.DB, DESCRIPTION.DB };
    }
}
