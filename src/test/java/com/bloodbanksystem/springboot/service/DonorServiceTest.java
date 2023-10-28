package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Donor;
import com.bloodbanksystem.springboot.repository.DonorRepository;
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
public class DonorServiceTest {

    @InjectMocks
    private DonorService donorService;

    @Mock
    private DonorRepository donorRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllDonors() {
        List<Donor> donors = new ArrayList<>();
        // Add some donors to the list

        when(donorRepository.findAll()).thenReturn(donors);

        List<Donor> result = donorService.getAllDonors();

        assertEquals(donors.size(), result.size());
        verify(donorRepository, times(1)).findAll();
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

        when(donorRepository.findById(donorId)).thenReturn(Optional.of(donor));

        Donor result = donorService.getDonorById(donorId);

        assertEquals(donor, result);
        verify(donorRepository, times(1)).findById(donorId);
    }

    @Test
    public void testGetDonorByIdNotFound() {
        int id = 1;

        when(donorRepository.findById(id)).thenReturn(Optional.empty());

        Donor result = donorService.getDonorById(id);

        assertEquals(null, result);
        verify(donorRepository, times(1)).findById(id);
    }

    @Test
    public void testCreateDonor() {
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

        when(donorRepository.save(donor)).thenReturn(donor);

        Donor result = donorService.createDonor(donor);

        assertEquals(donor, result);
        verify(donorRepository, times(1)).save(donor);
    }

    @Test
    public void testUpdateDonor() {
        int id = 1;
        Donor existingDonor = new Donor();
        existingDonor.setDonorId(id);
        existingDonor.setName("John Doe");
        existingDonor.setContact("12345");
        existingDonor.setGender("Male");
        existingDonor.setAddress("123 Main St");
        existingDonor.setDob(new Date());
        existingDonor.setBloodGroup("A+");
        existingDonor.setRegTeamId(1);

        int donorId = 1;
        Donor updatedDonor = new Donor();
        updatedDonor.setDonorId(donorId);
        updatedDonor.setName("John Doe");
        updatedDonor.setContact("12345");
        updatedDonor.setGender("Male");
        updatedDonor.setAddress("123 Main St");
        updatedDonor.setDob(new Date());
        updatedDonor.setBloodGroup("A+");
        updatedDonor.setRegTeamId(1);

        when(donorRepository.existsById(id)).thenReturn(true);
        when(donorRepository.save(updatedDonor)).thenReturn(updatedDonor);

        Donor result = donorService.updateDonor(id, updatedDonor);

        assertEquals(updatedDonor, result);
        verify(donorRepository, times(1)).existsById(id);
        verify(donorRepository, times(1)).save(updatedDonor);
    }

    @Test
    public void testUpdateDonorNotFound() {
        int id = 1;
        int donorId = 1;
        Donor updatedDonor = new Donor();
        updatedDonor.setDonorId(donorId);
        updatedDonor.setName("John Doe");
        updatedDonor.setContact("12345");
        updatedDonor.setGender("Male");
        updatedDonor.setAddress("123 Main St");
        updatedDonor.setDob(new Date());
        updatedDonor.setBloodGroup("A+");
        updatedDonor.setRegTeamId(1);

        when(donorRepository.existsById(id)).thenReturn(false);

        Donor result = donorService.updateDonor(id, updatedDonor);

        assertEquals(null, result);
        verify(donorRepository, times(1)).existsById(id);
    }

    @Test
    public void testDeleteDonor() {
        int id = 1;

        donorService.deleteDonor(id);

        verify(donorRepository, times(1)).deleteById(id);
    }
}
