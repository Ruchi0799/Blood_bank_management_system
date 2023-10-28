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
@Table(name = "donor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DonorId")
    private int donorId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Contact")
    private String contact;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Address")
    private String address;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "BloodGroup")
    private String bloodGroup;

    @Column(name = "RegTeamId")
    private Integer regTeamId;
}
