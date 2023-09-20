package com.padwicki.webclinic.controlls.controllers;

import com.padwicki.webclinic.controlls.controllersInjection.PatientControllerInterface;
import com.padwicki.webclinic.domain.entity.Patient;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController implements PatientControllerInterface {


    @Override
    public List<Patient> getPatients() {
        return null;
    }

    @Override
    public Patient getPatientBySerialNumber(int serialNumber) {
        return null;
    }
}
