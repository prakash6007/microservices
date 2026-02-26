package com.ass.one.service;

import com.ass.one.model.Service;        // import your model class
import com.ass.one.repo.ServiceRepository;
import java.util.List;

// Fully qualify the annotation to avoid conflict
@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    // Correct method return type using model.Service
    public List<Service> getAllServices() {
        return repository.findAll();  // List of your model.Service
    }

    public Service getServiceById(Long id) {
        return repository.findById(id).orElse(null);  // return your model.Service
    }

    public Service saveService(Service service) {
        return repository.save(service);  // save your model.Service
    }

    public long getServiceCount() {
        return repository.count();
    }
}