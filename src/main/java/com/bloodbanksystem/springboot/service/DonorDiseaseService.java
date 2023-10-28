package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.DonorDisease;
import com.bloodbanksystem.springboot.entity.DonorDiseaseId;
import com.bloodbanksystem.springboot.repository.DonorDiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorDiseaseService {

    @Autowired
    DonorDiseaseRepository donorDiseaseRepository;

    public List<DonorDisease> getAllDonorDiseaseEntries() {
        return donorDiseaseRepository.findAll();
    }

    public DonorDisease getDonorDiseaseEntry(Long donorId) {
        //DonorDiseaseId id = new DonorDiseaseId(donorId, disease);
        DonorDisease donorDiseaseOptional = donorDiseaseRepository.findByDonorId(donorId);
        return donorDiseaseOptional;
    }

    public DonorDisease createDonorDiseaseEntry(DonorDisease donorDisease) {
        return donorDiseaseRepository.save(donorDisease);
    }

    public void deleteDonorDiseaseEntry(Long donorId, String disease) {
        DonorDiseaseId id = new DonorDiseaseId(donorId, disease);
        donorDiseaseRepository.deleteById(id);
    }
}
