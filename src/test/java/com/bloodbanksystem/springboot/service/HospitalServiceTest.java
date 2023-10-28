package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Hospital;
import com.bloodbanksystem.springboot.repository.HospitalRepository;
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
public class HospitalServiceTest {

    @InjectMocks
    private HospitalService hospitalService;

    @Mock
    private HospitalRepository hospitalRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllHospitals() {
        List<Hospital> hospitals = new ArrayList<>();
        // Add some hospitals to the list

        when(hospitalRepository.findAll()).thenReturn(hospitals);

        List<Hospital> result = hospitalService.getAllHospitals();

        assertEquals(hospitals.size(), result.size());
        verify(hospitalRepository, times(1)).findAll();
    }

    @Test
    public void testGetHospitalById() {
        int id = 1;
        Hospital hospital = new Hospital(id, "Hospital Name", "Hospital Location");

        when(hospitalRepository.findById(id)).thenReturn(Optional.of(hospital));

        Hospital result = hospitalService.getHospitalById(id);

        assertEquals(hospital, result);
        verify(hospitalRepository, times(1)).findById(id);
    }

    @Test
    public void testGetHospitalByIdNotFound() {
        int id = 1;

        when(hospitalRepository.findById(id)).thenReturn(Optional.empty());

        Hospital result = hospitalService.getHospitalById(id);

        assertEquals(null, result);
        verify(hospitalRepository, times(1)).findById(id);
    }

    @Test
    public void testCreateHospital() {
        Hospital hospital = new Hospital(1, "Hospital Name", "Hospital Location");

        when(hospitalRepository.save(hospital)).thenReturn(hospital);

        Hospital result = hospitalService.createHospital(hospital);

        assertEquals(hospital, result);
        verify(hospitalRepository, times(1)).save(hospital);
    }

    @Test
    public void testUpdateHospital() {
        int id = 1;
        Hospital existingHospital = new Hospital(id, "Hospital Name", "Hospital Location");
        Hospital updatedHospital = new Hospital(id, "Updated Hospital Name", "Updated Hospital Location");

        when(hospitalRepository.existsById(id)).thenReturn(true);
        when(hospitalRepository.save(updatedHospital)).thenReturn(updatedHospital);

        Hospital result = hospitalService.updateHospital(id, updatedHospital);

        assertEquals(updatedHospital, result);
        verify(hospitalRepository, times(1)).existsById(id);
        verify(hospitalRepository, times(1)).save(updatedHospital);
    }

    @Test
    public void testUpdateHospitalNotFound() {
        int id = 1;
        Hospital updatedHospital = new Hospital(id, "Updated Hospital Name", "Updated Hospital Location");

        when(hospitalRepository.existsById(id)).thenReturn(false);

        Hospital result = hospitalService.updateHospital(id, updatedHospital);

        assertEquals(null, result);
        verify(hospitalRepository, times(1)).existsById(id);
    }

    @Test
    public void testDeleteHospital() {
        int id = 1;

        hospitalService.deleteHospital(id);

        verify(hospitalRepository, times(1)).deleteById(id);
    }
}
