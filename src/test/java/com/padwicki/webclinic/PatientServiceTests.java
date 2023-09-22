package com.padwicki.webclinic;

import com.padwicki.webclinic.api.dto.AddPatientRqDTO;
import com.padwicki.webclinic.service.exceptions.customExceptions.InvalidSerialNumberException;
import com.padwicki.webclinic.service.exceptions.customExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.service.exceptions.customExceptions.NotFoundPatientException;
import com.padwicki.webclinic.app.WebClinicApplication;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.domain.repository.PatientRepository;
import com.padwicki.webclinic.service.serviceImpl.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests class.
 */
@SpringBootTest(classes = WebClinicApplication.class)
class PatientServiceTests {
    private String serial = "10000";

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;


    /**
     * Deletion test to check for performance with valid data.
     */
    @Test
    void testDeleting1_withNotLessZeroSerialNumber_thenReturnNull() {
        Patient patient = addOneRecord();

        patientRepository.save(patient);

        if (patientService.getPatientBySerialNumber(String.valueOf(patient.getSerialNumber())) != null) {
            patientService.deletePatient(String.valueOf(patient.getSerialNumber()));
            assertNull(patientService.getPatientBySerialNumber(String.valueOf(patient.getSerialNumber())));
        }
    }

    /**
     * Deletion test where a non-valid serial number is input value.
     * @throws InvalidSerialNumberException invalid format of serial number(<=0).
     */
    @Test
    void testDeleting2_withLessZeroSerialNumber_thenReturnException() throws InvalidSerialNumberException {
        assertThrows(InvalidSerialNumberException.class, () -> patientService.deletePatient("-1"));
    }

    /**
     * Deletion test where a serial number is supplied such that there is no such entry in the database.
     * @throws NotFoundPatientException no record in the database.
     */
    @Test
    void testDeleting3_withNoRecordInDB_thenReturnException() throws NotFoundPatientException {
        assertThrows(NotFoundPatientException.class, () -> patientService.deletePatient("1"));
    }

    /**
     * Test for adding to a database where valid data is fed in.
     */
    @Test
    void testAdding1_withNoLessZeroSerialNumberAndNotDuplicateInDB_thenReturnObject() {
        Patient patient = addOneRecord();
        AddPatientRqDTO patientRqDTO = patientRqDTO(patient);
        patientService.addPatient(patientRqDTO);

        assertEquals("test",
                patientRepository.findPatientBySerialNumber(Integer.parseInt(patientRqDTO.getSerialNumber())).getName());

        patientService.deletePatient(serial);
    }

    /**
     * Test for adding to the database where the wrong serial number format is supplied.
     * @throws InvalidSerialNumberException invalid format of serial number(<=0).
     */
    @Test
    void testAdding2_withLessZeroSerialNumber_thenReturnException() throws InvalidSerialNumberException {
        AddPatientRqDTO patientRqDTO =
                patientRqDTO(new Patient(-1,"test","test","test","test"));
        assertThrows(InvalidSerialNumberException.class, () -> patientService.addPatient(patientRqDTO));
    }

    /**
     * Test for adding to the database, where such a serial number is supplied that such a record already exists.
     * @throws NotDoublePatientsException A record already exists.
     */
    @Test
    void testAdding3_withDuplicateInDB_thenReturnException() throws NotDoublePatientsException {
        AddPatientRqDTO patientRqDTO =
                patientRqDTO(new Patient(10000,"test", "test","test","test"));
        patientService.addPatient(patientRqDTO);
        assertThrows(NotDoublePatientsException.class, () -> patientService.addPatient(patientRqDTO));
        patientService.deletePatient("10000");
    }

    /**
     * Serial number data showing test, where valid data is submitted.
     */
    @Test
    void testShowingRecordBySerialNumber1_withNoLessZeroSerialNumber_thenReturnRecord() {
        Patient patient = addOneRecord();

        patientRepository.save(patient);

        assertEquals(patient.getName(),patientService.getPatientBySerialNumber(serial).getName());

        patientService.deletePatient(serial);
    }

    /**
     * Serial number data output test, where an incorrect serial number format is supplied.
     * @throws InvalidSerialNumberException invalid format of serial number(<=0).
     */
    @Test
    void testShowingRecordBySerialNumber2_withLessZeroSerialNumber_thenReturnException() throws InvalidSerialNumberException {
        assertThrows(InvalidSerialNumberException.class,  () -> patientService.getPatientBySerialNumber("-1"));
    }

    /**
     * Test for record data updates by old serial number with the correct inputs.
     */
    @Test
    void testUpdatingRecord1_withNoRequiredParam_thenReturnUpdateRecord() {
        Patient patient = addOneRecord();

        patientRepository.save(patient);

        patient = patientService.getPatientBySerialNumber(serial);
        patient.setName("update");

        patientRepository.save(patient);

        assertEquals("update", patientService.getPatientBySerialNumber(serial).getName());

        patientService.deletePatient(serial);
    }

    /**
     * Test for updating record data by old serial number with wrong serial number format.
     * @throws InvalidSerialNumberException invalid format of serial number(<=0).
     */
    @Test
    void testUpdatingRecord2_withLessZeroSerialNumber_thenReturnException() throws InvalidSerialNumberException {
        assertThrows(InvalidSerialNumberException.class, () -> patientService.updatePatient("-1",
                "100", "test", "test", "test", "test"));
    }

    /**
     * Test for data updates of a record using the old serial number, where the old serial number is
     * incorrect and no such record exists.
     * @throws NotFoundPatientException no record in the database.
     */
    @Test
    void testUpdatingRecord3_withNoRecordInDB_thenReturnException() throws NotFoundPatientException {
        assertThrows(NotFoundPatientException.class, () -> patientService.updatePatient("100",
                "100", "test", "test", "test", "test"));
    }

    /**
     * Test to verify the output of all records from the database.
     */
    @Test
    void testShowingAll_thenReturnListAsString() {
        List<Patient> realList = patientService.getPatients();
        List<Patient> expectedList = patientRepository.findAll();

        assertEquals(realList.toString(),expectedList.toString());
    }

    private Patient addOneRecord() {
        int serialInteger = Integer.parseInt(serial);
        Patient patient = new Patient();

        patient.setName("test");
        patient.setSurname("test");
        while (patientRepository.findPatientBySerialNumber(serialInteger) != null) {
            serialInteger++;
        }
        patient.setSerialNumber(serialInteger);
        patient.setComingDate(Timestamp.valueOf(LocalDateTime.now()));
        patient.setDiagnosis("test");
        patient.setDrugs("test");
        return patient;
    }
    private AddPatientRqDTO patientRqDTO(Patient patient) {
        AddPatientRqDTO patientRqDTO = new AddPatientRqDTO();
        patientRqDTO.setName(patient.getName());
        patientRqDTO.setSurname(patient.getSurname());
        patientRqDTO.setSerialNumber(String.valueOf(patient.getSerialNumber()));
        patientRqDTO.setDiagnosis(patient.getDiagnosis());
        patientRqDTO.setDrugs(patient.getDrugs());
        return patientRqDTO;
    }
}
