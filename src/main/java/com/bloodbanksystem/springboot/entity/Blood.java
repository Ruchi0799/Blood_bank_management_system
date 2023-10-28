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
@Table(name = "blood")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BloodId")
    private Integer bloodId;

    @Column(name = "BloodGroup")
    private String bloodGroup;

    @Column(name = "Status")
    private String status;

    @Column(name = "QuantityOfBlood")
    private String quantityOfBlood;

    @Column(name = "DateOfDonation")
    private Date dateOfDonation;

    @Column(name = "BloodBankId")
    private Integer bloodBankId;
}

