package com.micro.assign2patient.service;

import com.micro.assign2patient.client.ServiceClient;
import com.micro.assign2patient.model.Patient;
import com.micro.assign2patient.model.Service;
import com.micro.assign2patient.repo.PatientRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@org.springframework.stereotype.Service
public class PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository repository;
    private final ServiceClient serviceClient;
    private final CircuitBreakerRegistry registry;

    public PatientService(PatientRepository repository,
                          ServiceClient serviceClient,
                          CircuitBreakerRegistry registry) {
        this.repository = repository;
        this.serviceClient = serviceClient;
        this.registry = registry;
    }

    @PostConstruct
    public void initCircuitBreakerListener() {
        registry.circuitBreaker("serviceMSCircuitBreaker")
                .getEventPublisher()
                .onStateTransition(event -> log.info("CircuitBreaker state changed: {}", event.getStateTransition()));
    }

    @CircuitBreaker(name = "serviceMSCircuitBreaker", fallbackMethod = "getAllPatientsFallback")
    public List<Patient> getAllPatientsWithServices() {
        log.info("Fetching all patients with services (remote call)");
        List<Patient> patients = repository.findAll();

        Service[] services = serviceClient.getAllServices(); // Feign call to Service-MS

        if (services != null) {
            for (Patient p : patients) {
                p.setServices(List.of(services));
            }
        }

        return patients;
    }

    public List<Patient> getAllPatientsFallback(Throwable t) {
        log.warn("Circuit Breaker triggered (fallback): {}", t.getMessage());
        List<Patient> patients = repository.findAll();
        for (Patient p : patients) {
            p.setServices(List.of()); // empty list on fallback
        }
        return patients;
    }

    @CircuitBreaker(name = "serviceMSCircuitBreaker", fallbackMethod = "getByIdFallback")
    public Patient getPatientByIdWithServices(Long id) {
        log.info("Fetching patient by ID with services");
        Patient patient = repository.findById(id).orElse(null);

        if (patient != null) {
            Service[] services = serviceClient.getAllServices();
            if (services != null) {
                patient.setServices(List.of(services));
            }
        }

        return patient;
    }

    public Patient getByIdFallback(Long id, Throwable t) {
        log.warn("Circuit Breaker triggered for getById: {}", t.getMessage());
        Patient patient = repository.findById(id).orElse(null);
        if (patient != null) patient.setServices(List.of());
        return patient;
    }

    public Patient savePatient(Patient patient) {
        return repository.save(patient);
    }

    public long getPatientCount() {
        return repository.count();
    }
}