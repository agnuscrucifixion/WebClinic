package com.padwicki.webclinic.service.exceptions.customExceptions;

/**
 * The class exception that reacts to incorrect serial number.
 */
public class InvalidSerialNumberException extends RuntimeException {
    /**
     * Exception constructor.
     * @param message error message.
     */
    public InvalidSerialNumberException(String message) {
        super(message);
    }
}
