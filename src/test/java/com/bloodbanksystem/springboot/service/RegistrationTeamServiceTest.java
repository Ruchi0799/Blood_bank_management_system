package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.RegistrationTeam;
import com.bloodbanksystem.springboot.repository.RegistrationTeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationTeamServiceTest {

    @InjectMocks
    private RegistrationTeamService registrationTeamService;

    @Mock
    private RegistrationTeamRepository registrationTeamRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllRegistrationTeams() {
        int regTeamId = 1;
        RegistrationTeam registrationTeam = new RegistrationTeam();
        registrationTeam.setRegTeamId(regTeamId);
        List<RegistrationTeam> registrationTeams = new ArrayList<>();
        registrationTeams.add(registrationTeam);

        when(registrationTeamRepository.findAll()).thenReturn(registrationTeams);

        List<RegistrationTeam> result = registrationTeamService.getAllRegistrationTeams();

        // Add assertions to verify the result
    }

    @Test
    public void testGetRegistrationTeamById() {
        int regTeamId = 1;
        RegistrationTeam registrationTeam = new RegistrationTeam();
        registrationTeam.setRegTeamId(regTeamId);

        when(registrationTeamRepository.findById(regTeamId)).thenReturn(Optional.of(registrationTeam));

        RegistrationTeam result = registrationTeamService.getRegistrationTeamById(regTeamId);

        // Add assertions to verify the result
    }

    @Test
    public void testCreateRegistrationTeam() {
        RegistrationTeam registrationTeam = new RegistrationTeam();

        when(registrationTeamRepository.save(any(RegistrationTeam.class))).thenReturn(registrationTeam);

        RegistrationTeam result = registrationTeamService.createRegistrationTeam(registrationTeam);

        // Add assertions to verify the result
    }

    @Test
    public void testUpdateRegistrationTeam() {
        int regTeamId = 1;
        RegistrationTeam registrationTeam = new RegistrationTeam();
        registrationTeam.setRegTeamId(regTeamId);

        when(registrationTeamRepository.existsById(regTeamId)).thenReturn(true);
        when(registrationTeamRepository.save(any(RegistrationTeam.class))).thenReturn(registrationTeam);

        RegistrationTeam result = registrationTeamService.updateRegistrationTeam(regTeamId, registrationTeam);

        // Add assertions to verify the result
    }

    @Test
    public void testUpdateRegistrationTeamNotFound() {
        int regTeamId = 1;
        RegistrationTeam registrationTeam = new RegistrationTeam();

        when(registrationTeamRepository.existsById(regTeamId)).thenReturn(false);

        RegistrationTeam result = registrationTeamService.updateRegistrationTeam(regTeamId, registrationTeam);

        // Add assertions to verify the result
    }

    @Test
    public void testDeleteRegistrationTeam() {
        int regTeamId = 1;

        registrationTeamService.deleteRegistrationTeam(regTeamId);

        // Add assertions to verify that the delete operation was called
    }
}
