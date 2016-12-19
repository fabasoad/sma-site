package org.fabasoad.db.dao;

import org.fabasoad.db.pojo.PojoProperties;
import org.fabasoad.db.pojo.VacanciesPojo;
import org.junit.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import static junit.framework.TestCase.assertNull;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.ID;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.CONTRACT_DURATION;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.DESCRIPTION;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.JOINING_DATE;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.NATIONALITY;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.RANK;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.VESSEL_TYPE;
import static org.fabasoad.db.pojo.PojoProperties.Vacancies.WAGE;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author efabizhevsky
 * @date 12/19/2016.
 */
public class VacanciesDaoTest {

    private VacanciesPojo createPojo() {
        VacanciesPojo pojo = new VacanciesPojo();
        pojo.setProperty(CONTRACT_DURATION.DB, "3 months");
        pojo.setProperty(DESCRIPTION.DB, "Good position");
        pojo.setProperty(JOINING_DATE.DB, "2017-05-05");
        pojo.setProperty(NATIONALITY.DB, "Ukraine");
        pojo.setProperty(RANK.DB, "Chief Officer");
        pojo.setProperty(VESSEL_TYPE.DB, "OSV");
        pojo.setProperty(WAGE.DB, "$250/daily");
        return pojo;
    }

    private static boolean match(VacanciesPojo pojo1, VacanciesPojo pojo2, PojoProperties.Vacancies property) {
        return Objects.equals(pojo1.getProperty(property.DB), pojo2.getProperty(property.DB));
    }

    @Test
    public void testCRUD() {
        final BaseDao<VacanciesPojo> dao = DaoFactory.create(VacanciesPojo.class);
        VacanciesPojo expected = createPojo();
        dao.create(expected);

        final Predicate<VacanciesPojo> predicateMatch = p -> match(p, expected, CONTRACT_DURATION)
                && match(p, expected, DESCRIPTION) && match(p, expected, JOINING_DATE) && match(p, expected, NATIONALITY)
                && match(p, expected, RANK) && match(p, expected, VESSEL_TYPE) && match(p, expected, WAGE);

        Optional<VacanciesPojo> actual = dao.getAll().stream().filter(predicateMatch).findAny();
        assertTrue(actual.isPresent());

        assertNotNull(dao.get(actual.get().getProperty(ID.DB)));

        dao.delete(actual.get().getProperty(ID.DB));
        assertNull(dao.get(actual.get().getProperty(ID.DB)));
    }
}
