package com.padwicki.webclinic.model.service;

import com.padwicki.webclinic.CustomExceptions.InvalidSerialNumberException;
import com.padwicki.webclinic.CustomExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.CustomExceptions.NotFoundPatientException;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.domain.repository.PatientRepository;
import com.padwicki.webclinic.model.serviceInjection.PatientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        if (validateSerialNumber(serialNumber)) {
            return patientRepository.findPatientBySerialNumber(serialNumber);
        }
        throw new InvalidSerialNumberException("Serial number must ne greater than 0");
    }

    @Override
    public void addPatient(int serialNumber, String name, String surname,
                           String diagnostic, String drugs) {
        if (validateSerialNumber(serialNumber)) {
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
            } else {
                throw new NotDoublePatientsException("A patient with that serial number already EXISTS");
            }
        } else  {
            throw new InvalidSerialNumberException("Serial number must ne greater than 0");
        }

    }

    @Override
    public void updatePatient(int oldSerialNumber, Integer newSerialNumber,
                              String newName, String newSurname,
                              String newDiagnostic, String newDrugs) {
        if (validateSerialNumber(oldSerialNumber)) {
            Patient oldPatient = patientRepository.findPatientBySerialNumber(oldSerialNumber);
            if (oldPatient != null) {
                if (newSerialNumber != null) {
                    if (validateSerialNumber(newSerialNumber)) {
                        oldPatient.setSerialNumber(newSerialNumber);
                    } else {
                        throw new InvalidSerialNumberException("Serial number must ne greater than 0");
                    }
                }
                if (newName != null) {
                    oldPatient.setName(newName);
                }
                if (newSurname != null) {
                    oldPatient.setSurname(newSurname);
                }
                if (newDiagnostic != null) {
                    oldPatient.setDiagnosis(newDiagnostic);
                }
                if (newDrugs != null) {
                    oldPatient.setDrugs(newDrugs);
                }
                patientRepository.save(oldPatient);
            } else {
                throw new NotFoundPatientException("There is NO patient to change with a given serial number");
            }
        } else {
            throw new InvalidSerialNumberException("Serial number must ne greater than 0");
        }
    }

    @Override
    public void deletePatient(int serialNumber) {
        if (validateSerialNumber(serialNumber)) {
            Patient patient = patientRepository.findPatientBySerialNumber(serialNumber);
            if (patient != null) {
                patientRepository.delete(patient);
            } else {
                throw new NotFoundPatientException("There is NO patient to remove with that serial number");
            }
        } else  {
            throw new InvalidSerialNumberException("Serial number must ne greater than 0");
        }

    }

    private boolean validateSerialNumber(int serialNumber) {
        return serialNumber > 0;
    }
}
