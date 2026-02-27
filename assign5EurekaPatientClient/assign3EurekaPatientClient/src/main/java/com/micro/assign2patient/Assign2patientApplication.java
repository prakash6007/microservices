package com.micro.assign2patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Assign2patientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Assign2patientApplication.class, args);
    }

}