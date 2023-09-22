package com.padwicki.webclinic.service.exceptions.customExceptions;

/**
 * The class exception that reacts when trying to find a patient in a database that doesn't exist.
 */
public class NotFoundPatientException extends RuntimeException {
    /**
     * Exception constructor.
     * @param message error message.
     */
    public NotFoundPatientException(String message) {
        super(message);
    }
}
