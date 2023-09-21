package com.padwicki.webclinic.domain.repository;

import com.padwicki.webclinic.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing the database and the "patients" table.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    /**
     * Hibenate finds a patient with a given serial number in the table.
     * @param serialNumber patient's Serial Number must be greater than 0 and less than 2147483647.
     * @return Patient class object or null
     */
    Patient findPatientBySerialNumber(int serialNumber);
}
