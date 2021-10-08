package com.epam.esm.entity;

/**
 * The class that contain exception response.
 *
 * @author Yauheni Tstiov
 */
public class ResponseExceptionEntity {
    private String errorMessage;
    private int errorCode;

    /**
     * Construct a new Exception response.
     *
     * @param errorMessage the message
     * @param errorCode    the error code
     */
    public ResponseExceptionEntity(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets errorMessage    .
     *
     * @param errorMessage the message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
