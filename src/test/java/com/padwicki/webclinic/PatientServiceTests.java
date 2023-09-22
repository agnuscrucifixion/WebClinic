package com.padwicki.webclinic;

import com.padwicki.webclinic.CustomExceptions.InvalidSerialNumberException;
import com.padwicki.webclinic.CustomExceptions.NotDoublePatientsException;
import com.padwicki.webclinic.CustomExceptions.NotFoundPatientException;
import com.padwicki.webclinic.app.WebClinicApplication;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.domain.repository.PatientRepository;
import com.padwicki.webclinic.model.service.PatientService;
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
    private int serial = 10000;

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

        if (patientService.getPatientBySerialNumber(serial) != null) {
            patientService.deletePatient(serial);
            assertNull(patientService.getPatientBySerialNumber(serial));
        }
    }

    /**
     * Deletion test where a non-valid serial number is input value.
     * @throws InvalidSerialNumberException invalid format of serial number(<=0).
     */
    @Test
    void testDeleting2_withLessZeroSerialNumber_thenReturnException() throws InvalidSerialNumberException {
        assertThrows(InvalidSerialNumberException.class, () -> patientService.deletePatient(-1));
    }

    /**
     * Deletion test where a serial number is supplied such that there is no such entry in the database.
     * @throws NotFoundPatientException no record in the database.
     */
    @Test
    void testDeleting3_withNoRecordInDB_thenReturnException() throws NotFoundPatientException {
        assertThrows(NotFoundPatientException.class, () -> patientService.deletePatient(1));
    }

    /**
     * Test for adding to a database where valid data is fed in.
     */
    @Test
    void testAdding1_withNoLessZeroSerialNumberAndNotDuplicateInDB_thenReturnObject() {
        Patient patient = addOneRecord();

        patientService.addPatient(patient.getSerialNumber(),patient.getName(), patient.getSurname(),
                patient.getDiagnosis(),patient.getDrugs());

        assertEquals("test", patientRepository.findPatientBySerialNumber(serial).getName());

        patientService.deletePatient(serial);
    }

    /**
     * Test for adding to the database where the wrong serial number format is supplied.
     * @throws InvalidSerialNumberException invalid format of serial number(<=0).
     */
    @Test
    void testAdding2_withLessZeroSerialNumber_thenReturnException() throws InvalidSerialNumberException {
        assertThrows(InvalidSerialNumberException.class, () -> patientService.addPatient(-1,"test",
                "test","test","test"));
    }

    /**
     * Test for adding to the database, where such a serial number is supplied that such a record already exists.
     * @throws NotDoublePatientsException A record already exists.
     */
    @Test
    void testAdding3_withDuplicateInDB_thenReturnException() throws NotDoublePatientsException {
        patientService.addPatient(10000,"test", "test","test","test");
        assertThrows(NotDoublePatientsException.class, () -> patientService.addPatient(10000,"test",
                "test","test","test"));
        patientService.deletePatient(10000);
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
        assertThrows(InvalidSerialNumberException.class,  () -> patientService.getPatientBySerialNumber(-1));
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
        assertThrows(InvalidSerialNumberException.class, () -> patientService.updatePatient(-1,
                100, "test", "test", "test", "test"));
    }

    /**
     * Test for data updates of a record using the old serial number, where the old serial number is
     * incorrect and no such record exists.
     * @throws NotFoundPatientException no record in the database.
     */
    @Test
    void testUpdatingRecord3_withNoRecordInDB_thenReturnException() throws NotFoundPatientException {
        assertThrows(NotFoundPatientException.class, () -> patientService.updatePatient(100,
                100, "test", "test", "test", "test"));
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
        Patient patient = new Patient();

        patient.setName("test");
        patient.setSurname("test");
        while (patientRepository.findPatientBySerialNumber(serial) != null) {
            serial++;
        }
        patient.setSerialNumber(serial);
        patient.setComingDate(Timestamp.valueOf(LocalDateTime.now()));
        patient.setDiagnosis("test");
        patient.setDrugs("test");
        return patient;
    }
}
