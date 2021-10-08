package com.epam.esm.handler;

import com.epam.esm.exception.ResourceNotAddedException;
import com.epam.esm.entity.ResponseExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The ResourceNotAddedException handler.
 *
 * @author Yauheni Tstiov
 */
@ControllerAdvice
public class ResourceNotAddedExceptionHandler {
    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final int ERROR_CODE = 50001;

    /**
     * Handle ResourceNotAddedException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotAddedException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleRuntimeExceptions(ResourceNotAddedException e) {
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(e.getLocalizedMessage(), ERROR_CODE);
        return ResponseEntity.status(status).body(exceptionResponseBody);
    }
}
