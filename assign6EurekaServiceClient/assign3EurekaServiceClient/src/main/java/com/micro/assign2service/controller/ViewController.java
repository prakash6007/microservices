package com.micro.assign2service.controller;

import com.micro.assign2service.model.Service;
import com.micro.assign2service.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {

    private static final Logger log = LoggerFactory.getLogger(ViewController.class);
    private final ServiceService serviceService;

    public ViewController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/")
    public String home() {
        log.info("Accessed Home Page");
        return "index";
    }

    @GetMapping("/services")
    public String services(Model model) {
        log.info("Viewing all services");
        model.addAttribute("services", serviceService.getAllServices());
        return "services";
    }

    @GetMapping("/services/add")
    public String addServiceForm(Model model) {
        log.info("Accessed Add Service form");
        model.addAttribute("service", new Service());
        return "add-service";
    }

    @PostMapping("/services/add")
    public String addServiceSubmit(@ModelAttribute Service service) {
        log.info("Adding new service: {}", service.getServiceName());
        serviceService.saveService(service);
        return "redirect:/services";
    }
}