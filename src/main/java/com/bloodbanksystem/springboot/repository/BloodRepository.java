package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.Blood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BloodRepository extends JpaRepository<Blood, Integer> {

//    @Procedure(name = "GetBloodGroupCountByBloodBankId")
//    List<Object[]> getBloodGroupCountByBloodBankId(@Param("bloodBankIdParam") int bloodBankId);
//    // You can define custom queries here if needed

    @Query(value = "CALL GetAvailableBloodByBloodBankId(:bloodBankIdParam);", nativeQuery = true)
    List<Blood> getBloodGroupCountByBloodBankId(@Param("bloodBankIdParam") int bloodBankId);
}
