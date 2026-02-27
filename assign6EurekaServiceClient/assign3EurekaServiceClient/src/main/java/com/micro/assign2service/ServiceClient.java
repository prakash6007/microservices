package com.micro.assign2service;

import com.micro.assign2service.model.Service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SERVICE-MS")
public interface ServiceClient {

    @GetMapping("/api/services")
    Service[] getAllServices();
}