package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.BloodBank;
import com.bloodbanksystem.springboot.repository.BloodBankRepository;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BloodBankServiceTest {

    @InjectMocks
    private BloodBankService bloodBankService;

    @Mock
    private BloodBankRepository bloodBankRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllBloodBanks() {
        int bloodBankId = 1;
        BloodBank bloodBank = new BloodBank();
        bloodBank.setBloodBankId(bloodBankId);
        List<BloodBank> bloodBanks = new ArrayList<>();
        bloodBanks.add(bloodBank);
        when(bloodBankRepository.findAll()).thenReturn(bloodBanks);

        List<BloodBank> result = bloodBankService.getAllBloodBanks();

        assertNotNull(result);
        assertEquals(bloodBanks.size(), result.size());
        assertEquals(bloodBanks, result);
    }

    @Test
    public void testGetBloodBankById() {
        int bloodBankId = 1;
        BloodBank bloodBank = new BloodBank();
        bloodBank.setBloodBankId(bloodBankId);

        when(bloodBankRepository.findById(bloodBankId)).thenReturn(Optional.of(bloodBank));

        BloodBank result = bloodBankService.getBloodBankById(bloodBankId);

        assertNotNull(result);
        assertEquals(bloodBankId, result.getBloodBankId());
    }

    @Test
    public void testCreateBloodBank() {
        BloodBank bloodBank = new BloodBank();

        when(bloodBankRepository.save(any(BloodBank.class))).thenReturn(bloodBank);

        BloodBank result = bloodBankService.createBloodBank(bloodBank);

        assertNotNull(result);
        // You can add more specific assertions based on your data and use case
    }

    @Test
    public void testUpdateBloodBank() {
        int bloodBankId = 1;
        BloodBank bloodBank = new BloodBank();
        bloodBank.setBloodBankId(bloodBankId);

        when(bloodBankRepository.existsById(bloodBankId)).thenReturn(true);
        when(bloodBankRepository.save(any(BloodBank.class))).thenReturn(bloodBank);

        BloodBank result = bloodBankService.updateBloodBank(bloodBankId, bloodBank);

        assertNotNull(result);
        assertEquals(bloodBankId, result.getBloodBankId());
    }

    @Test
    public void testUpdateBloodBankNotFound() {
        int bloodBankId = 1;
        BloodBank bloodBank = new BloodBank();

        when(bloodBankRepository.existsById(bloodBankId)).thenReturn(false);

        BloodBank result = bloodBankService.updateBloodBank(bloodBankId, bloodBank);

        assertNull(result);
    }

    @Test
    public void testDeleteBloodBank() {
        int bloodBankId = 1;

        bloodBankService.deleteBloodBank(bloodBankId);

        // Add assertions to verify that the delete operation was called
        verify(bloodBankRepository, times(1)).deleteById(bloodBankId);
    }
}
