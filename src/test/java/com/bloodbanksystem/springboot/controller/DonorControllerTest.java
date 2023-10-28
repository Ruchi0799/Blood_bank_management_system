package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Donor;
import com.bloodbanksystem.springboot.service.DonorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DonorControllerTest {

    @InjectMocks
    private DonorController donorController;

    @Mock
    private DonorService donorService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllDonors() {
        int donorId = 1;
        Donor donor = new Donor();
        donor.setDonorId(donorId);
        donor.setName("John Doe");
        donor.setContact("12345");
        donor.setGender("Male");
        donor.setAddress("123 Main St");
        donor.setDob(new Date());
        donor.setBloodGroup("A+");
        donor.setRegTeamId(1);
        List<Donor> donors = new ArrayList<>();
        donors.add(donor);

        when(donorService.getAllDonors()).thenReturn(donors);

        List<Donor> result = donorController.getAllDonors();

        assertEquals(donors.size(), result.size());
        verify(donorService, times(1)).getAllDonors();
    }

    @Test
    public void testGetDonorById() {
        int donorId = 1;
        Donor donor = new Donor();
        donor.setDonorId(donorId);
        donor.setName("John Doe");
        donor.setContact("12345");
        donor.setGender("Male");
        donor.setAddress("123 Main St");
        donor.setDob(new Date());
        donor.setBloodGroup("A+");
        donor.setRegTeamId(1);

        when(donorService.getDonorById(donorId)).thenReturn(donor);

        ResponseEntity<Donor> response = donorController.getDonorById(donorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(donor, response.getBody());
        verify(donorService, times(1)).getDonorById(donorId);
    }

    @Test
    public void testGetDonorByIdNotFound() {
        int donorId = 1;

        when(donorService.getDonorById(donorId)).thenReturn(null);

        ResponseEntity<Donor> response = donorController.getDonorById(donorId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(donorService, times(1)).getDonorById(donorId);
    }

    @Test
    public void testCreateDonor() {
        Donor donor = new Donor();
        donor.setDonorId(1);
        donor.setName("John Doe");
        donor.setContact("12345");
        donor.setGender("Male");
        donor.setAddress("123 Main St");
        donor.setDob(new Date());
        donor.setBloodGroup("A+");
        donor.setRegTeamId(1);

        when(donorService.createDonor(donor)).thenReturn(donor);

        ResponseEntity<Donor> response = donorController.createDonor(donor);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(donor, response.getBody());
        verify(donorService, times(1)).createDonor(donor);
    }

    @Test
    public void testUpdateDonor() {
        int donorId = 1;
        Donor donor = new Donor();
        donor.setDonorId(donorId);
        donor.setName("John Doe");
        donor.setContact("12345");
        donor.setGender("Male");
        donor.setAddress("123 Main St");
        donor.setDob(new Date());
        donor.setBloodGroup("A+");
        donor.setRegTeamId(1);

        when(donorService.updateDonor(eq(donorId), any(Donor.class))).thenReturn(donor);

        ResponseEntity<Donor> response = donorController.updateDonor(donorId, donor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(donor, response.getBody());
        verify(donorService, times(1)).updateDonor(eq(donorId), any(Donor.class));
    }

    @Test
    public void testUpdateDonorNotFound() {
        int donorId = 1;

        when(donorService.updateDonor(eq(donorId), any(Donor.class))).thenReturn(null);

        ResponseEntity<Donor> response = donorController.updateDonor(donorId, new Donor());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(donorService, times(1)).updateDonor(eq(donorId), any(Donor.class));
    }

    @Test
    public void testDeleteDonor() {
        int donorId = 1;

        ResponseEntity<Void> response = donorController.deleteDonor(donorId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(donorService, times(1)).deleteDonor(donorId);
    }
}
