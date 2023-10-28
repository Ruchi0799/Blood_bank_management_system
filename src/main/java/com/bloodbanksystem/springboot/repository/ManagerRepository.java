package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    // You can add custom queries if needed
}
