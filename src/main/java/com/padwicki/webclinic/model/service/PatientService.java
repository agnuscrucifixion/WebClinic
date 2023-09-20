package com.padwicki.webclinic.model.service;

import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.domain.repository.PatientRepository;
import com.padwicki.webclinic.model.serviceInjection.PatientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements PatientServiceInterface {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientBySerialNumber(int serialNumber) {
        return patientRepository.findPatientBySerialNumber(serialNumber);
    }

    @Override
    public void addPatient(int serialNumber, String name, String surname,
                           String diagnostic, String drugs) {
        if (patientRepository.findPatientBySerialNumber(serialNumber) == null) {
            Patient patient = new Patient();
            patient.setSerialNumber(serialNumber);
            patient.setName(name);
            patient.setSurname(surname);
            patient.setDiagnosis(diagnostic);
            patient.setDrugs(drugs);
            LocalDateTime localDateTime = LocalDateTime.now();
            patient.setComingDate(Timestamp.valueOf(localDateTime));
            patientRepository.save(patient);
        }
    }
}
