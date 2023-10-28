package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.HospitalBloodContact;
import com.bloodbanksystem.springboot.entity.HospitalBloodContactId;
import com.bloodbanksystem.springboot.repository.HospitalBloodContactRepository;
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
public class HospitalBloodContactServiceTest {

    @InjectMocks
    private HospitalBloodContactService hospitalBloodContactService;

    @Mock
    private HospitalBloodContactRepository hospitalBloodContactRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllHospitalBloodContacts() {
        List<HospitalBloodContact> hospitalBloodContacts = new ArrayList<>();
        // Add some hospital blood contacts to the list

        when(hospitalBloodContactRepository.findAll()).thenReturn(hospitalBloodContacts);

        List<HospitalBloodContact> result = hospitalBloodContactService.getAllHospitalBloodContacts();

        assertEquals(hospitalBloodContacts.size(), result.size());
        verify(hospitalBloodContactRepository, times(1)).findAll();
    }

    @Test
    public void testCreateHospitalBloodContact() {
        HospitalBloodContact hospitalBloodContact = new HospitalBloodContact(new HospitalBloodContactId(1L, 2L));

        when(hospitalBloodContactRepository.save(hospitalBloodContact)).thenReturn(hospitalBloodContact);

        HospitalBloodContact result = hospitalBloodContactService.createHospitalBloodContact(hospitalBloodContact);

        assertEquals(hospitalBloodContact, result);
        verify(hospitalBloodContactRepository, times(1)).save(hospitalBloodContact);
    }

    @Test
    public void testGetHospitalBloodContact() {
        Long hospitalId = 1L;
        Long bloodBankId = 2L;
        HospitalBloodContactId id = new HospitalBloodContactId(hospitalId, bloodBankId);
        HospitalBloodContact hospitalBloodContact = new HospitalBloodContact(id);

        when(hospitalBloodContactRepository.findById(id)).thenReturn(Optional.of(hospitalBloodContact));

        HospitalBloodContact result = hospitalBloodContactService.getHospitalBloodContact(hospitalId, bloodBankId);

        assertEquals(hospitalBloodContact, result);
        verify(hospitalBloodContactRepository, times(1)).findById(id);
    }

    @Test
    public void testGetHospitalBloodContactNotFound() {
        Long hospitalId = 1L;
        Long bloodBankId = 2L;
        HospitalBloodContactId id = new HospitalBloodContactId(hospitalId, bloodBankId);

        when(hospitalBloodContactRepository.findById(id)).thenReturn(Optional.empty());

        HospitalBloodContact result = hospitalBloodContactService.getHospitalBloodContact(hospitalId, bloodBankId);

        assertEquals(null, result);
        verify(hospitalBloodContactRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteHospitalBloodContact() {
        Long hospitalId = 1L;
        Long bloodBankId = 2L;
        HospitalBloodContactId id = new HospitalBloodContactId(hospitalId, bloodBankId);

        hospitalBloodContactService.deleteHospitalBloodContact(hospitalId, bloodBankId);

        verify(hospitalBloodContactRepository, times(1)).deleteById(id);
    }
}
