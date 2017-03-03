package org.fabasoad.db.exceptions;

/**
 * @author efabizhevsky
 * @date 3/3/2017.
 */
public class FieldUniqueException extends RuntimeException {

    public FieldUniqueException() {
        super("Such record already exists");
    }
}
