package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.DonorBlood;
import com.bloodbanksystem.springboot.entity.DonorBloodId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorBloodRepository extends JpaRepository<DonorBlood, DonorBloodId> {
    // You can add custom queries if needed
}
