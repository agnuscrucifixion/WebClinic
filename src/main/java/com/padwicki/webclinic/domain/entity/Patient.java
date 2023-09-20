package com.padwicki.webclinic.domain.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "id",nullable = false, unique = true)
    @SequenceGenerator(name = "patientsIdSeq", sequenceName = "patients_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patientsIdSeq")
    Long id;

    @Column(name = "serial_number",nullable = false,unique = true)
    int serialNumber;

    @Column(name = "name",nullable = false)
    String name;

    @Column(name = "surname",nullable = false)
    String surname;

    @Column(name = "diagnosis",nullable = false)
    String diagnosis;

    @Column(name = "coming_date",nullable = false)
    Timestamp comingDate;

    @Column(name = "prescription_drugs",nullable = false)
    String drugs;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Timestamp getComingDate() {
        return comingDate;
    }

    public void setComingDate(Timestamp comingDate) {
        this.comingDate = comingDate;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }
}
