package com.padwicki.webclinic.controlls.controllersInjection;

import com.padwicki.webclinic.domain.entity.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/patient")
public interface PatientControllerInterface {
    @GetMapping("/show-all-patients")
    public List<Patient> getPatients();

    @GetMapping("/show-patint-by-serialNumber")
    public Patient getPatientBySerialNumber(@RequestParam int serialNumber);
}
