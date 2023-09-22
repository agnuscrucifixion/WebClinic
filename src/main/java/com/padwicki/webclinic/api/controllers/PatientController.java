package com.padwicki.webclinic.api.controllers;

import com.padwicki.webclinic.api.controllerInterfaces.PatientControllerInterface;
import com.padwicki.webclinic.api.dto.AddPatientRqDTO;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.service.serviceImpl.PatientService;
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
    public Patient getPatientBySerialNumber(String serialNumber) {
        return patientService.getPatientBySerialNumber(serialNumber);
    }

    @Override
    public void addPatient(AddPatientRqDTO addPatientRqDTO) {
        patientService.addPatient(addPatientRqDTO);
    }

    @Override
    public void updatePatient(String oldSerialNumber, String newSerialNumber,
                              String newName, String newSurname,
                              String newDiagnostic, String newDrugs) {
        patientService.updatePatient(oldSerialNumber, newSerialNumber, newName, newSurname, newDiagnostic, newDrugs);
    }

    @Override
    public void deletePatient(String serialNumber) {
        patientService.deletePatient(serialNumber);
    }
}
