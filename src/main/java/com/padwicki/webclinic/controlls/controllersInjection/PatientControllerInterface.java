package com.padwicki.webclinic.controlls.controllersInjection;

import com.padwicki.webclinic.domain.entity.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/patient")
public interface PatientControllerInterface {
    @GetMapping("/show-all-patients")
    List<Patient> getPatients();

    @GetMapping("/show-patint-by-serialNumber")
    Patient getPatientBySerialNumber(@RequestParam int serialNumber);

    @PostMapping("/add_patient")
    void addPatient(@RequestParam int serialNumber, @RequestParam String name, @RequestParam String surname,
                           @RequestParam String diagnostic, @RequestParam String drugs);

    @PatchMapping("update-info-by-serialNumber")
    void updatePatient(@RequestParam int oldSerialNumber, @RequestParam(required = false) Integer newSerialNumber,
                       @RequestParam(required = false) String newName,
                       @RequestParam(required = false) String newSurname,
                       @RequestParam(required = false) String newDiagnostic,
                       @RequestParam(required = false) String newDrugs);

    @DeleteMapping("/delete-patient-by-serialNumber")
    void deletePatient(@RequestParam int serialNumber);
}
