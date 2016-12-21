package org.fabasoad.db.dao;

import org.fabasoad.db.pojo.BasePojo;
import org.junit.Test;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author efabizhevsky
 * @date 12/21/2016.
 */
abstract class BaseDaoTest<T extends BasePojo, E extends Enum<E>> {

    abstract T createPojo();

    abstract Class<E> getEnumClazz();

    abstract EnumSet<E> excludedEnumFields();

    abstract E getEnumId();

    abstract String getColumnForUpdate();

    @Test
    public void testCRUD() {
        T expected = createPojo();

        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) expected.getClass();
        final BaseDao<T> dao = DaoFactory.create(clazz);
        dao.create(expected);

        final Predicate<T> predicateMatch = p -> EnumSet.allOf(getEnumClazz()).stream()
                .filter(v -> !excludedEnumFields().contains(v))
                .allMatch(v -> {
                    String dbValue = getDbValue(v);
                    return Objects.equals(p.getProperty(dbValue), expected.getProperty(dbValue));
                });

        Optional<T> actual = dao.getAll().stream().filter(predicateMatch).findAny();
        assertTrue(actual.isPresent());

        String dbValue = getDbValue(getEnumId());
        assertNotNull(dao.get(actual.get().getProperty(dbValue)));

        String expectedUpdatedFieldValue = "some";
        expected.setProperty(getColumnForUpdate(), expectedUpdatedFieldValue);
        dao.update(expected);
        assertEquals(expectedUpdatedFieldValue, dao.get(actual.get().getProperty(dbValue)).getProperty(getColumnForUpdate()));

        dao.delete(actual.get().getProperty(dbValue));
        assertNull(dao.get(actual.get().getProperty(dbValue)));
    }

    private String getDbValue(E v) {
        try {
            return (String) getEnumClazz().getDeclaredField("DB").get(v);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            fail(e.getMessage());
            return null;
        }
    }
}
