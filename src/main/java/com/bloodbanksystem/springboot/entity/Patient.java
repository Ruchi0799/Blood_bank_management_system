package com.bloodbanksystem.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientId")
    private int patientId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Contact")
    private String contact;

    @Column(name = "BloodGroup")
    private String bloodGroup;

    @Column(name = "Address")
    private String address;

    @Column(name = "DateOfIntake")
    private Date dateOfIntake;

    @Column(name = "RegTeamId")
    private Integer regTeamId;

    @Column(name = "BloodId")
    private Integer bloodId;


}
