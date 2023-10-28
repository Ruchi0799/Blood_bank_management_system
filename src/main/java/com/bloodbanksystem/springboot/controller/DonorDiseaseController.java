package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.DonorDisease;
import com.bloodbanksystem.springboot.entity.DonorDiseaseDTO;
import com.bloodbanksystem.springboot.entity.DonorDiseaseId;
import com.bloodbanksystem.springboot.service.DonorDiseaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donor-disease")
@Api(tags = "Donor Disease APIs")
public class DonorDiseaseController {

    @Autowired
    DonorDiseaseService donorDiseaseService;

    @GetMapping
    public List<DonorDisease> getAllDonorDiseaseEntries() {
        return donorDiseaseService.getAllDonorDiseaseEntries();
    }

    @GetMapping("/{donorId}")
    public ResponseEntity<DonorDisease> getDonorDiseaseEntry(@PathVariable Long donorId) {
        DonorDisease donorDisease = donorDiseaseService.getDonorDiseaseEntry(donorId);
        if (donorDisease != null) {
            return new ResponseEntity<>(donorDisease, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DonorDisease> createDonorDiseaseEntry(@RequestBody DonorDiseaseDTO donorDisease) {
        DonorDiseaseId donorDiseaseId= new DonorDiseaseId();
        donorDiseaseId.setDonorId(donorDisease.getDonorId());
        donorDiseaseId.setDisease(donorDisease.getDisease());
        DonorDisease donorDisease1 = new DonorDisease();
        donorDisease1.setDonorDiseaseId(donorDiseaseId);
        DonorDisease createdDonorDisease = donorDiseaseService.createDonorDiseaseEntry(donorDisease1);
        return new ResponseEntity<>(createdDonorDisease, HttpStatus.CREATED);
    }

    @DeleteMapping("/{donorId}/{disease}")
    public ResponseEntity<Void> deleteDonorDiseaseEntry(@PathVariable Long donorId, @PathVariable String disease) {
        donorDiseaseService.deleteDonorDiseaseEntry(donorId, disease);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
