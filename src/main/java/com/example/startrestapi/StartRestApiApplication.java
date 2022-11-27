package com.example.startrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StartRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartRestApiApplication.class, args);
	}

}
