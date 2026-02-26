package com.ass.one.controller;


import org.springframework.web.bind.annotation.*;

import com.ass.one.model.Patient;
import com.ass.one.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Patient> getAll() {
        return service.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return service.getPatientById(id);
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