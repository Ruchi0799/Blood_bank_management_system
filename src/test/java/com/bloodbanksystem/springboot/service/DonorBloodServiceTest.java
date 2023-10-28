package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.DonorBlood;
import com.bloodbanksystem.springboot.entity.DonorBloodId;
import com.bloodbanksystem.springboot.repository.DonorBloodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DonorBloodServiceTest {

    @InjectMocks
    private DonorBloodService donorBloodService;

    @Mock
    private DonorBloodRepository donorBloodRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllDonorBloodEntries() {
        List<DonorBlood> donorBloodEntries = new ArrayList<>();
        // Add some donor blood entries to the list

        when(donorBloodRepository.findAll()).thenReturn(donorBloodEntries);

        List<DonorBlood> result = donorBloodService.getAllDonorBloodEntries();

        assertEquals(donorBloodEntries.size(), result.size());
        verify(donorBloodRepository, times(1)).findAll();
    }

    @Test
    public void testGetDonorBloodEntry() {
        Long donorId = 1L;
        Long bloodId = 2L;
        DonorBloodId id = new DonorBloodId(donorId, bloodId);
        DonorBlood donorBlood = new DonorBlood(id);

        when(donorBloodRepository.findById(id)).thenReturn(Optional.of(donorBlood));

        DonorBlood result = donorBloodService.getDonorBloodEntry(donorId, bloodId);

        assertEquals(donorBlood, result);
        verify(donorBloodRepository, times(1)).findById(id);
    }

    @Test
    public void testGetDonorBloodEntryNotFound() {
        Long donorId = 1L;
        Long bloodId = 2L;
        DonorBloodId id = new DonorBloodId(donorId, bloodId);

        when(donorBloodRepository.findById(id)).thenReturn(Optional.empty());

        DonorBlood result = donorBloodService.getDonorBloodEntry(donorId, bloodId);

        assertEquals(null, result);
        verify(donorBloodRepository, times(1)).findById(id);
    }

    @Test
    public void testCreateDonorBloodEntry() {
        DonorBlood donorBlood = new DonorBlood(new DonorBloodId(1L, 2L));

        when(donorBloodRepository.save(donorBlood)).thenReturn(donorBlood);

        DonorBlood result = donorBloodService.createDonorBloodEntry(donorBlood);

        assertEquals(donorBlood, result);
        verify(donorBloodRepository, times(1)).save(donorBlood);
    }

    @Test
    public void testDeleteDonorBloodEntry() {
        Long donorId = 1L;
        Long bloodId = 2L;
        DonorBloodId id = new DonorBloodId(donorId, bloodId);

        donorBloodService.deleteDonorBloodEntry(donorId, bloodId);

        verify(donorBloodRepository, times(1)).deleteById(id);
    }
}
