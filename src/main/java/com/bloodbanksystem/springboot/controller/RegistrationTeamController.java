package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.RegistrationTeam;
import com.bloodbanksystem.springboot.service.RegistrationTeamService;
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
@RequestMapping("/registration-team")
@Api(tags = "Registration team APIs")
public class RegistrationTeamController {

    @Autowired
    RegistrationTeamService registrationTeamService;
    @GetMapping
    public List<RegistrationTeam> getAllRegistrationTeams() {
        return registrationTeamService.getAllRegistrationTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationTeam> getRegistrationTeamById(@PathVariable int id) {
        RegistrationTeam registrationTeam = registrationTeamService.getRegistrationTeamById(id);
        if (registrationTeam != null) {
            return new ResponseEntity<>(registrationTeam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RegistrationTeam> createRegistrationTeam(@RequestBody RegistrationTeam registrationTeam) {
        RegistrationTeam createdRegistrationTeam = registrationTeamService.createRegistrationTeam(registrationTeam);
        return new ResponseEntity<>(createdRegistrationTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationTeam> updateRegistrationTeam(@PathVariable int id, @RequestBody RegistrationTeam registrationTeam) {
        RegistrationTeam updatedRegistrationTeam = registrationTeamService.updateRegistrationTeam(id, registrationTeam);
        if (updatedRegistrationTeam != null) {
            return new ResponseEntity<>(updatedRegistrationTeam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistrationTeam(@PathVariable int id) {
        registrationTeamService.deleteRegistrationTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
