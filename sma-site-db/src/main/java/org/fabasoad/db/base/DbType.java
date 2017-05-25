package org.fabasoad.db.base;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @author efabizhevsky
 * @date 5/24/2017.
 */
public abstract class DbType {

    public String getDbTypeName() {
        return this.getClass().getAnnotation(DbTypeInfo.class).name();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + Optional.ofNullable(getDbTypeName()).map(String::hashCode).orElse(0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DbType)) {
            return false;
        }

        DbType objDbType = (DbType) obj;

        return StringUtils.equals(getDbTypeName(), objDbType.getDbTypeName());
    }
}
