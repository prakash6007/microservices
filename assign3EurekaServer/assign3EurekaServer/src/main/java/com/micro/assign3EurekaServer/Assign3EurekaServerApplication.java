package com.micro.assign3EurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Assign3EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assign3EurekaServerApplication.class, args);
	}

}
