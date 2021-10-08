package com.epam.esm.exception;

/**
 * Custom ResourceNotAdded exception.
 *
 * @author Yauheni Tsitov
 */
public class ResourceNotAddedException extends RuntimeException {
    /**
     * Construct a new ResourceNotAddedException with message.
     *
     * @param message the message
     */
    public ResourceNotAddedException(String message) {
        super(message);
    }
}
