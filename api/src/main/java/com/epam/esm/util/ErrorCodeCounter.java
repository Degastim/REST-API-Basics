package com.epam.esm.util;

import org.springframework.http.HttpStatus;

/**
 * Counter error code for response exception entity
 *
 * @author Yauheni Tstiov
 */
public class ErrorCodeCounter {
    private ErrorCodeCounter() {
    }
    /**
     * Count error code for response exception entity
     *
     * @param httpStatus object which contain a http status
     * @param exceptionCauseCode object which contain an object code
     */
    public static int countErrorCode(HttpStatus httpStatus, int exceptionCauseCode) {
        return 100 * httpStatus.value() + exceptionCauseCode;
    }
}
