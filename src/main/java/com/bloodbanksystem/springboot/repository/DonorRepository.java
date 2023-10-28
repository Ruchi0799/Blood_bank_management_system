package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {
    // You can add custom queries if needed
}
