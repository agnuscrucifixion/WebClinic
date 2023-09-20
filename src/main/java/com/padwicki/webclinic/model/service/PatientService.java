package com.padwicki.webclinic.model.service;

import com.padwicki.webclinic.domain.entity.Patient;
import com.padwicki.webclinic.model.serviceInjection.PatientServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements PatientServiceInterface {

    @Override
    public List<Patient> getPatients() {
        return null;
    }

    @Override
    public Patient getPatientBySerialNumber(int serialNumber) {
        return null;
    }
}
