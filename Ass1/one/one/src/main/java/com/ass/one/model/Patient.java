package com.ass.one.model;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    @ManyToMany
    @JoinTable(
            name = "patient_services",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @JsonIgnoreProperties("patients")
    private List<Service> services = new ArrayList<>();

    public Patient() {}

    public Patient(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public List<Service> getServices() { return services; }

    public void setId(Long id) { this.id = id; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setServices(List<Service> services) { this.services = services; }
}