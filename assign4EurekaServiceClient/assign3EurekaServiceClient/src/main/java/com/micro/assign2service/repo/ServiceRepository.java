package com.micro.assign2service.repo;





import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.assign2service.model.Service;



public interface ServiceRepository extends JpaRepository<Service, Long> {
}