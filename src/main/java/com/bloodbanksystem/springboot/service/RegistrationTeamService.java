package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.RegistrationTeam;
import com.bloodbanksystem.springboot.repository.RegistrationTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationTeamService {
    @Autowired
    RegistrationTeamRepository registrationTeamRepository;

    public List<RegistrationTeam> getAllRegistrationTeams() {
        return registrationTeamRepository.findAll();
    }

    public RegistrationTeam getRegistrationTeamById(int id) {
        Optional<RegistrationTeam> registrationTeamOptional = registrationTeamRepository.findById(id);
        return registrationTeamOptional.orElse(null);
    }

    public RegistrationTeam createRegistrationTeam(RegistrationTeam registrationTeam) {
        return registrationTeamRepository.save(registrationTeam);
    }

    public RegistrationTeam updateRegistrationTeam(int id, RegistrationTeam registrationTeam) {
        if (registrationTeamRepository.existsById(id)) {
            registrationTeam.setRegTeamId(id);
            return registrationTeamRepository.save(registrationTeam);
        }
        return null;
    }

    public void deleteRegistrationTeam(int id) {
        registrationTeamRepository.deleteById(id);
    }
}
