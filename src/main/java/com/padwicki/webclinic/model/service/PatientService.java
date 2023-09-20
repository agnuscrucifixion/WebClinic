package com.padwicki.webclinic.model.service;

import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.domain.repository.PatientRepository;
import com.padwicki.webclinic.model.serviceInjection.PatientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public void updatePatient(int oldSerialNumber, int newSerialNumber,
                              String newName, String newSurname,
                              String newDiagnostic, String newDrugs) {
        Patient patient = patientRepository.findPatientBySerialNumber(newSerialNumber);
        if (patient == null) {
            Patient oldPatient = patientRepository.findPatientBySerialNumber(oldSerialNumber);

            oldPatient.setSerialNumber(newSerialNumber);
            oldPatient.setName(newName);
            oldPatient.setSurname(newSurname);
            oldPatient.setDiagnosis(newDiagnostic);
            oldPatient.setDrugs(newDrugs);
            patientRepository.save(oldPatient);
        }
    }

    @Override
    public void deletePatient(int serialNumber) {
        Patient patient = patientRepository.findPatientBySerialNumber(serialNumber);
        if (patient != null) {
            patientRepository.delete(patient);
        }
        //добавить исключения
    }
}
