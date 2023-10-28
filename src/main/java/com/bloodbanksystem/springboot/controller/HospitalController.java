package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Hospital;
import com.bloodbanksystem.springboot.service.HospitalService;
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
@RequestMapping("/hospital")
@Api(tags = "Hospital APIs")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable int id) {
        Hospital hospital = hospitalService.getHospitalById(id);
        if (hospital != null) {
            return new ResponseEntity<>(hospital, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        Hospital createdHospital = hospitalService.createHospital(hospital);
        return new ResponseEntity<>(createdHospital, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable int id, @RequestBody Hospital hospital) {
        Hospital updatedHospital = hospitalService.updateHospital(id, hospital);
        if (updatedHospital != null) {
            return new ResponseEntity<>(updatedHospital, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
