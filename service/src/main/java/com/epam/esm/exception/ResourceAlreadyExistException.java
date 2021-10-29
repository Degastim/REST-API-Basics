package com.epam.esm.exception;

/**
 * Custom ResourceAlreadyExist exception.
 *
 * @author Yauheni Tsitov
 */
public class ResourceAlreadyExistException extends RuntimeException {
    private final int codeExceptionCause;

    /**
     * Construct a new ResourceAlreadyExistException with message.
     *
     * @param message the message
     */
    public ResourceAlreadyExistException(String message, int codeExceptionCause) {
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
