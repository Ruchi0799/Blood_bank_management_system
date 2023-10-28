package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Blood;
import com.bloodbanksystem.springboot.repository.BloodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BloodServiceTest {

    @InjectMocks
    private BloodService bloodService;

    @Mock
    private BloodRepository bloodRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetBloodById() {
        int id = 1;
        Blood blood = new Blood(id, "A+", "Available", "100 ml", new Date(), 1);

        when(bloodRepository.findById(id)).thenReturn(Optional.of(blood));

        Optional<Blood> result = bloodService.getBloodById(id);

        assertEquals(blood, result.orElse(null));
        verify(bloodRepository, times(1)).findById(id);
    }

    @Test
    public void testCreateBlood() {
        Blood blood = new Blood(1, "B+", "Available", "200 ml", new Date(), 2);

        when(bloodRepository.save(blood)).thenReturn(blood);

        Blood result = bloodService.createBlood(blood);

        assertEquals(blood, result);
        verify(bloodRepository, times(1)).save(blood);
    }

    @Test
    public void testUpdateBlood() {
        int id = 1;
        Blood existingBlood = new Blood(id, "O-", "Available", "150 ml", new Date(), 3);
        Blood updatedBlood = new Blood(id, "O+", "Available", "300 ml", new Date(), 3);

        when(bloodRepository.existsById(id)).thenReturn(true);
        when(bloodRepository.save(updatedBlood)).thenReturn(updatedBlood);

        Blood result = bloodService.updateBlood(id, updatedBlood);

        assertEquals(updatedBlood, result);
        verify(bloodRepository, times(1)).existsById(id);
        verify(bloodRepository, times(1)).save(updatedBlood);
    }

    @Test
    public void testUpdateBloodNotFound() {
        int id = 1;
        Blood updatedBlood = new Blood(id, "O+", "Available", "300 ml", new Date(), 3);

        when(bloodRepository.existsById(id)).thenReturn(false);

        Blood result = bloodService.updateBlood(id, updatedBlood);

        assertEquals(null, result);
        verify(bloodRepository, times(1)).existsById(id);
    }

    @Test
    public void testDeleteBlood() {
        int id = 1;

        bloodService.deleteBlood(id);

        verify(bloodRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetAllBlood() {
        List<Blood> bloodList = new ArrayList<>();
        // Add some blood entries to the list

        when(bloodRepository.findAll()).thenReturn(bloodList);

        List<Blood> result = bloodService.getAllBlood();

        assertEquals(bloodList.size(), result.size());
        verify(bloodRepository, times(1)).findAll();
    }

    @Test
    public void testGetBloodGroupCountByBloodBankId() {
        Long bloodBankId = 1L;
        List<Blood> bloodList = new ArrayList<>();
        // Add some blood entries to the list

        when(bloodRepository.getBloodGroupCountByBloodBankId(Math.toIntExact(bloodBankId))).thenReturn(bloodList);

        List<Blood> result = bloodService.getBloodGroupCountByBloodBankId(bloodBankId);

        assertEquals(bloodList.size(), result.size());
        verify(bloodRepository, times(1)).getBloodGroupCountByBloodBankId(Math.toIntExact(bloodBankId));
    }
}
