package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.DonorBlood;
import com.bloodbanksystem.springboot.entity.DonorBloodDTO;
import com.bloodbanksystem.springboot.entity.DonorBloodId;
import com.bloodbanksystem.springboot.service.DonorBloodService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DonorBloodControllerTest {

    @InjectMocks
    private DonorBloodController donorBloodController;

    @Mock
    private DonorBloodService donorBloodService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllDonorBloodEntries() {
        DonorBloodId donorBloodId= new DonorBloodId();
        donorBloodId.setBloodId(Long.valueOf(2));
        donorBloodId.setDonorId(Long.valueOf(1));
        DonorBlood donorBlood = new DonorBlood();
        donorBlood.setId(donorBloodId);
        List<DonorBlood> donorBloodEntries = new ArrayList<>();
        donorBloodEntries.add(donorBlood);

        when(donorBloodService.getAllDonorBloodEntries()).thenReturn(donorBloodEntries);

        List<DonorBlood> result = donorBloodController.getAllDonorBloodEntries();

        assertEquals(donorBloodEntries.size(), result.size());
        verify(donorBloodService, times(1)).getAllDonorBloodEntries();
    }

    @Test
    public void testGetDonorBloodEntry() {
        Long donorId = Long.valueOf(1);
        Long bloodId = Long.valueOf(2);
        DonorBloodId donorBloodId = new DonorBloodId(donorId, bloodId);
        DonorBlood donorBlood = new DonorBlood();
        // Set up the behavior of your service mock to return the donorBlood when getDonorBloodEntry is called.
        when(donorBloodService.getDonorBloodEntry(Long.valueOf(donorId), Long.valueOf(bloodId))).thenReturn(donorBlood);

        ResponseEntity<DonorBlood> response = donorBloodController.getDonorBloodEntry(1, 2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(donorBlood, response.getBody());
        verify(donorBloodService, times(1)).getDonorBloodEntry(Long.valueOf(donorId), Long.valueOf(bloodId));
    }

    @Test
    public void testGetDonorBloodEntryNotFound() {
        int donorId = 1;
        int bloodId = 2;
        // Set up the behavior of your service mock to return null when getDonorBloodEntry is called.
        when(donorBloodService.getDonorBloodEntry(Long.valueOf(donorId), Long.valueOf(bloodId))).thenReturn(null);

        ResponseEntity<DonorBlood> response = donorBloodController.getDonorBloodEntry(donorId, bloodId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(donorBloodService, times(1)).getDonorBloodEntry(Long.valueOf(donorId), Long.valueOf(bloodId));
    }

    @Test
    public void testCreateDonorBloodEntry() {
        DonorBloodDTO donorBloodDTO = new DonorBloodDTO();
        donorBloodDTO.setDonorId(1L);
        donorBloodDTO.setBloodId(2L);
        DonorBlood donorBlood = new DonorBlood();
        donorBlood.setId(new DonorBloodId(1L, 2L));

        when(donorBloodService.createDonorBloodEntry(any(DonorBlood.class))).thenReturn(donorBlood);

        DonorBlood createdDonorBlood = donorBloodController.createHospitalBloodContact(donorBloodDTO);

        assertEquals(donorBlood, createdDonorBlood);
        verify(donorBloodService, times(1)).createDonorBloodEntry(any(DonorBlood.class));
    }

    @Test
    public void testDeleteDonorBloodEntry() {
        Long donorId = 1L;
        Long bloodId = 2L;

        ResponseEntity<Void> response = donorBloodController.deleteDonorBloodEntry(donorId, bloodId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(donorBloodService, times(1)).deleteDonorBloodEntry(donorId, bloodId);
    }
}
