package com.micro.assign2patient.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.assign2patient.model.Patient;



public interface PatientRepository extends JpaRepository<Patient, Long> {
}