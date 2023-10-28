package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Blood;
import com.bloodbanksystem.springboot.service.BloodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BloodControllerTest {

    @InjectMocks
    private BloodController bloodController;

    @Mock
    private BloodService bloodService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetBloodById() {
        int bloodId = 1;
        Blood blood = new Blood(bloodId, "Blood Group A", "Available", "500ml", new Date(), 1);

        when(bloodService.getBloodById(bloodId)).thenReturn(Optional.of(blood));

        ResponseEntity<Blood> response = bloodController.getBloodById(bloodId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blood, response.getBody());
        verify(bloodService, times(1)).getBloodById(bloodId);
    }

    @Test
    public void testGetBloodByIdNotFound() {
        int bloodId = 1;

        when(bloodService.getBloodById(bloodId)).thenReturn(Optional.empty());

        ResponseEntity<Blood> response = bloodController.getBloodById(bloodId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bloodService, times(1)).getBloodById(bloodId);
    }

    @Test
    public void testCreateBlood() {
        Blood blood = new Blood(1, "Blood Group A", "Available", "500ml", new Date(), 1);

        when(bloodService.createBlood(blood)).thenReturn(blood);

        ResponseEntity<Blood> response = bloodController.createBlood(blood);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(blood, response.getBody());
        verify(bloodService, times(1)).createBlood(blood);
    }

    @Test
    public void testUpdateBlood() {
        int bloodId = 1;
        Blood blood = new Blood(bloodId, "Blood Group A", "Available", "500ml", new Date(), 1);

        when(bloodService.updateBlood(eq(bloodId), any(Blood.class))).thenReturn(blood);

        ResponseEntity<Blood> response = bloodController.updateBlood(bloodId, new Blood(1, "New Blood Group A", "Available", "500ml", new Date(), 1));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blood, response.getBody());
        verify(bloodService, times(1)).updateBlood(eq(bloodId), any(Blood.class));
    }

    @Test
    public void testUpdateBloodNotFound() {
        int bloodId = 1;

        when(bloodService.updateBlood(eq(bloodId), any(Blood.class))).thenReturn(null);

        ResponseEntity<Blood> response = bloodController.updateBlood(bloodId, new Blood(1, "New Blood Group A", "Available", "500ml", new Date(), 1));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bloodService, times(1)).updateBlood(eq(bloodId), any(Blood.class));
    }
}
