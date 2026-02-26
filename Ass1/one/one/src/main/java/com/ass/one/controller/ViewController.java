package com.ass.one.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import com.ass.one.model.Patient;
import com.ass.one.model.Service;
import com.ass.one.service.PatientService;
import com.ass.one.service.ServiceService;

import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {

    private final PatientService patientService;
    private final ServiceService serviceService;

    public ViewController(PatientService patientService, ServiceService serviceService) {
        this.patientService = patientService;
        this.serviceService = serviceService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // --- Existing list pages ---
    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("services", serviceService.getAllServices());
        return "services";
    }

    // --- New Add Patient Page ---
    @GetMapping("/patients/add")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    @PostMapping("/patients/add")
    public String addPatientSubmit(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    // --- New Add Service Page ---
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