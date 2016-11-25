package org.fabasoad.db;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class Test {

    public static void main(String[] args) {
        DbAdapterFactory.create(SqlType.SQLITE);
    }
}
