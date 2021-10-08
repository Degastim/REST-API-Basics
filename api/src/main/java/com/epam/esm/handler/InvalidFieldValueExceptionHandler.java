package com.epam.esm.handler;

import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.entity.ResponseExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The InvalidFieldValueException handler.
 *
 * @author Yauheni Tstiov
 */
@ControllerAdvice
public class InvalidFieldValueExceptionHandler {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private static final int ERROR_CODE = 40001;

    /**
     * Handle InvalidFieldValueException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(InvalidFieldValueException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleRuntimeExceptions(InvalidFieldValueException e) {
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(e.getLocalizedMessage(), ERROR_CODE);
        return ResponseEntity.status(status).body(exceptionResponseBody);
    }
}