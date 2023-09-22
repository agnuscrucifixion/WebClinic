package com.padwicki.webclinic.api.dto;

import lombok.Data;

@Data
public class AddPatientRqDTO {
    String serialNumber;
    String name;
    String surname;
    String diagnosis;
    String drugs;
}
