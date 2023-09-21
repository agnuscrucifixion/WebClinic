package com.padwicki.webclinic.CustomExceptions;

public class ErrorResponsePatient {
    String message;

    public ErrorResponsePatient(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
