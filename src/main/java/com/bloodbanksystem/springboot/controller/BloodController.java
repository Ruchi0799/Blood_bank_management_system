package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Blood;
import com.bloodbanksystem.springboot.service.BloodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blood")
@Api(tags = "Blood APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BloodController {

    @Autowired
    BloodService bloodService;

    @GetMapping("/{id}")
    public ResponseEntity<Blood> getBloodById(@PathVariable Integer id) {
        Optional<Blood> blood = bloodService.getBloodById(id);
        return blood.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/insert")
    public ResponseEntity<Blood> createBlood(@RequestBody Blood blood) {
        Blood createdBlood = bloodService.createBlood(blood);
        return new ResponseEntity<>(createdBlood, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blood> updateBlood(@PathVariable Integer id, @RequestBody Blood blood) {
        Blood updatedBlood = bloodService.updateBlood(id, blood);
        if (updatedBlood != null) {
            return new ResponseEntity<>(updatedBlood, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlood(@PathVariable Integer id) {
        bloodService.deleteBlood(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Optionally, you can add an endpoint to get all blood entries
    @GetMapping("/all")
    public ResponseEntity<List<Blood>> getAllBlood() {
        List<Blood> bloodList = bloodService.getAllBlood();
        return new ResponseEntity<>(bloodList, HttpStatus.OK);
    }

    @GetMapping("/bloodGroupCount")
    public List<Blood> getBloodGroupCountByBloodBankId(@RequestParam("bloodBankId") Long bloodBankId) {
        return bloodService.getBloodGroupCountByBloodBankId(bloodBankId);
    }

}
