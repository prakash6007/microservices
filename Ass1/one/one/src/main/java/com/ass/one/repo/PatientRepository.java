package com.ass.one.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ass.one.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}