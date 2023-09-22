package com.padwicki.webclinic.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * An entity class that imitates a patient and stores the main information about the patient.
 */
@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {

    /**
     * Primary key field.
     */
    @Id
    @Column(name = "id",nullable = false, unique = true)
    @SequenceGenerator(name = "patientsIdSeq", sequenceName = "patients_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patientsIdSeq")
    private Long id;

    /**
     * Patient's serial number.
     * Individual and not repeatable.
     */
    @Column(name = "serial_number",nullable = false,unique = true)
    private int serialNumber;

    /**
     * Patient's name.
     */
    @Column(name = "name",nullable = false)
    private String name;

    /**
     * Patient's surname.
     */
    @Column(name = "surname",nullable = false)
    private String surname;

    /**
     * Patient's diagnosis
     */
    @Column(name = "diagnosis",nullable = false)
    private String diagnosis;

    /**
     * Date and time the patient was admitted.
     */
    @Column(name = "coming_date",nullable = false)
    private Timestamp comingDate;

    /**
     *  Drugs for the patient.
     */
    @Column(name = "prescription_drugs",nullable = false)
    private String drugs;

    /**
     * Empty entity constructor.
     */
    public Patient() {
    }

    /**
     * Entity constructor
     * @param serialNumber serial number if patient.
     * @param name name if patient.
     * @param surname surname if patient.
     * @param diagnosis diagnosis if patient.
     * @param drugs drugs if patient.
     */
    public Patient(int serialNumber, String name, String surname, String diagnosis, String drugs) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.surname = surname;
        this.diagnosis = diagnosis;
        this.drugs = drugs;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", comingDate=" + comingDate +
                ", drugs='" + drugs + '\'' +
                '}';
    }
}
