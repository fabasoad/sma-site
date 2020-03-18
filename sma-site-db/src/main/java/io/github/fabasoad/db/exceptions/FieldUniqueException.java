package io.github.fabasoad.db.exceptions;

public class FieldUniqueException extends RuntimeException {

    public FieldUniqueException() {
        super("Such record already exists");
    }
}
