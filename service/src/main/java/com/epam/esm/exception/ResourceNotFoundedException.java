package com.epam.esm.exception;

/**
 * Custom ResourceNotFounded exception.
 *
 * @author Yauheni Tsitov
 */
public class ResourceNotFoundedException extends RuntimeException {
    /**
     * Construct a new ResourceNotFoundedException with message.
     *
     * @param message the message
     */
    public ResourceNotFoundedException(String message) {
        super(message);
    }
}
