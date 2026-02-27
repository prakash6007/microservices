package com.micro.assign2service.controller;

import com.micro.assign2service.model.Service;
import com.micro.assign2service.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:8080") // Allow Patient-MS calls
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);
    private final ServiceService serviceService;

    // Toggle to simulate service failure
    private boolean simulateFailure = false;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Endpoint to toggle failure simulation
    @PostMapping("/simulateFailure/{status}")
    public String toggleFailure(@PathVariable boolean status) {
        simulateFailure = status;
        serviceService.setSimulateFailure(status);
        log.info("Service failure simulation set to {}", status);
        return "Failure simulation: " + status;
    }

    // Get all services
    @GetMapping
    public List<Service> getAll() {
        log.info("Fetching all services");
        return serviceService.getAllServices();
    }

    // Get service by ID
    @GetMapping("/{id}")
    public Service getById(@PathVariable Long id) {
        log.info("Fetching service with id {}", id);
        return serviceService.getServiceById(id);
    }

    // Create a new service
    @PostMapping
    public Service create(@RequestBody Service serviceObj) {
        log.info("Saving service {}", serviceObj.getServiceName());
        return serviceService.saveService(serviceObj);
    }

    // Count of services
    @GetMapping("/count")
    public long count() {
        return serviceService.getServiceCount();
    }
}