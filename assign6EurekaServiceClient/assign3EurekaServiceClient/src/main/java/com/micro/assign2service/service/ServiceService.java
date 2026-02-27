package com.micro.assign2service.service;

import com.micro.assign2service.model.Service;
import com.micro.assign2service.repo.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private static final Logger log = LoggerFactory.getLogger(ServiceService.class);

    private final ServiceRepository repository;

    // Flag to simulate failure
    private boolean simulateFailure = false;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public void setSimulateFailure(boolean simulateFailure) {
        this.simulateFailure = simulateFailure;
    }

    public List<Service> getAllServices() {
        if (simulateFailure) {
            log.error("Simulated failure triggered!");
            throw new RuntimeException("Simulated Service-MS failure");
        }
        log.info("Fetching all services");
        return repository.findAll();
    }

    public Service getServiceById(Long id) {
        if (simulateFailure) {
            throw new RuntimeException("Simulated Service-MS failure");
        }
        log.info("Fetching service with id: {}", id);
        return repository.findById(id).orElse(null);
    }

    public Service saveService(Service service) {
        log.info("Saving service: {}", service.getServiceName());
        return repository.save(service);
    }

    public long getServiceCount() {
        return repository.count();
    }
}