package com.micro.assign2service.controller;

import com.micro.assign2service.model.Service;
import com.micro.assign2service.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")

@CrossOrigin(origins = "http://localhost:8080")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Get all services
    @GetMapping
    public List<Service> getAll() {
        return serviceService.getAllServices();
    }

    // Get a single service by ID
    @GetMapping("/{id}")
    public Service getById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    // Create a new service
    @PostMapping
    public Service create(@RequestBody Service serviceObj) {
        return serviceService.saveService(serviceObj);
    }

    // Count of services
    @GetMapping("/count")
    public long count() {
        return serviceService.getServiceCount();
    }
}