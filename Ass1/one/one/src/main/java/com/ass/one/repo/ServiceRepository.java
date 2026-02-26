package com.ass.one.repo;




import org.springframework.data.jpa.repository.JpaRepository;

import com.ass.one.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
