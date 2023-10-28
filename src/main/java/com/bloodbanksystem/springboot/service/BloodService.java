package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Blood;
import com.bloodbanksystem.springboot.repository.BloodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodService {
    @Autowired
    BloodRepository bloodRepository;

    // Get blood by ID
    public Optional<Blood> getBloodById(Integer id) {
        return bloodRepository.findById(id);
    }

    // Create a new blood entry
    public Blood createBlood(Blood blood) {
        return bloodRepository.save(blood);
    }

    // Update blood by ID
    public Blood updateBlood(Integer id, Blood updatedBlood) {
        if (bloodRepository.existsById(id)) {
            updatedBlood.setBloodId(id);
            return bloodRepository.save(updatedBlood);
        }
        return null; // Blood with the specified ID not found
    }

    // Delete blood by ID
    public void deleteBlood(Integer id) {
        bloodRepository.deleteById(id);
    }

    // Get all blood entries (optional)
    public List<Blood> getAllBlood() {
        return bloodRepository.findAll();
    }

    public List<Blood> getBloodGroupCountByBloodBankId(Long bloodBankId) {
        return bloodRepository.getBloodGroupCountByBloodBankId(Math.toIntExact(bloodBankId));
    }
}
