package com.padwicki.webclinic.ControllerAdvice;

import com.padwicki.webclinic.CustomExceptions.ErrorResponsePatient;
import com.padwicki.webclinic.CustomExceptions.InvalidSerialNumberException;
import com.padwicki.webclinic.CustomExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.CustomExceptions.NotFoundPatientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class AdviceException {
    @ExceptionHandler(NotFoundPatientException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(NotFoundPatientException e) {
        ErrorResponsePatient response = new ErrorResponsePatient(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotDoublePatientsException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(NotDoublePatientsException e) {
        ErrorResponsePatient response = new ErrorResponsePatient(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(TypeNotPresentException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(TypeNotPresentException e) {
        ErrorResponsePatient response = new ErrorResponsePatient(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidSerialNumberException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(InvalidSerialNumberException e) {
        ErrorResponsePatient response = new ErrorResponsePatient(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponsePatient> handleException(MethodArgumentTypeMismatchException e) {
        ErrorResponsePatient response = new ErrorResponsePatient("Serial number must ne less than 2147483647");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
