package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.DonorDisease;
import com.bloodbanksystem.springboot.entity.DonorDiseaseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorDiseaseRepository extends JpaRepository<DonorDisease, DonorDiseaseId> {

    @Query(value = "SELECT * FROM donordisease u WHERE u.DonorId = ?1",
            nativeQuery = true)
    DonorDisease findByDonorId(Long donorId);
    // You can add custom queries if needed
}
