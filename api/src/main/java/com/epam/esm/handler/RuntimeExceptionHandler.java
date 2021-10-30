package com.epam.esm.handler;

import com.epam.esm.entity.ResponseExceptionEntity;
import com.epam.esm.exception.*;
import com.epam.esm.util.ErrorCodeCounter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The runtime exception handler.
 *
 * @author Yauheni Tstiov
 */
@ControllerAdvice
public class RuntimeExceptionHandler {
    /**
     * Handle InvalidFieldValueException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(InvalidFieldValueException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleInvalidFieldValueException(InvalidFieldValueException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = e.getLocalizedMessage();
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, e.getCodeExceptionCause());
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

    /**
     * Handle InvalidFieldValueException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(InvalidURLParameterException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleInvalidURLParameterException(InvalidURLParameterException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = e.getLocalizedMessage();
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, e.getCodeExceptionCause());
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

    /**
     * Handle ResourceNotAddedException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotAddedException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleResourceNotAddedException(ResourceNotAddedException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = e.getLocalizedMessage();
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, e.getCodeExceptionCause());
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

    /**
     * Handle ResourceNotFoundedException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundedException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleResourceNotFoundedException(ResourceNotFoundedException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = e.getLocalizedMessage();
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, e.getCodeExceptionCause());
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

    /**
     * Handle ResourceNotFoundedException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleResourceAlreadyExistException(ResourceAlreadyExistException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = e.getLocalizedMessage();
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, e.getCodeExceptionCause());
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }


}
