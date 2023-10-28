package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Hospital;
import com.bloodbanksystem.springboot.service.HospitalService;
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
public class HospitalControllerTest {

    @InjectMocks
    private HospitalController hospitalController;

    @Mock
    private HospitalService hospitalService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllHospitals() {
        int hospitalId = 1;
        Hospital hospital = new Hospital();
        hospital.setHospitalId(hospitalId);

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hospital);

        when(hospitalService.getAllHospitals()).thenReturn(hospitals);

        List<Hospital> result = hospitalController.getAllHospitals();

        assertEquals(hospitals.size(), result.size());
        verify(hospitalService, times(1)).getAllHospitals();
    }

    @Test
    public void testGetHospitalById() {
        int hospitalId = 1;
        Hospital hospital = new Hospital();
        hospital.setHospitalId(hospitalId);

        when(hospitalService.getHospitalById(hospitalId)).thenReturn(hospital);

        ResponseEntity<Hospital> response = hospitalController.getHospitalById(hospitalId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospital, response.getBody());
        verify(hospitalService, times(1)).getHospitalById(hospitalId);
    }

    @Test
    public void testGetHospitalByIdNotFound() {
        int hospitalId = 1;

        when(hospitalService.getHospitalById(hospitalId)).thenReturn(null);

        ResponseEntity<Hospital> response = hospitalController.getHospitalById(hospitalId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(hospitalService, times(1)).getHospitalById(hospitalId);
    }

    @Test
    public void testCreateHospital() {
        Hospital hospital = new Hospital();
        hospital.setHospitalId(1);

        when(hospitalService.createHospital(any(Hospital.class))).thenReturn(hospital);

        ResponseEntity<Hospital> response = hospitalController.createHospital(hospital);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(hospital, response.getBody());
        verify(hospitalService, times(1)).createHospital(any(Hospital.class));
    }

    @Test
    public void testUpdateHospital() {
        int hospitalId = 1;
        Hospital hospital = new Hospital();
        hospital.setHospitalId(hospitalId);

        when(hospitalService.updateHospital(eq(hospitalId), any(Hospital.class))).thenReturn(hospital);

        ResponseEntity<Hospital> response = hospitalController.updateHospital(hospitalId, hospital);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospital, response.getBody());
        verify(hospitalService, times(1)).updateHospital(eq(hospitalId), any(Hospital.class));
    }

    @Test
    public void testUpdateHospitalNotFound() {
        int hospitalId = 1;

        when(hospitalService.updateHospital(eq(hospitalId), any(Hospital.class))).thenReturn(null);

        ResponseEntity<Hospital> response = hospitalController.updateHospital(hospitalId, new Hospital());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(hospitalService, times(1)).updateHospital(eq(hospitalId), any(Hospital.class));
    }

    @Test
    public void testDeleteHospital() {
        int hospitalId = 1;

        ResponseEntity<Void> response = hospitalController.deleteHospital(hospitalId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(hospitalService, times(1)).deleteHospital(hospitalId);
    }
}
