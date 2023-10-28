package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.HospitalBloodContact;
import com.bloodbanksystem.springboot.entity.HospitalBloodContactDTO;
import com.bloodbanksystem.springboot.entity.HospitalBloodContactId;
import com.bloodbanksystem.springboot.service.HospitalBloodContactService;
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
@RequestMapping("/hospital-blood-contact")
@Api(tags = "Hospital Blood Contact APIs")
public class HospitalBloodContactController {

    @Autowired
    HospitalBloodContactService hospitalBloodContactService;

    @GetMapping("/all")
    public List<HospitalBloodContact> getAllHospitalBloodContacts() {
        return hospitalBloodContactService.getAllHospitalBloodContacts();
    }

    @PostMapping("/create")
    public HospitalBloodContact createHospitalBloodContact(@RequestBody HospitalBloodContactDTO hospitalBloodContact) {
        HospitalBloodContactId hospitalBloodContactId= new HospitalBloodContactId();
        hospitalBloodContactId.setHospitalId(hospitalBloodContact.getHospitalId());
        hospitalBloodContactId.setBloodBankId(hospitalBloodContact.getBloodBankId());
        HospitalBloodContact hospitalBloodContact1 = new HospitalBloodContact();
        hospitalBloodContact1.setId(hospitalBloodContactId);
        return hospitalBloodContactService.createHospitalBloodContact(hospitalBloodContact1);
    }

    @GetMapping("/{hospitalId}/{bloodBankId}")
    public ResponseEntity<HospitalBloodContact> getHospitalBloodContact(@PathVariable int hospitalId, @PathVariable int bloodBankId) {
        HospitalBloodContact hospitalBloodContact = hospitalBloodContactService.getHospitalBloodContact(Long.valueOf(hospitalId), Long.valueOf(bloodBankId));
        if (hospitalBloodContact != null) {
            return new ResponseEntity<>(hospitalBloodContact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{hospitalId}/{bloodBankId}")
    public ResponseEntity<Void> deleteHospitalBloodContact(@PathVariable int hospitalId, @PathVariable int bloodBankId) {
        hospitalBloodContactService.deleteHospitalBloodContact(Long.valueOf(hospitalId), Long.valueOf(bloodBankId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
