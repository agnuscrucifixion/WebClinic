package com.padwicki.webclinic.CustomExceptions;

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
