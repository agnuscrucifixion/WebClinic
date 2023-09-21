package com.padwicki.webclinic.ControllerAdvice;

import com.padwicki.webclinic.CustomExceptions.ErrorResponsePatient;
import com.padwicki.webclinic.CustomExceptions.InvalidSerialNumberException;
import com.padwicki.webclinic.CustomExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.CustomExceptions.NotFoundPatientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * Centralized class exception handler.
 */
@RestControllerAdvice
public class AdviceException {
    /**
     *
     * @param e Object of the class of the exception to be caught.
     * @return Returns the response body, which includes the message and HTTP code.
     */
    @ExceptionHandler(NotFoundPatientException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(NotFoundPatientException e) {
        ErrorResponsePatient response = new ErrorResponsePatient(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    /**
     *
     * @param e Object of the class of the exception to be caught.
     * @return Returns the response body, which includes the message and HTTP code.
     */
    @ExceptionHandler(NotDoublePatientsException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(NotDoublePatientsException e) {
        ErrorResponsePatient response = new ErrorResponsePatient(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    /**
     *
     * @param e Object of the class of the exception to be caught.
     * @return Returns the response body, which includes the message and HTTP code.
     */
    @ExceptionHandler(TypeNotPresentException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(TypeNotPresentException e) {
        ErrorResponsePatient response = new ErrorResponsePatient(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    /**
     *
     * @param e Object of the class of the exception to be caught.
     * @return Returns the response body, which includes the message and HTTP code.
     */
    @ExceptionHandler(InvalidSerialNumberException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(InvalidSerialNumberException e) {
        ErrorResponsePatient response = new ErrorResponsePatient(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @return Returns the response body, which includes the message and HTTP code.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponsePatient> handleException() {
        ErrorResponsePatient response = new ErrorResponsePatient("Serial number must ne less than 2147483647");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
