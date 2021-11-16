package com.epam.esm.exception;

/**
 * Custom AccessException exception.
 *
 * @author Yauheni Tsitov
 */
public class AccessException extends RuntimeException {
    private final int codeExceptionCause;

    /**
     * Construct a new ResourceNotAddedException with message.
     *
     * @param message the message
     */
    public AccessException(String message, int codeExceptionCause) {
        super(message);
        this.codeExceptionCause = codeExceptionCause;
    }

    /**
     * Get code exceotion cause
     *
     * @return int code
     */
    public int getCodeExceptionCause() {
        return codeExceptionCause;
    }
}
