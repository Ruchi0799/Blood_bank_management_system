package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.BloodBank;
import com.bloodbanksystem.springboot.service.BloodBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/bloodbanks")
@Api(tags = "Blood Bank APIs")
public class BloodBankController {
    private final BloodBankService bloodBankService;

    @Autowired
    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService = bloodBankService;
    }

    @ApiOperation("Get all Blood Bank")
    @GetMapping
    public List<BloodBank> getAllBloodBanks() {
        return bloodBankService.getAllBloodBanks();
    }

    @ApiOperation("Get Blood Bank By id")
    @GetMapping("/{id}")
    public ResponseEntity<BloodBank> getBloodBankById(@PathVariable int id) {
        BloodBank bloodBank = bloodBankService.getBloodBankById(id);
        if (bloodBank != null) {
            return new ResponseEntity<>(bloodBank, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Create Blood Bank")
    @PostMapping("/create")
    public ResponseEntity<BloodBank> createBloodBank(@RequestBody BloodBank bloodBank) {
        BloodBank createdBloodBank = bloodBankService.createBloodBank(bloodBank);
        return new ResponseEntity<>(createdBloodBank, HttpStatus.CREATED);
    }

    @ApiOperation("Update Blood Bank")
    @PutMapping("/{id}")
    public ResponseEntity<BloodBank> updateBloodBank(@PathVariable int id, @RequestBody BloodBank bloodBank) {
        BloodBank updatedBloodBank = bloodBankService.updateBloodBank(id, bloodBank);
        if (updatedBloodBank != null) {
            return new ResponseEntity<>(updatedBloodBank, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete Blood Bank")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodBank(@PathVariable int id) {
        bloodBankService.deleteBloodBank(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
