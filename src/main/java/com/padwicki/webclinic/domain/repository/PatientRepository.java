package com.padwicki.webclinic.domain.repository;

import com.padwicki.webclinic.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientBySerialNumber(int serialNumber);

}
