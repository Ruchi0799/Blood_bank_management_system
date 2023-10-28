package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Patient;
import com.bloodbanksystem.springboot.service.PatientService;
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
public class PatientTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllPatients() {
        int patientId = 1;
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        List<Patient> patients = new ArrayList<>();

        when(patientService.getAllPatients()).thenReturn(patients);

        List<Patient> result = patientController.getAllPatients();

        assertEquals(patients.size(), result.size());
        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    public void testGetPatientById() {
        int patientId = 1;
        Patient patient = new Patient();
        patient.setPatientId(patientId);

        when(patientService.getPatientById(patientId)).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.getPatientById(patientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).getPatientById(patientId);
    }

    @Test
    public void testGetPatientByIdNotFound() {
        int patientId = 1;

        when(patientService.getPatientById(patientId)).thenReturn(null);

        ResponseEntity<Patient> response = patientController.getPatientById(patientId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(patientService, times(1)).getPatientById(patientId);
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();
        patient.setPatientId(1);

        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.createPatient(patient);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).createPatient(any(Patient.class));
    }

    @Test
    public void testUpdatePatient() {
        int patientId = 1;
        Patient patient = new Patient();
        patient.setPatientId(patientId);

        when(patientService.updatePatient(eq(patientId), any(Patient.class))).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.updatePatient(patientId, patient);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).updatePatient(eq(patientId), any(Patient.class));
    }

    @Test
    public void testUpdatePatientNotFound() {
        int patientId = 1;

        when(patientService.updatePatient(eq(patientId), any(Patient.class))).thenReturn(null);

        ResponseEntity<Patient> response = patientController.updatePatient(patientId, new Patient());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(patientService, times(1)).updatePatient(eq(patientId), any(Patient.class));
    }

    @Test
    public void testDeletePatient() {
        int patientId = 1;

        ResponseEntity<Void> response = patientController.deletePatient(patientId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(patientService, times(1)).deletePatient(patientId);
    }
}
