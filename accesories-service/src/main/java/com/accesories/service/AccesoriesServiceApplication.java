package com.accesories.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccesoriesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccesoriesServiceApplication.class, args);
	}

}
