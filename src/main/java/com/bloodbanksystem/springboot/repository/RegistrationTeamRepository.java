package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.RegistrationTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationTeamRepository extends JpaRepository<RegistrationTeam, Integer> {
    // You can add custom queries if needed
}

