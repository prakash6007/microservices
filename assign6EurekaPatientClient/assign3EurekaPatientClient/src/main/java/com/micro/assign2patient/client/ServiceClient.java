package com.micro.assign2patient.client;

import com.micro.assign2patient.model.Service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SERVICE-MS")  // must match Service-MS spring.application.name
public interface ServiceClient {

    @GetMapping("/api/services")
    Service[] getAllServices();
}