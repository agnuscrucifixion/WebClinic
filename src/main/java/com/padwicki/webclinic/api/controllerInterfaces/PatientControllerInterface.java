package com.padwicki.webclinic.api.controllerInterfaces;

import com.padwicki.webclinic.api.dto.AddPatientRqDTO;
import com.padwicki.webclinic.domain.entity.Patient;
import org.springframework.web.bind.annotation.*;
import com.padwicki.webclinic.api.controllers.PatientController;
import java.util.List;

/**
 * An interface that addresses controller methods and is implemented by {@link PatientController} as @RestController.
 */
@RequestMapping("/patient")
public interface PatientControllerInterface {
    /**
     * Method of the controller for receiving all patients by service.
     * @return a list of all patients.
     */
    @GetMapping("/show-all-patients")
    List<Patient> getPatients();

    /**
     * Searches the database for a patient with a given serial number.
     * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
     * @return returns a patient object with the specified serial number or null.
     */
    @GetMapping("/show-patient-by-serialNumber")
    Patient getPatientBySerialNumber(@RequestParam String serialNumber);

    /**
     * Adds a patient to the database.
     * @param addPatientRqDTO {@link AddPatientRqDTO}.
     */
    @PostMapping("/add_patient")
    void addPatient(@RequestBody AddPatientRqDTO addPatientRqDTO);

    /**
     * Updates patient information, changes information if given appropriate parameters.
     * @param oldSerialNumber old patient's Serial Number must be greater than 0 and less than 2147483647.
     * @param newSerialNumber new patient's Serial Number must be greater than 0 and less than 2147483647.
     * @param newName new patient's name.
     * @param newSurname new patient's surname.
     * @param newDiagnostic new patient's diagnostic.
     * @param newDrugs new patient's drugs for heal.
     */
    @PatchMapping("/update-info-by-serialNumber")
    void updatePatient(@RequestParam String oldSerialNumber, @RequestParam(required = false) String newSerialNumber,
                       @RequestParam(required = false) String newName,
                       @RequestParam(required = false) String newSurname,
                       @RequestParam(required = false) String newDiagnostic,
                       @RequestParam(required = false) String newDrugs);

    /**
     * Deleting a patient from the database by serial number.
     * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
     */
    @DeleteMapping("/delete-patient-by-serialNumber")
    void deletePatient(@RequestParam String serialNumber);
}
