package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.DonorDisease;
import com.bloodbanksystem.springboot.entity.DonorDiseaseId;
import com.bloodbanksystem.springboot.repository.DonorDiseaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DonorDiseaseServiceTest {

    @InjectMocks
    private DonorDiseaseService donorDiseaseService;

    @Mock
    private DonorDiseaseRepository donorDiseaseRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllDonorDiseaseEntries() {
        List<DonorDisease> donorDiseaseEntries = new ArrayList<>();
        // Add some donor disease entries to the list

        when(donorDiseaseRepository.findAll()).thenReturn(donorDiseaseEntries);

        List<DonorDisease> result = donorDiseaseService.getAllDonorDiseaseEntries();

        assertEquals(donorDiseaseEntries.size(), result.size());
        verify(donorDiseaseRepository, times(1)).findAll();
    }

    @Test
    public void testGetDonorDiseaseEntry() {
        Long donorId = 1L;
        String disease = "Malaria";
        DonorDiseaseId id = new DonorDiseaseId(donorId, disease);
        DonorDisease donorDisease = new DonorDisease(id);

        when(donorDiseaseRepository.findByDonorId(donorId)).thenReturn(donorDisease);

        DonorDisease result = donorDiseaseService.getDonorDiseaseEntry(donorId);

        assertEquals(donorDisease, result);
        verify(donorDiseaseRepository, times(1)).findByDonorId(donorId);
    }

    @Test
    public void testGetDonorDiseaseEntryNotFound() {
        Long donorId = 1L;

        when(donorDiseaseRepository.findByDonorId(donorId)).thenReturn(null);

        DonorDisease result = donorDiseaseService.getDonorDiseaseEntry(donorId);

        assertEquals(null, result);
        verify(donorDiseaseRepository, times(1)).findByDonorId(donorId);
    }

    @Test
    public void testCreateDonorDiseaseEntry() {
        DonorDisease donorDisease = new DonorDisease(new DonorDiseaseId(1L, "Malaria"));

        when(donorDiseaseRepository.save(donorDisease)).thenReturn(donorDisease);

        DonorDisease result = donorDiseaseService.createDonorDiseaseEntry(donorDisease);

        assertEquals(donorDisease, result);
        verify(donorDiseaseRepository, times(1)).save(donorDisease);
    }

    @Test
    public void testDeleteDonorDiseaseEntry() {
        Long donorId = 1L;
        String disease = "Malaria";
        DonorDiseaseId id = new DonorDiseaseId(donorId, disease);

        donorDiseaseService.deleteDonorDiseaseEntry(donorId, disease);

        verify(donorDiseaseRepository, times(1)).deleteById(id);
    }
}
