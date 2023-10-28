package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.RegistrationTeam;
import com.bloodbanksystem.springboot.service.RegistrationTeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationTeamControllerTest {

    @InjectMocks
    private RegistrationTeamController registrationTeamController;

    @Mock
    private RegistrationTeamService registrationTeamService;

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


        when(registrationTeamService.getAllRegistrationTeams()).thenReturn(registrationTeams);

        List<RegistrationTeam> result = registrationTeamController.getAllRegistrationTeams();

        assertEquals(registrationTeams.size(), result.size());
        verify(registrationTeamService, times(1)).getAllRegistrationTeams();
    }

    @Test
    public void testGetRegistrationTeamById() {
        int regTeamId = 1;
        RegistrationTeam registrationTeam = new RegistrationTeam();
        registrationTeam.setRegTeamId(regTeamId);

        when(registrationTeamService.getRegistrationTeamById(regTeamId)).thenReturn(registrationTeam);

        ResponseEntity<RegistrationTeam> response = registrationTeamController.getRegistrationTeamById(regTeamId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(registrationTeam, response.getBody());
        verify(registrationTeamService, times(1)).getRegistrationTeamById(regTeamId);
    }

    @Test
    public void testGetRegistrationTeamByIdNotFound() {
        int regTeamId = 1;

        when(registrationTeamService.getRegistrationTeamById(regTeamId)).thenReturn(null);

        ResponseEntity<RegistrationTeam> response = registrationTeamController.getRegistrationTeamById(regTeamId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(registrationTeamService, times(1)).getRegistrationTeamById(regTeamId);
    }

    @Test
    public void testCreateRegistrationTeam() {
        RegistrationTeam registrationTeam = new RegistrationTeam();
        registrationTeam.setRegTeamId(1);

        when(registrationTeamService.createRegistrationTeam(any(RegistrationTeam.class))).thenReturn(registrationTeam);

        ResponseEntity<RegistrationTeam> response = registrationTeamController.createRegistrationTeam(registrationTeam);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(registrationTeam, response.getBody());
        verify(registrationTeamService, times(1)).createRegistrationTeam(any(RegistrationTeam.class));
    }

    @Test
    public void testUpdateRegistrationTeam() {
        int regTeamId = 1;
        RegistrationTeam registrationTeam = new RegistrationTeam();
        registrationTeam.setRegTeamId(regTeamId);

        when(registrationTeamService.updateRegistrationTeam(eq(regTeamId), any(RegistrationTeam.class))).thenReturn(registrationTeam);

        ResponseEntity<RegistrationTeam> response = registrationTeamController.updateRegistrationTeam(regTeamId, registrationTeam);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(registrationTeam, response.getBody());
        verify(registrationTeamService, times(1)).updateRegistrationTeam(eq(regTeamId), any(RegistrationTeam.class));
    }

    @Test
    public void testUpdateRegistrationTeamNotFound() {
        int regTeamId = 1;
        RegistrationTeam registrationTeam = new RegistrationTeam();

        when(registrationTeamService.updateRegistrationTeam(eq(regTeamId), any(RegistrationTeam.class))).thenReturn(null);

        ResponseEntity<RegistrationTeam> response = registrationTeamController.updateRegistrationTeam(regTeamId, registrationTeam);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(registrationTeamService, times(1)).updateRegistrationTeam(eq(regTeamId), any(RegistrationTeam.class));
    }

    @Test
    public void testDeleteRegistrationTeam() {
        int regTeamId = 1;

        ResponseEntity<Void> response = registrationTeamController.deleteRegistrationTeam(regTeamId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(registrationTeamService, times(1)).deleteRegistrationTeam(regTeamId);
    }
}
