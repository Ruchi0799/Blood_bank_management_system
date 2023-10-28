package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.DonorBlood;
import com.bloodbanksystem.springboot.entity.DonorBloodId;
import com.bloodbanksystem.springboot.repository.DonorBloodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorBloodService {
    @Autowired
    DonorBloodRepository donorBloodRepository;
    public List<DonorBlood> getAllDonorBloodEntries() {
        return donorBloodRepository.findAll();
    }

    public DonorBlood getDonorBloodEntry(Long donorId, Long bloodId) {
        DonorBloodId id = new DonorBloodId(donorId, bloodId);
        Optional<DonorBlood> donorBloodOptional = donorBloodRepository.findById(id);
        return donorBloodOptional.orElse(null);
    }

    public DonorBlood createDonorBloodEntry(DonorBlood donorBlood) {
        return donorBloodRepository.save(donorBlood);
    }

    public void deleteDonorBloodEntry(Long donorId, Long bloodId) {
        DonorBloodId id = new DonorBloodId(Long.valueOf(donorId), Long.valueOf(bloodId));
        donorBloodRepository.deleteById(id);
    }
}
