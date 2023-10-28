package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.BloodBank;
import com.bloodbanksystem.springboot.service.BloodBankService;
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
public class BloodBankControllerTest {

    @InjectMocks
    private BloodBankController bloodBankController;

    @Mock
    private BloodBankService bloodBankService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllBloodBanks() {
        List<BloodBank> bloodBanks = new ArrayList<>();
        bloodBanks.add(new BloodBank(1, "Blood Bank A", "Address A"));
        bloodBanks.add(new BloodBank(2, "Blood Bank B", "Address B"));

        when(bloodBankService.getAllBloodBanks()).thenReturn(bloodBanks);

        List<BloodBank> result = bloodBankController.getAllBloodBanks();

        assertEquals(2, result.size());
        verify(bloodBankService, times(1)).getAllBloodBanks();
    }

    @Test
    public void testGetBloodBankById() {
        int bloodBankId = 1;
        BloodBank bloodBank = new BloodBank(bloodBankId, "Blood Bank A", "Address A");

        when(bloodBankService.getBloodBankById(bloodBankId)).thenReturn(bloodBank);

        ResponseEntity<BloodBank> response = bloodBankController.getBloodBankById(bloodBankId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bloodBank, response.getBody());
        verify(bloodBankService, times(1)).getBloodBankById(bloodBankId);
    }

    @Test
    public void testGetBloodBankByIdNotFound() {
        int bloodBankId = 1;

        when(bloodBankService.getBloodBankById(bloodBankId)).thenReturn(null);

        ResponseEntity<BloodBank> response = bloodBankController.getBloodBankById(bloodBankId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bloodBankService, times(1)).getBloodBankById(bloodBankId);
    }

    @Test
    public void testCreateBloodBank() {
        BloodBank bloodBank = new BloodBank(1, "Blood Bank A", "Address A");

        when(bloodBankService.createBloodBank(bloodBank)).thenReturn(bloodBank);

        ResponseEntity<BloodBank> response = bloodBankController.createBloodBank(bloodBank);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bloodBank, response.getBody());
        verify(bloodBankService, times(1)).createBloodBank(bloodBank);
    }

    @Test
    public void testUpdateBloodBank() {
        int bloodBankId = 1;
        BloodBank bloodBank = new BloodBank(bloodBankId, "Blood Bank A", "Address A");

        when(bloodBankService.updateBloodBank(eq(bloodBankId), any(BloodBank.class))).thenReturn(bloodBank);

        ResponseEntity<BloodBank> response = bloodBankController.updateBloodBank(bloodBankId, new BloodBank(1, "New Blood Bank A", "New Address A"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bloodBank, response.getBody());
        verify(bloodBankService, times(1)).updateBloodBank(eq(bloodBankId), any(BloodBank.class));
    }

    @Test
    public void testUpdateBloodBankNotFound() {
        int bloodBankId = 1;

        when(bloodBankService.updateBloodBank(eq(bloodBankId), any(BloodBank.class))).thenReturn(null);

        ResponseEntity<BloodBank> response = bloodBankController.updateBloodBank(bloodBankId, new BloodBank(1, "New Blood Bank A", "New Address A"));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bloodBankService, times(1)).updateBloodBank(eq(bloodBankId), any(BloodBank.class));
    }

    @Test
    public void testDeleteBloodBank() {
        int bloodBankId = 1;

        ResponseEntity<Void> response = bloodBankController.deleteBloodBank(bloodBankId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bloodBankService, times(1)).deleteBloodBank(bloodBankId);
    }
}
