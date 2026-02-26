package com.micro.assign2service.controller;

import com.micro.assign2service.model.Service;
import com.micro.assign2service.service.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {

    private final ServiceService serviceService;

    public ViewController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("services", serviceService.getAllServices());
        return "services";
    }

    @GetMapping("/services/add")
    public String addServiceForm(Model model) {
        model.addAttribute("service", new Service());
        return "add-service";
    }

    @PostMapping("/services/add")
    public String addServiceSubmit(@ModelAttribute Service service) {
        serviceService.saveService(service);
        return "redirect:/services";
    }
}