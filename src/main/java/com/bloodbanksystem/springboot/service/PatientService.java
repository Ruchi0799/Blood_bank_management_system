package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Patient;
import com.bloodbanksystem.springboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.orElse(null);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(int id, Patient patient) {
        if (patientRepository.existsById(id)) {
            patient.setPatientId(id);
            return patientRepository.save(patient);
        }
        return null;
    }

    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
}
