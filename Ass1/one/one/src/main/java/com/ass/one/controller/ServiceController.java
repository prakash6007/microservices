package com.ass.one.controller;




import org.springframework.web.bind.annotation.*;

import com.ass.one.model.Service;
import com.ass.one.service.ServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/services")
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
