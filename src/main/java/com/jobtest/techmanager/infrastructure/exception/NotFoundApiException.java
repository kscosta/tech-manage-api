package com.jobtest.techmanager.infrastructure.exception;

public class NotFoundApiException extends RuntimeException {

    public NotFoundApiException(String message) {

        super(message);
    }

    public NotFoundApiException(String message, Throwable cause) {

        super(message, cause);
    }

}
