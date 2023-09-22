package com.padwicki.webclinic;

import com.padwicki.webclinic.app.WebClinicApplication;
import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.domain.repository.PatientRepository;
import com.padwicki.webclinic.model.service.PatientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = WebClinicApplication.class)
class PatientServiceTests {
    private int serial = 10000;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    void testDeleting1_withNotNullSerialNumber_thenReturnNull() {
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

        patientRepository.save(patient);

        if (patientService.getPatientBySerialNumber(serial) != null) {
            patientService.deletePatient(serial);
            assertNull(patientService.getPatientBySerialNumber(serial));
        }
    }


    @Test
    void testAdding1_withNotNullRecord_thenReturnObject() {
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

        patientService.addPatient(patient.getSerialNumber(),patient.getName(), patient.getSurname(),
                patient.getDiagnosis(),patient.getDrugs());

        assertEquals("test", patientRepository.findPatientBySerialNumber(serial).getName());

        patientService.deletePatient(serial);
    }

    @Test
    void testShowingAll_thenReturnListAsString() {
        List<Patient> realList = patientService.getPatients();
        List<Patient> expectedList = patientRepository.findAll();

        assertEquals(realList.toString(),expectedList.toString());
    }

    @Test
    void testShowingRecord1_withNotNullOrZeroSerialNumber_thenReturnRecord() {
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

        patientRepository.save(patient);

        assertEquals(patient.getName(),patientService.getPatientBySerialNumber(serial).getName());

        patientService.deletePatient(serial);
    }

    @Test
    void testUpdatingRecord_withNoRequiredParam_thenReturnUpdateRecord() {
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

        patientRepository.save(patient);

        patient = patientService.getPatientBySerialNumber(serial);
        patient.setName("update");

        patientRepository.save(patient);

        assertEquals("update", patientService.getPatientBySerialNumber(serial).getName());

        patientService.deletePatient(serial);
    }
}
