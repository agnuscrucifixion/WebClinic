package com.padwicki.webclinic.ControllerAdvice;

import com.padwicki.webclinic.CustomExceptions.ErrorResponsePatient;
import com.padwicki.webclinic.CustomExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.CustomExceptions.NotFoundPatientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


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
}
