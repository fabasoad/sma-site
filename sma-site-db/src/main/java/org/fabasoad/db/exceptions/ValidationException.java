package org.fabasoad.db.exceptions;

/**
 * @author Yevhen Fabizhevskyi
 * @date 03.01.2017.
 */
public class ValidationException extends Exception {

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message) {
        super(message);
    }
}
