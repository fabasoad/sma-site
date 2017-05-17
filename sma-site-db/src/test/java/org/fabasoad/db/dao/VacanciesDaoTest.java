package org.fabasoad.db.dao;

import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.VacanciesPojo;

import java.util.EnumSet;

import static org.fabasoad.db.pojo.PojoProperties.Vacancies.ID;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.CONTRACT_DURATION;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.DESCRIPTION;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.JOINING_DATE;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.NATIONALITY;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.RANK;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.VESSEL_TYPE;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.WAGE;

/**
 * @author efabizhevsky
 * @date 12/19/2016.
 */
public class VacanciesDaoTest extends BaseDaoTest<VacanciesPojo, PojoProperties.Vacancies> {

    @Override
    VacanciesPojo createPojo() {
        VacanciesPojo pojo = new VacanciesPojo();
        pojo.setProperty(CONTRACT_DURATION.DB, "3 months");
        pojo.setProperty(DESCRIPTION.DB, "Good position");
        pojo.setProperty(JOINING_DATE.DB, "2090-05-05");
        pojo.setProperty(NATIONALITY.DB, "Ukraine");
        pojo.setProperty(RANK.DB, "Chief Officer");
        pojo.setProperty(VESSEL_TYPE.DB, "OSV");
        pojo.setProperty(WAGE.DB, "$250/daily");
        return pojo;
    }

    @Override
    Class<PojoProperties.Vacancies> getEnumClazz() {
        return PojoProperties.Vacancies.class;
    }

    @Override
    EnumSet<PojoProperties.Vacancies> excludedEnumFields() {
        return EnumSet.of(ID);
    }

    @Override
    PojoProperties.Vacancies getEnumId() {
        return ID;
    }

    @Override
    String getColumnForUpdate() {
        return VESSEL_TYPE.DB;
    }
}
