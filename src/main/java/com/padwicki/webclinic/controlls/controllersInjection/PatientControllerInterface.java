package com.padwicki.webclinic.controlls.controllersInjection;

import com.padwicki.webclinic.domain.entity.Patient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/patient")
public interface PatientControllerInterface {
    @GetMapping("/show-all-patients")
    public List<Patient> getPatients();

    @GetMapping("/show-patint-by-serialNumber")
    public Patient getPatientBySerialNumber(@RequestParam int serialNumber);

    @PostMapping("/add_patient")
    public void addPatient(@RequestParam int serialNumber, @RequestParam String name, @RequestParam String surname,
                           @RequestParam String diagnostic, @RequestParam String drugs);

    @PatchMapping("update-info-by-serialNumber")
    public void updatePatient(@RequestParam int oldSerialNumber, @RequestParam int newSerialNumber,
                              @RequestParam String newName, @RequestParam String newSurname,
                              @RequestParam String newDiagnostic, @RequestParam String newDrugs);
}
