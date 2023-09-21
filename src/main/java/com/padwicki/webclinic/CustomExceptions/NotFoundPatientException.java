package com.padwicki.webclinic.CustomExceptions;

public class NotFoundPatientException extends RuntimeException {
    public NotFoundPatientException(String message) {
        super(message);
    }
}
