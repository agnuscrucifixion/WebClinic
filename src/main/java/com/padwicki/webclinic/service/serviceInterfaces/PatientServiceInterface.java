package com.padwicki.webclinic.service.serviceInterfaces;


import com.padwicki.webclinic.api.dto.AddPatientRqDTO;
import com.padwicki.webclinic.service.exceptions.customExceptions.InvalidSerialNumberException;
import com.padwicki.webclinic.service.exceptions.customExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.service.exceptions.customExceptions.NotFoundPatientException;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.service.serviceImpl.PatientService;
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
         * @throws InvalidSerialNumberException if serial number < 0.
         * @throws NumberFormatException  if serial number > Integer.MAX_VALUE.
         */
        Patient getPatientBySerialNumber(String serialNumber) throws InvalidSerialNumberException,
                                                                        NumberFormatException;

        /**
         *   Method for adding a patient to the database.
         * @param addPatientRqDTO patient's Serial Number in dto must be greater than 0 and less than 2147483647.
         * @throws NotDoublePatientsException if there is already such a record found in the
         * database by such a serial number.
         * @throws InvalidSerialNumberException if serial number < 0.
         * @throws NumberFormatException if serial number > Integer.MAX_VALUE.
         */
        void addPatient(AddPatientRqDTO addPatientRqDTO) throws NotDoublePatientsException,
                                                                InvalidSerialNumberException,
                                                                NumberFormatException;

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
         * @throws NumberFormatException if serial number > Integer.MAX_VALUE.
         */
        void updatePatient(String oldSerialNumber, String newSerialNumber,
                                  String newName, String newSurname,
                                  String newDiagnostic, String newDrugs) throws InvalidSerialNumberException,
                                                                                NotFoundPatientException,
                                                                                NumberFormatException;

        /**
         * Deleting a patient from the database by serial number.
         * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
         * @throws NotFoundPatientException if a patient is not found by old serial number.
         * @throws InvalidSerialNumberException if wrong serial number.
         */
        void deletePatient(String serialNumber) throws NotFoundPatientException,
                                                        InvalidSerialNumberException,
                                                        NumberFormatException;
}
