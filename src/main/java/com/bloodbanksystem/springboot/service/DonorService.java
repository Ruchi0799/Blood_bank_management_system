package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Donor;
import com.bloodbanksystem.springboot.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DonorService {
    @Autowired
    DonorRepository donorRepository;
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor getDonorById(int id) {
        Optional<Donor> donorOptional = donorRepository.findById(id);
        return donorOptional.orElse(null);
    }

    public Donor createDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public Donor updateDonor(int id, Donor donor) {
        if (donorRepository.existsById(id)) {
            donor.setDonorId(id);
            return donorRepository.save(donor);
        }
        return null;
    }

    public void deleteDonor(int id) {
        donorRepository.deleteById(id);
    }

}
