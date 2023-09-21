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

    /**
     * Implementation of the controller method for receiving all patients by service.
     * @return returns a list of all patients.
     */
    @Override
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    /**
     * Searches the database for a patient with a given serial number by service.
     * @param serialNumber Patient's Serial Number.
     * @return returns a patient object with the specified serial number or null.
     */
    @Override
    public Patient getPatientBySerialNumber(int serialNumber) {
        return patientService.getPatientBySerialNumber(serialNumber);
    }

    /**
     * Adds a patient to the database by service.
     * @param serialNumber patient's Serial Number.
     * @param name patient's name.
     * @param surname patient's surname.
     * @param diagnostic patient's diagnostic.
     * @param drugs patient's drugs for heal.
     */
    @Override
    public void addPatient(int serialNumber, String name, String surname,
                           String diagnostic, String drugs) {
        patientService.addPatient(serialNumber, name, surname, diagnostic, drugs);
    }

    /**
     * Updates patient information, changes information if given appropriate parameters by service.
     * @param oldSerialNumber old patient's Serial Number.
     * @param newSerialNumber new patient's Serial Number.
     * @param newName new patient's name.
     * @param newSurname new patient's surname.
     * @param newDiagnostic new patient's diagnostic.
     * @param newDrugs new patient's drugs for heal.
     */
    @Override
    public void updatePatient(int oldSerialNumber, Integer newSerialNumber,
                              String newName, String newSurname,
                              String newDiagnostic, String newDrugs) {
        patientService.updatePatient(oldSerialNumber, newSerialNumber, newName, newSurname, newDiagnostic, newDrugs);
    }

    /**
     * Deleting a patient from the database by serial number by service.
     * @param serialNumber patient's Serial Number.
     */
    @Override
    public void deletePatient(int serialNumber) {
        patientService.deletePatient(serialNumber);
    }
}
