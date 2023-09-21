package com.padwicki.webclinic.CustomExceptions;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class InvalidSerialNumberException extends RuntimeException {
    public InvalidSerialNumberException(String message) {
        super(message);
    }
}
