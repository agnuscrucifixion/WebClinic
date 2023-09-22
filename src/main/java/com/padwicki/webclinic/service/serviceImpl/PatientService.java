package com.padwicki.webclinic.service.serviceImpl;

import com.padwicki.webclinic.api.dto.AddPatientRqDTO;
import com.padwicki.webclinic.service.exceptions.customExceptions.InvalidSerialNumberException;
import com.padwicki.webclinic.service.exceptions.customExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.service.exceptions.customExceptions.NotFoundPatientException;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.domain.repository.PatientRepository;
import com.padwicki.webclinic.service.serviceInterfaces.PatientServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
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

    @Override
    public List<Patient> getPatients() {
        List<Patient> patientList = patientRepository.findAll();
        if (patientList.isEmpty()) {
            log.warn("Method '/show-all-patients' return empty list of patients");
        }
        return patientList;
    }

    @Override
    public Patient getPatientBySerialNumber(String serialNumber) throws InvalidSerialNumberException,
                                                                        NumberFormatException {
        log.info("Collect {}", serialNumber);

        if (validateSerialNumber(serialNumber)) {
            Patient patient = patientRepository.findPatientBySerialNumber(Integer.parseInt(serialNumber));
            log.info("Get this record: {}",patient);
            return patient;
        }

        log.error("In method '/show-patient-by-serialNumber' wrong input argument {} with Exception {}", serialNumber,
                InvalidSerialNumberException.class);

        throw new InvalidSerialNumberException("Serial number must be more than 0 and less than 2147483647");
    }

    @Override
    public void addPatient(AddPatientRqDTO addPatientRqDTO) throws NotDoublePatientsException,
            InvalidSerialNumberException, NumberFormatException {
        log.info("Collect serialNumber: {}, name: {}, surname: {}, diagnostic: {}, drugs: {}",
                addPatientRqDTO.getSerialNumber(), addPatientRqDTO.getName(), addPatientRqDTO.getSurname(),
                addPatientRqDTO.getDiagnosis(), addPatientRqDTO.getDrugs());


        if (validateSerialNumber(addPatientRqDTO.getSerialNumber())) {
            if (patientRepository.findPatientBySerialNumber(Integer.parseInt(addPatientRqDTO.getSerialNumber())) == null) {
                Patient patient = new Patient();
                patient.setSerialNumber(Integer.parseInt(addPatientRqDTO.getSerialNumber()));
                patient.setName(addPatientRqDTO.getName());
                patient.setSurname(addPatientRqDTO.getSurname());
                patient.setDiagnosis(addPatientRqDTO.getDiagnosis());
                patient.setDrugs(addPatientRqDTO.getDrugs());
                LocalDateTime localDateTime = LocalDateTime.now();
                patient.setComingDate(Timestamp.valueOf(localDateTime));
                patientRepository.save(patient);
                log.info("Save to database object {}", patient);
            } else {
                log.warn("In method '/add_patient' duplicate record attempt by serial number: {} with Exception: {}",
                        addPatientRqDTO.getSerialNumber(), NotDoublePatientsException.class);

                throw new NotDoublePatientsException("A patient with that serial number already EXISTS");
            }
        } else {
            log.error("In method '/add_patient' wrong input argument {} with Exception: {}",
                    addPatientRqDTO.getSerialNumber(), InvalidSerialNumberException.class);

            throw new InvalidSerialNumberException("Serial number must be more than 0 and less than 2147483647");
        }

    }

    @Override
    public void updatePatient(String oldSerialNumber, String newSerialNumber,
                              String newName, String newSurname,
                              String newDiagnostic, String newDrugs) throws InvalidSerialNumberException,
            NotFoundPatientException, NumberFormatException {
        log.info("Collect oldSerialNumber: {}, newSerialNumber: {}, name: {}, surname: {}, diagnostic: {}, drugs: {}",
                oldSerialNumber, newSerialNumber, newName, newSurname, newDiagnostic, newDrugs);

        if (validateSerialNumber(oldSerialNumber)) {
            Patient oldPatient = patientRepository.findPatientBySerialNumber(Integer.parseInt(oldSerialNumber));
            log.info("Read old patient record: {}", oldPatient);
            if (oldPatient != null) {
                if (newSerialNumber != null) {
                    if (validateSerialNumber(newSerialNumber)) {
                        oldPatient.setSerialNumber(Integer.parseInt(newSerialNumber));
                    } else {
                        log.error("In method '/update-info-by-serialNumber' wrong input argument {} with Exception: {}",
                                newSerialNumber, InvalidSerialNumberException.class);

                        throw new InvalidSerialNumberException("Serial number must be more than 0 " +
                                "and less than 2147483647");
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
                log.info("Save to database object {}", oldPatient);
            } else {
                log.warn("In method '/update-info-by-serialNumber' attempting to find " +
                        "a record with a nonexistent serial number: {}", oldSerialNumber);

                throw new NotFoundPatientException("There is NO patient to change with a given serial number");
            }
        } else {
            log.error("In method '/update-info-by-serialNumber' wrong input argument {} with Exception: {}",
                    oldSerialNumber, InvalidSerialNumberException.class);

            throw new InvalidSerialNumberException("Serial number must be more than 0 and less than 2147483647");
        }
    }

    @Override
    public void deletePatient(String serialNumber) throws NotFoundPatientException, InvalidSerialNumberException,
                                                        NumberFormatException{
        log.info("Collect serial number: {}", serialNumber);
        if (validateSerialNumber(serialNumber)) {
            Patient patient = patientRepository.findPatientBySerialNumber(Integer.parseInt(serialNumber));
            log.info("Read record: {} by serial number: {}", patient,serialNumber);
            if (patient != null) {
                patientRepository.delete(patient);
                log.info("Record is deleted");
            } else {
                log.error("Attempting to remove a nonexistent record" +
                        " from the database by serial number: {}", serialNumber);

                throw new NotFoundPatientException("There is NO patient to remove with that serial number");
            }
        } else {
            log.error("In method '/delete-patient-by-serialNumber' wrong input argument {} with Exception: {}",
                    serialNumber, InvalidSerialNumberException.class);

            throw new InvalidSerialNumberException("Serial number must be more than 0 and less than 2147483647");
        }

    }

    private boolean validateSerialNumber(String serialNumber) {
        try {
            int number = Integer.parseInt(serialNumber);
            return number > 0 && number < Integer.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
