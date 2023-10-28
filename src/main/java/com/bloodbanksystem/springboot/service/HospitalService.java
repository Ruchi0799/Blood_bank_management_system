package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Hospital;
import com.bloodbanksystem.springboot.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(int id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        return hospitalOptional.orElse(null);
    }

    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Hospital updateHospital(int id, Hospital hospital) {
        if (hospitalRepository.existsById(id)) {
            hospital.setHospitalId(id);
            return hospitalRepository.save(hospital);
        }
        return null;
    }

    public void deleteHospital(int id) {
        hospitalRepository.deleteById(id);
    }
}
