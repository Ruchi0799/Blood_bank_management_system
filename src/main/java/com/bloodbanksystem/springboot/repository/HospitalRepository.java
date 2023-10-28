package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    // You can add custom queries if needed
}
