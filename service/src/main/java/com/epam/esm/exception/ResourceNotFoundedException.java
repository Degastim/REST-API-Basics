package com.epam.esm.exception;

/**
 * Custom ResourceNotFounded exception.
 *
 * @author Yauheni Tsitov
 */
public class ResourceNotFoundedException extends RuntimeException {
    private final int codeExceptionCause;

    /**
     * Construct a new ResourceNotFoundedException with message.
     *
     * @param message the message
     */
    public ResourceNotFoundedException(String message, int codeExceptionCause) {
        super(message);
        this.codeExceptionCause = codeExceptionCause;
    }

    /**
     * Return code exception cause.
     *
     * @return code exception cause.
     */
    public int getCodeExceptionCause() {
        return codeExceptionCause;
    }
}
