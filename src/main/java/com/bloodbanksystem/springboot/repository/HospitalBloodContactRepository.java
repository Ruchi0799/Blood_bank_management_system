package com.bloodbanksystem.springboot.repository;

import com.bloodbanksystem.springboot.entity.HospitalBloodContact;
import com.bloodbanksystem.springboot.entity.HospitalBloodContactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalBloodContactRepository extends JpaRepository<HospitalBloodContact, HospitalBloodContactId> {
}

