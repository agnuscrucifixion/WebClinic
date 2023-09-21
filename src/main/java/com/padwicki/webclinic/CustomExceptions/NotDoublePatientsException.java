package com.padwicki.webclinic.CustomExceptions;

/**
 * The class exception that reacts when attempting to create a second patient with the same serial number.
 */
public class NotDoublePatientsException extends RuntimeException {
    /**
     * Exception constructor.
     * @param message error message.
     */
    public NotDoublePatientsException(String message) {
        super(message);
    }
}
