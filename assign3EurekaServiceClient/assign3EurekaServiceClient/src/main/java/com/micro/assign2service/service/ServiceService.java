package com.micro.assign2service.service;






import java.util.List;

import com.micro.assign2service.model.Service;
import com.micro.assign2service.repo.ServiceRepository;

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