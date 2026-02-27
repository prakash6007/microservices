package com.micro.assign2patient.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.micro.assign2patient.model.Patient;
import com.micro.assign2patient.repo.PatientRepository;






@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> getAllPatients() { return repository.findAll(); }
    public Patient getPatientById(Long id) { return repository.findById(id).orElse(null); }
    public Patient savePatient(Patient patient) { return repository.save(patient); }
    public long getPatientCount() { return repository.count(); }
}
