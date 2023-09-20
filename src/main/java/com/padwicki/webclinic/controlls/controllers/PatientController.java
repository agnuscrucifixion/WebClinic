package com.padwicki.webclinic.controlls.controllers;

import com.padwicki.webclinic.controlls.controllersInjection.PatientControllerInterface;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.model.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController implements PatientControllerInterface {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @Override
    public Patient getPatientBySerialNumber(int serialNumber) {
        return patientService.getPatientBySerialNumber(serialNumber);
    }
}
