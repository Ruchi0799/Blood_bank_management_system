package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.HospitalBloodContact;
import com.bloodbanksystem.springboot.entity.HospitalBloodContactDTO;
import com.bloodbanksystem.springboot.entity.HospitalBloodContactId;
import com.bloodbanksystem.springboot.service.HospitalBloodContactService;
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
public class HospitalBloodContactControllerTest {

    @InjectMocks
    private HospitalBloodContactController hospitalBloodContactController;

    @Mock
    private HospitalBloodContactService hospitalBloodContactService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllHospitalBloodContacts() {
        HospitalBloodContact hospitalBloodContact = new HospitalBloodContact();
        HospitalBloodContactId hospitalBloodContactId = new HospitalBloodContactId(1L, 2L);
        hospitalBloodContact.setId(hospitalBloodContactId);
        List<HospitalBloodContact> hospitalBloodContacts = new ArrayList<>();
        hospitalBloodContact.setId(hospitalBloodContactId);

        when(hospitalBloodContactService.getAllHospitalBloodContacts()).thenReturn(hospitalBloodContacts);

        List<HospitalBloodContact> result = hospitalBloodContactController.getAllHospitalBloodContacts();

        assertEquals(hospitalBloodContacts.size(), result.size());
        verify(hospitalBloodContactService, times(1)).getAllHospitalBloodContacts();
    }

    @Test
    public void testCreateHospitalBloodContact() {
        HospitalBloodContactDTO hospitalBloodContactDTO = new HospitalBloodContactDTO();
        hospitalBloodContactDTO.setHospitalId(1L);
        hospitalBloodContactDTO.setBloodBankId(2L);
        HospitalBloodContact hospitalBloodContact = new HospitalBloodContact();
        HospitalBloodContactId hospitalBloodContactId = new HospitalBloodContactId(1L, 2L);
        hospitalBloodContact.setId(hospitalBloodContactId);

        when(hospitalBloodContactService.createHospitalBloodContact(any(HospitalBloodContact.class))).thenReturn(hospitalBloodContact);

        HospitalBloodContact response = hospitalBloodContactController.createHospitalBloodContact(hospitalBloodContactDTO);

        assertEquals(hospitalBloodContact, response);
        verify(hospitalBloodContactService, times(1)).createHospitalBloodContact(any(HospitalBloodContact.class));
    }

    @Test
    public void testGetHospitalBloodContact() {
        int hospitalId = 1;
        int bloodBankId = 2;
        HospitalBloodContact hospitalBloodContact = new HospitalBloodContact();
        HospitalBloodContactId hospitalBloodContactId = new HospitalBloodContactId(1L, 2L);
        hospitalBloodContact.setId(hospitalBloodContactId);

        when(hospitalBloodContactService.getHospitalBloodContact(1L, 2L)).thenReturn(hospitalBloodContact);

        ResponseEntity<HospitalBloodContact> response = hospitalBloodContactController.getHospitalBloodContact(hospitalId, bloodBankId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospitalBloodContact, response.getBody());
        verify(hospitalBloodContactService, times(1)).getHospitalBloodContact(1L, 2L);
    }

    @Test
    public void testGetHospitalBloodContactNotFound() {
        int hospitalId = 1;
        int bloodBankId = 2;

        when(hospitalBloodContactService.getHospitalBloodContact(1L, 2L)).thenReturn(null);

        ResponseEntity<HospitalBloodContact> response = hospitalBloodContactController.getHospitalBloodContact(hospitalId, bloodBankId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(hospitalBloodContactService, times(1)).getHospitalBloodContact(1L, 2L);
    }

    @Test
    public void testDeleteHospitalBloodContact() {
        int hospitalId = 1;
        int bloodBankId = 2;

        ResponseEntity<Void> response = hospitalBloodContactController.deleteHospitalBloodContact(hospitalId, bloodBankId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(hospitalBloodContactService, times(1)).deleteHospitalBloodContact(1L, 2L);
    }
}
