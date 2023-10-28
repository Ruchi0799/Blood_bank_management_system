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

@Entity
@Table(name = "manager")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ManagerId")
    private int managerId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Location")
    private String location;

    @Column(name = "BloodBankId")
    private Integer bloodBankId;

}
