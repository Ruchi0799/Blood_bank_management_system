package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.BloodBank;
import com.bloodbanksystem.springboot.repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodBankService {

    @Autowired
    BloodBankRepository bloodBankRepository;

    public List<BloodBank> getAllBloodBanks() {
        return bloodBankRepository.findAll();
    }


    public BloodBank getBloodBankById(int id) {
        Optional<BloodBank> bloodBankOptional = bloodBankRepository.findById(id);
        return bloodBankOptional.orElse(null);
    }


    public BloodBank createBloodBank(BloodBank bloodBank) {
        return bloodBankRepository.save(bloodBank);
    }


    public BloodBank updateBloodBank(int id, BloodBank bloodBank) {
        if (bloodBankRepository.existsById(id)) {
            bloodBank.setBloodBankId(id);
            return bloodBankRepository.save(bloodBank);
        }
        return null;
    }


    public void deleteBloodBank(int id) {
        bloodBankRepository.deleteById(id);
    }
}
