package com.epam.esm.handler;

import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.entity.ResponseExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The ResourceNotFoundedException handler.
 *
 * @author Yauheni Tstiov
 */
@ControllerAdvice
public class ResourceNotFoundedExceptionHandler {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    private static final int ERROR_CODE = 40401;

    /**
     * Handle InvalidFieldValueException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundedException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleRuntimeExceptions(ResourceNotFoundedException e) {
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(e.getLocalizedMessage(), ERROR_CODE);
        return ResponseEntity.status(status).body(exceptionResponseBody);
    }
}