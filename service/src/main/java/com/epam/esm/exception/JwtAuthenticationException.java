package com.epam.esm.exception;

/**
 * Custom JwtAuthentication exception.
 *
 * @author Yauheni Tsitov
 */
public class JwtAuthenticationException extends RuntimeException {
    private final int codeExceptionCause;

    /**
     * Construct a new JwtAuthenticationException with message.
     *
     * @param message the message
     */
    public JwtAuthenticationException(String message, int codeExceptionCause) {
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
