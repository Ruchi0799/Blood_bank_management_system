package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {
    // You can add custom queries if needed
}
