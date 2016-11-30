package org.fabasoad.db;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public enum SqlType {
    SQLITE;

    public String getFolderName() {
        return name().toLowerCase();
    }

    public static SqlType valueOfIgnoreCase(String value) {
        return SqlType.valueOf(value.toUpperCase());
    }
}
