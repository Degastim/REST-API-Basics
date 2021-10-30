package com.epam.esm.exception;

/**
 * Custom InvalidURLParameterException exception.
 *
 * @author Yauheni Tsitov
 */
public class InvalidURLParameterException extends RuntimeException {
    private final int codeExceptionCause;

    /**
     * Construct a new InvalidFieldValueException with message.
     *
     * @param message the message
     */
    public InvalidURLParameterException(String message, int codeExceptionCause) {
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
