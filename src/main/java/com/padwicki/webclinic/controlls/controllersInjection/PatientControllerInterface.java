package com.padwicki.webclinic.controlls.controllersInjection;

import com.padwicki.webclinic.domain.entity.Patient;
import org.springframework.web.bind.annotation.*;
import com.padwicki.webclinic.controlls.controllers.PatientController;
import java.util.List;

/**
 * An interface that addresses controller methods and is implemented by {@link PatientController} as @RestController.
 */
@RequestMapping("/patient")
public interface PatientControllerInterface {
    /**
     * An interface method with address "/show-all-patients".
     * @return a list of all patients
     */
    @GetMapping("/show-all-patients")
    List<Patient> getPatients();

    /**
     * An interface method with address "/show-patient-by-serialNumber".
     * @param serialNumber patient's Serial Number.
     * @return patient class object
     */
    @GetMapping("/show-patient-by-serialNumber")
    Patient getPatientBySerialNumber(@RequestParam int serialNumber);

    /**
     * An interface method with address "/add_patient".
     * @param serialNumber patient's Serial Number.
     * @param name patient's name.
     * @param surname patient's surname.
     * @param diagnostic patient's diagnostic.
     * @param drugs patient's drugs for heal.
     */
    @PostMapping("/add_patient")
    void addPatient(@RequestParam int serialNumber, @RequestParam String name, @RequestParam String surname,
                           @RequestParam String diagnostic, @RequestParam String drugs);

    /**
     * An interface method with address "/update-info-by-serialNumber".
     * @param oldSerialNumber old patient's Serial Number.
     * @param newSerialNumber new patient's Serial Number.
     * @param newName new patient's name.
     * @param newSurname new patient's surname.
     * @param newDiagnostic new patient's diagnostic.
     * @param newDrugs new patient's drugs for heal.
     */
    @PatchMapping("/update-info-by-serialNumber")
    void updatePatient(@RequestParam int oldSerialNumber, @RequestParam(required = false) Integer newSerialNumber,
                       @RequestParam(required = false) String newName,
                       @RequestParam(required = false) String newSurname,
                       @RequestParam(required = false) String newDiagnostic,
                       @RequestParam(required = false) String newDrugs);

    /**
     * An interface method with address "/delete-patient-by-serialNumber".
     * @param serialNumber patient's Serial Number.
     */
    @DeleteMapping("/delete-patient-by-serialNumber")
    void deletePatient(@RequestParam int serialNumber);
}
