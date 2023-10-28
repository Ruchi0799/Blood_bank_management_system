package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Patient;
import com.bloodbanksystem.springboot.repository.PatientRepository;
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
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        // Add some patients to the list

        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getAllPatients();

        assertEquals(patients.size(), result.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testGetPatientById() {
        int id = 1;
        Patient patient = new Patient(id, "Patient Name", "Male", "1234567890", "A+", "Patient Address", new Date(), 1, 1);

        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        Patient result = patientService.getPatientById(id);

        assertEquals(patient, result);
        verify(patientRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPatientByIdNotFound() {
        int id = 1;

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        Patient result = patientService.getPatientById(id);

        assertEquals(null, result);
        verify(patientRepository, times(1)).findById(id);
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient(1, "Patient Name", "Male", "1234567890", "A+", "Patient Address", new Date(), 1, 1);

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient result = patientService.createPatient(patient);

        assertEquals(patient, result);
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testUpdatePatient() {
        int id = 1;
        Patient existingPatient = new Patient(id, "Patient Name", "Male", "1234567890", "A+", "Patient Address", new Date(), 1, 1);
        Patient updatedPatient = new Patient(id, "Updated Patient Name", "Female", "9876543210", "B+", "Updated Address", new Date(), 2, 2);

        when(patientRepository.existsById(id)).thenReturn(true);
        when(patientRepository.save(updatedPatient)).thenReturn(updatedPatient);

        Patient result = patientService.updatePatient(id, updatedPatient);

        assertEquals(updatedPatient, result);
        verify(patientRepository, times(1)).existsById(id);
        verify(patientRepository, times(1)).save(updatedPatient);
    }

    @Test
    public void testUpdatePatientNotFound() {
        int id = 1;
        Patient updatedPatient = new Patient(id, "Updated Patient Name", "Female", "9876543210", "B+", "Updated Address", new Date(), 2, 2);

        when(patientRepository.existsById(id)).thenReturn(false);

        Patient result = patientService.updatePatient(id, updatedPatient);

        assertEquals(null, result);
        verify(patientRepository, times(1)).existsById(id);
    }

    @Test
    public void testDeletePatient() {
        int id = 1;

        patientService.deletePatient(id);

        verify(patientRepository, times(1)).deleteById(id);
    }
}
