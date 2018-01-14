package com.wallet.web.model;

public class ErrorResponse {
    private String exception;
    private String message;

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse(String exception, String message) {
        this.exception = exception;
        this.message = message;
    }
}
