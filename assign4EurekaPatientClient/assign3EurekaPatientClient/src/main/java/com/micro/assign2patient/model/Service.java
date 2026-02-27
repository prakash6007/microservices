package com.micro.assign2patient.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private float fees;

    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private List<Patient> patients = new ArrayList<>();

    public Service() {}

    public Service(String serviceName, float fees) {
        this.serviceName = serviceName;
        this.fees = fees;
    }

    public Long getId() { return id; }
    public String getServiceName() { return serviceName; }
    public float getFees() { return fees; }

    public void setId(Long id) { this.id = id; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public void setFees(float fees) { this.fees = fees; }
}