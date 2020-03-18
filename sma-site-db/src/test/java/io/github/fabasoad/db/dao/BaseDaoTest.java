package io.github.fabasoad.db.dao;

import io.github.fabasoad.db.dao.context.DaoContextTest;
import io.github.fabasoad.db.exceptions.ValidationException;
import io.github.fabasoad.db.pojo.BasePojo;
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

abstract class BaseDaoTest<T extends BasePojo, E extends Enum<E>> {

    abstract T createPojo();

    abstract Class<E> getEnumClazz();

    abstract EnumSet<E> excludedEnumFields();

    abstract E getEnumId();

    abstract String getColumnForUpdate();

    @Test
    public void testCRUD() throws ValidationException {
        final T expected = createPojo();

        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) expected.getClass();
        final BaseDao<T> dao = DaoFactory.create(DaoContextTest.class, clazz);
        dao.create(expected);

        final Predicate<T> predicateMatch = p -> EnumSet.allOf(getEnumClazz()).stream()
                .filter(v -> !excludedEnumFields().contains(v))
                .map(this::getDbValue)
                .allMatch(v -> Objects.equals(p.getProperty(v), expected.getProperty(v)));

        Optional<T> actual = dao.getAll().stream().filter(predicateMatch).findAny();
        assertTrue(actual.isPresent());

        String idColumn = getDbValue(getEnumId());
        T obj = dao.get(actual.get().getProperty(idColumn));
        assertNotNull(obj);

        String expectedUpdatedFieldValue = "some";
        expected.setProperty(getColumnForUpdate(), expectedUpdatedFieldValue);
        expected.setProperty(idColumn, obj.getProperty(idColumn));
        dao.update(expected);
        assertEquals(expectedUpdatedFieldValue, dao.get(actual.get().getProperty(idColumn)).getProperty(getColumnForUpdate()));

        dao.delete(actual.get().getProperty(idColumn));
        assertNull(dao.get(actual.get().getProperty(idColumn)));
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
