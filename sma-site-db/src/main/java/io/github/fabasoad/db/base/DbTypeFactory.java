package io.github.fabasoad.db.base;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class DbTypeFactory {

    private static DbType[] registrars = {
        new MySQLDbType(),
        new SQLiteDbType()
    };

    public static DbType getDbType(final String dbTypeName) {
        return Arrays.stream(registrars)
            .filter(r -> r.getClass().isAnnotationPresent(DbTypeInfo.class))
            .filter(r -> StringUtils.equals(dbTypeName, r.getClass().getAnnotation(DbTypeInfo.class).name()))
            .findAny()
            .orElseThrow(() -> new RuntimeException(String.format("Unknown '%s' db-type", dbTypeName)));
    }
}
