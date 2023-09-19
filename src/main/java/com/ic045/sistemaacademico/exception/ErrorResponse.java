package com.ic045.sistemaacademico.exception;

public class ErrorResponse {

    private int statusCode;

    private String message;

    public ErrorResponse(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(String message) {
        super();
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
