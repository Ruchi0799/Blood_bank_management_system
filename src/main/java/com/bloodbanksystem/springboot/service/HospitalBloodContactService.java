package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.HospitalBloodContact;
import com.bloodbanksystem.springboot.entity.HospitalBloodContactId;
import com.bloodbanksystem.springboot.repository.HospitalBloodContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalBloodContactService {

    @Autowired
    HospitalBloodContactRepository hospitalBloodContactRepository;

    public List<HospitalBloodContact> getAllHospitalBloodContacts() {
        return hospitalBloodContactRepository.findAll();
    }

    public HospitalBloodContact createHospitalBloodContact(HospitalBloodContact hospitalBloodContact) {
        return hospitalBloodContactRepository.save(hospitalBloodContact);
    }

    public HospitalBloodContact getHospitalBloodContact(Long hospitalId, Long bloodBankId) {
        HospitalBloodContactId id = new HospitalBloodContactId(hospitalId, bloodBankId);
        Optional<HospitalBloodContact> result = hospitalBloodContactRepository.findById(id);
        return result.orElse(null);
    }

    // Delete HospitalBloodContact by HospitalId and BloodBankId
    public void deleteHospitalBloodContact(Long hospitalId, Long bloodBankId) {
        HospitalBloodContactId id = new HospitalBloodContactId(hospitalId, bloodBankId);
        hospitalBloodContactRepository.deleteById(id);

    }
}
