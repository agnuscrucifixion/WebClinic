package com.padwicki.webclinic.controlls.controllers;

import com.padwicki.webclinic.controlls.controllersInjection.PatientControllerInterface;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.model.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation of the controller interface {@link PatientControllerInterface}.
 */
@RestController
public class PatientController implements PatientControllerInterface {

    private final PatientService patientService;

    /**
     * Injection {@link PatientService} into this class.
     * @param patientService field of the {@link PatientService},
     * which is an implementation of the service operation for the controller.
     */
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


    @Override
    public void addPatient(int serialNumber, String name, String surname,
                           String diagnostic, String drugs) {
        patientService.addPatient(serialNumber, name, surname, diagnostic, drugs);
    }

    @Override
    public void updatePatient(int oldSerialNumber, Integer newSerialNumber,
                              String newName, String newSurname,
                              String newDiagnostic, String newDrugs) {
        patientService.updatePatient(oldSerialNumber, newSerialNumber, newName, newSurname, newDiagnostic, newDrugs);
    }

    @Override
    public void deletePatient(int serialNumber) {
        patientService.deletePatient(serialNumber);
    }
}
