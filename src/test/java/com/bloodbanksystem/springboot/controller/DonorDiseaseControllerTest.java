package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.DonorDisease;
import com.bloodbanksystem.springboot.entity.DonorDiseaseDTO;
import com.bloodbanksystem.springboot.entity.DonorDiseaseId;
import com.bloodbanksystem.springboot.service.DonorDiseaseService;
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
public class DonorDiseaseControllerTest {

    @InjectMocks
    private DonorDiseaseController donorDiseaseController;

    @Mock
    private DonorDiseaseService donorDiseaseService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllDonorDiseaseEntries() {
        Long donorId = 1L;
        DonorDiseaseId donorDiseaseId = new DonorDiseaseId(donorId, "Some Disease");
        DonorDisease donorDisease = new DonorDisease();
        donorDisease.setDonorDiseaseId(donorDiseaseId);
        List<DonorDisease> donorDiseaseEntries = new ArrayList<>();
        donorDiseaseEntries.add(donorDisease);

        when(donorDiseaseService.getAllDonorDiseaseEntries()).thenReturn(donorDiseaseEntries);

        List<DonorDisease> result = donorDiseaseController.getAllDonorDiseaseEntries();

        assertEquals(donorDiseaseEntries.size(), result.size());
        verify(donorDiseaseService, times(1)).getAllDonorDiseaseEntries();
    }

    @Test
    public void testGetDonorDiseaseEntry() {
        Long donorId = 1L;
        DonorDiseaseId donorDiseaseId = new DonorDiseaseId(donorId, "Some Disease");
        DonorDisease donorDisease = new DonorDisease();
        donorDisease.setDonorDiseaseId(donorDiseaseId);

        when(donorDiseaseService.getDonorDiseaseEntry(donorId)).thenReturn(donorDisease);

        ResponseEntity<DonorDisease> response = donorDiseaseController.getDonorDiseaseEntry(donorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(donorDisease, response.getBody());
        verify(donorDiseaseService, times(1)).getDonorDiseaseEntry(donorId);
    }

    @Test
    public void testGetDonorDiseaseEntryNotFound() {
        Long donorId = 1L;

        when(donorDiseaseService.getDonorDiseaseEntry(donorId)).thenReturn(null);

        ResponseEntity<DonorDisease> response = donorDiseaseController.getDonorDiseaseEntry(donorId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(donorDiseaseService, times(1)).getDonorDiseaseEntry(donorId);
    }

    @Test
    public void testCreateDonorDiseaseEntry() {
        DonorDiseaseDTO donorDiseaseDTO = new DonorDiseaseDTO();
        donorDiseaseDTO.setDonorId(1L);
        donorDiseaseDTO.setDisease("Some Disease");
        DonorDisease donorDisease = new DonorDisease();
        DonorDiseaseId donorDiseaseId = new DonorDiseaseId(1L, "Some Disease");
        donorDisease.setDonorDiseaseId(donorDiseaseId);

        when(donorDiseaseService.createDonorDiseaseEntry(any(DonorDisease.class))).thenReturn(donorDisease);

        ResponseEntity<DonorDisease> response = donorDiseaseController.createDonorDiseaseEntry(donorDiseaseDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(donorDisease, response.getBody());
        verify(donorDiseaseService, times(1)).createDonorDiseaseEntry(any(DonorDisease.class));
    }

    @Test
    public void testDeleteDonorDiseaseEntry() {
        Long donorId = 1L;
        String disease = "Some Disease";

        ResponseEntity<Void> response = donorDiseaseController.deleteDonorDiseaseEntry(donorId, disease);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(donorDiseaseService, times(1)).deleteDonorDiseaseEntry(donorId, disease);
    }
}
