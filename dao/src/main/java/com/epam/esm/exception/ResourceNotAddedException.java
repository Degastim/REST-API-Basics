package com.epam.esm.exception;

/**
 * Custom ResourceNotAdded exception.
 *
 * @author Yauheni Tsitov
 */
public class ResourceNotAddedException extends RuntimeException {
    private final int codeExceptionCause;

    /**
     * Construct a new ResourceNotAddedException with message.
     *
     * @param message the message
     */
    public ResourceNotAddedException(String message, int codeExceptionCause) {
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
