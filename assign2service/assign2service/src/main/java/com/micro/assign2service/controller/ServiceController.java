package com.micro.assign2service.controller;

import com.micro.assign2service.model.Service;
import com.micro.assign2service.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:8080") // allow Patient MS to call
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Service> getAll() {
        return service.getAllServices();
    }

    @GetMapping("/{id}")
    public Service getById(@PathVariable Long id) {
        return service.getServiceById(id);
    }

    @PostMapping
    public Service create(@RequestBody Service serviceObj) {
        return service.saveService(serviceObj);
    }

    @GetMapping("/count")
    public long count() {
        return service.getServiceCount();
    }
}