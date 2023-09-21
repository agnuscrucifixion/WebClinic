package com.padwicki.webclinic.model.serviceInjection;


import com.padwicki.webclinic.CustomExceptions.InvalidSerialNumberException;
import com.padwicki.webclinic.CustomExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.CustomExceptions.NotFoundPatientException;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.model.service.PatientService;
import java.util.List;

/**
 * Service interface, with an implementation of controller methods,
 * and this interface implements {@link PatientService}.
 */
public interface PatientServiceInterface {
        /**
         * Method for finding all patients.
         * @return a list of all patients.
         */
        List<Patient> getPatients();

        /**
         * Method for searching patient by serial number.
         * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
         * @return a patient object with the specified serial number or null.
         * @throws InvalidSerialNumberException if wrong serial number.
         */
        Patient getPatientBySerialNumber(int serialNumber) throws InvalidSerialNumberException;

        /**
         * Method for adding a patient to the database.
         * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
         * @param name patient's name.
         * @param surname patient's surname.
         * @param diagnostic patient's diagnostic.
         * @param drugs patient's drugs for heal.
         * @throws NotDoublePatientsException if a patient with the same serial number is found.
         * @throws InvalidSerialNumberException if wrong serial number.
         */
        void addPatient(int serialNumber, String name,
                        String surname, String diagnostic,
                        String drugs) throws NotDoublePatientsException, InvalidSerialNumberException;

        /**
         * Updates patient information, changes information if given appropriate parameters.
         * @param oldSerialNumber old patient's Serial Number must be greater than 0 and less than 2147483647.
         * @param newSerialNumber new patient's Serial Number must be greater than 0 and less than 2147483647.
         * @param newName new patient's name.
         * @param newSurname new patient's surname.
         * @param newDiagnostic new patient's diagnostic.
         * @param newDrugs new patient's drugs for heal.
         * @throws InvalidSerialNumberException if wrong serial number.
         * @throws NotFoundPatientException if a patient is not found by old serial number.
         */
        void updatePatient(int oldSerialNumber, Integer newSerialNumber,
                                  String newName, String newSurname,
                                  String newDiagnostic, String newDrugs) throws InvalidSerialNumberException,
                                                                                NotFoundPatientException;

        /**
         * Deleting a patient from the database by serial number.
         * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
         * @throws NotFoundPatientException if a patient is not found by old serial number.
         * @throws InvalidSerialNumberException if wrong serial number.
         */
        void deletePatient(int serialNumber) throws NotFoundPatientException, InvalidSerialNumberException;
}
