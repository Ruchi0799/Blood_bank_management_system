package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // You can add custom queries if needed
}

