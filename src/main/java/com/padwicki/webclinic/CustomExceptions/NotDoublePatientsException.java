package com.padwicki.webclinic.CustomExceptions;

public class NotDoublePatientsException extends RuntimeException {
    public NotDoublePatientsException(String message) {
        super(message);
    }
}
