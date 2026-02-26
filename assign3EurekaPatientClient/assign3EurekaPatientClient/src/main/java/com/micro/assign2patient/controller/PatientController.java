package com.micro.assign2patient.controller;

import com.micro.assign2patient.model.Patient;
import com.micro.assign2patient.model.Service;
import com.micro.assign2patient.service.PatientService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;
    private final RestTemplate restTemplate;
    private final String SERVICE_MS_URL = "http://localhost:8081/api/services";

    public PatientController(PatientService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<Patient> getAll() {
        List<Patient> patients = service.getAllPatients();
        Service[] services = restTemplate.getForObject(SERVICE_MS_URL, Service[].class);
        for (Patient p : patients) {
            p.setServices(List.of(services));
        }
        return patients;
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        Patient patient = service.getPatientById(id);
        if (patient != null) {
            Service[] services = restTemplate.getForObject(SERVICE_MS_URL, Service[].class);
            patient.setServices(List.of(services));
        }
        return patient;
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return service.savePatient(patient);
    }

    @GetMapping("/count")
    public long count() {
        return service.getPatientCount();
    }
}