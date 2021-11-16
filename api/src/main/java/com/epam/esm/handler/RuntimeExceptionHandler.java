package com.epam.esm.handler;

import com.epam.esm.entity.ResponseExceptionEntity;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.*;
import com.epam.esm.util.ErrorCodeCounter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    /**
     * Handle MethodArgumentTypeMismatchException.
     *
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleMethodArgumentTypeMismatchException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid number data in URL";
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, ExceptionCauseCode.UNKNOWN);
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

    /**
     * Handle HttpMessageNotReadableException.
     *
     * @return the response entity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleHttpMessageNotReadableException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid data entered in the request body";
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, ExceptionCauseCode.UNKNOWN);
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

    /**
     * Handle BindException.
     *
     * @return the response entity
     */
    @ExceptionHandler(BindException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleBindException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid data entered in the URL";
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, ExceptionCauseCode.UNKNOWN);
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

    /**
     * Handle BadCredentialsException.
     *
     * @return the response entity
     */
    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleBadCredentialsException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid user credentials";
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, ExceptionCauseCode.USER);
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

    /**
     * Handle LockedException.
     *
     * @return the response entity
     */
    @ExceptionHandler(LockedException.class)
    public final ResponseEntity<ResponseExceptionEntity> handleLockedException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "User account locked";
        int errorCode = ErrorCodeCounter.countErrorCode(httpStatus, ExceptionCauseCode.USER);
        ResponseExceptionEntity exceptionResponseBody = new ResponseExceptionEntity(message, errorCode);
        return ResponseEntity.status(httpStatus).body(exceptionResponseBody);
    }

}
