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

/**
 * Implementation of the service interface {@link PatientServiceInterface}.
 */
@Service
public class PatientService implements PatientServiceInterface {

    private final PatientRepository patientRepository;

    /**
     * Injection {@link PatientRepository} into this class.
     * @param patientRepository field of the {@link PatientRepository},
     * that communicates with the database.
     */
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Implementation of the service interface method for finding all patients.
     * @return a list of all patients.
     */
    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    /**
     * Searches the database for a patient with a given serial number by repository.
     * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
     * @return a patient object with the specified serial number or null.
     * @throws InvalidSerialNumberException if wrong serial number.
     */
    @Override
    public Patient getPatientBySerialNumber(int serialNumber) throws InvalidSerialNumberException {
        if (validateSerialNumber(serialNumber)) {
            return patientRepository.findPatientBySerialNumber(serialNumber);
        }
        throw new InvalidSerialNumberException("Serial number must ne greater than 0");
    }

    /**
     * Adds a patient to the database by repository.
     * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
     * @param name patient's name.
     * @param surname patient's surname.
     * @param diagnostic patient's diagnostic.
     * @param drugs patient's drugs for heal.
     * @throws NotDoublePatientsException if a patient with the same serial number is found.
     * @throws InvalidSerialNumberException if wrong serial number.
     */
    @Override
    public void addPatient(int serialNumber, String name,
                           String surname, String diagnostic,
                           String drugs) throws NotDoublePatientsException, InvalidSerialNumberException {
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

    /**
     * Updates patient information, changes information if given appropriate parameters by repository.
     * @param oldSerialNumber old patient's Serial Number must be greater than 0 and less than 2147483647.
     * @param newSerialNumber new patient's Serial Number must be greater than 0 and less than 2147483647.
     * @param newName new patient's name.
     * @param newSurname new patient's surname.
     * @param newDiagnostic new patient's diagnostic.
     * @param newDrugs new patient's drugs for heal.
     * @throws InvalidSerialNumberException if wrong serial number.
     * @throws NotFoundPatientException if a patient is not found by old serial number.
     */
    @Override
    public void updatePatient(int oldSerialNumber, Integer newSerialNumber,
                              String newName, String newSurname,
                              String newDiagnostic, String newDrugs) throws InvalidSerialNumberException,
                                                                            NotFoundPatientException {
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

    /**
     * Deleting a patient from the database by serial number by repository.
     * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
     * @throws NotFoundPatientException if a patient is not found by old serial number.
     * @throws InvalidSerialNumberException if wrong serial number.
     */
    @Override
    public void deletePatient(int serialNumber) throws NotFoundPatientException, InvalidSerialNumberException {
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
