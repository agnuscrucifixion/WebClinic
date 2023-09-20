package com.padwicki.webclinic.model.serviceInjection;


import com.padwicki.webclinic.domain.entity.Patient;

import java.util.List;

public interface PatientServiceInterface {
        public List<Patient> getPatients();

        public Patient getPatientBySerialNumber(int serialNumber);
}
