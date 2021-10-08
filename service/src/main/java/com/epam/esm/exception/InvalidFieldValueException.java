package com.epam.esm.exception;

/**
 * Custom InvalidFieldValue exception.
 *
 * @author Yauheni Tsitov
 */
public class InvalidFieldValueException extends RuntimeException {
    /**
     * Construct a new InvalidFieldValueException with message.
     *
     * @param message the message
     */
    public InvalidFieldValueException(String message) {
        super(message);
    }
}
