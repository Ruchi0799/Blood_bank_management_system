package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Donor;
import com.bloodbanksystem.springboot.service.DonorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donor")
@Api(tags = "Donor APIs")
public class DonorController {
    @Autowired
    DonorService donorService;

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable int id) {
        Donor donor = donorService.getDonorById(id);
        if (donor != null) {
            return new ResponseEntity<>(donor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Donor> createDonor(@RequestBody Donor donor) {
        Donor createdDonor = donorService.createDonor(donor);
        return new ResponseEntity<>(createdDonor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable int id, @RequestBody Donor donor) {
        Donor updatedDonor = donorService.updateDonor(id, donor);
        if (updatedDonor != null) {
            return new ResponseEntity<>(updatedDonor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable int id) {
        donorService.deleteDonor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
