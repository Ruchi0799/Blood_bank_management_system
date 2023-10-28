package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.DonorBlood;
import com.bloodbanksystem.springboot.entity.DonorBloodDTO;
import com.bloodbanksystem.springboot.entity.DonorBloodId;
import com.bloodbanksystem.springboot.service.DonorBloodService;
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
@RequestMapping("/donor-blood")
@Api(tags = "Donor Blood Bank APIs")
public class DonorBloodController {

    @Autowired
    DonorBloodService donorBloodService;

    @GetMapping
    public List<DonorBlood> getAllDonorBloodEntries() {
        return donorBloodService.getAllDonorBloodEntries();
    }

    @GetMapping("/{donorId}/{bloodId}")
    public ResponseEntity<DonorBlood> getDonorBloodEntry(@PathVariable int donorId, @PathVariable int bloodId) {
        DonorBlood donorBlood = donorBloodService.getDonorBloodEntry(Long.valueOf(donorId), Long.valueOf(bloodId));
        if (donorBlood != null) {
            return new ResponseEntity<>(donorBlood, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/create")
    public DonorBlood createHospitalBloodContact(@RequestBody DonorBloodDTO donorBlood) {
                DonorBloodId donorBloodId=new DonorBloodId();
       donorBloodId.setBloodId(donorBlood.getBloodId());
        donorBloodId.setDonorId(donorBlood.getDonorId());
        DonorBlood donorBlood1 = new DonorBlood();
        donorBlood1.setId(donorBloodId);
        DonorBlood createdDonorBlood = donorBloodService.createDonorBloodEntry(donorBlood1);
        return createdDonorBlood;
    }


    @DeleteMapping("/{donorId}/{bloodId}")
    public ResponseEntity<Void> deleteDonorBloodEntry(@PathVariable Long donorId, @PathVariable Long bloodId) {
        donorBloodService.deleteDonorBloodEntry(donorId, bloodId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
