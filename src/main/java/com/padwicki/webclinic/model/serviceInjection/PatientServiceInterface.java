package com.padwicki.webclinic.model.serviceInjection;


import com.padwicki.webclinic.domain.entity.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientServiceInterface {
        public List<Patient> getPatients();

        public Patient getPatientBySerialNumber(int serialNumber);

        public void addPatient(int serialNumber, String name, String surname,
                               String diagnostic, String drugs);
        public void updatePatient(int oldSerialNumber, int newSerialNumber,
                                  String newName, String newSurname,
                                  String newDiagnostic, String newDrugs);
}
