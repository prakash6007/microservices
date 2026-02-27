package com.micro.assign2patient.controller;

import com.micro.assign2patient.model.Patient;
import com.micro.assign2patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAll() {
        log.info("Controller: Fetching all patients with services");
        List<Patient> patients = patientService.getAllPatientsWithServices();
        log.info("Controller: Returning {} patients", patients.size());
        return patients;
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        log.info("Controller: Fetching patient with id {}", id);
        Patient patient = patientService.getPatientByIdWithServices(id);
        if (patient != null) {
            log.info("Controller: Patient {} returned with {} services",
                    patient.getId(), patient.getServices().size());
        }
        return patient;
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        log.info("Controller: Saving patient {} {}", patient.getFirstname(), patient.getLastname());
        return patientService.savePatient(patient);
    }

    @GetMapping("/count")
    public long count() {
        long count = patientService.getPatientCount();
        log.info("Controller: Total patient count = {}", count);
        return count;
    }
}