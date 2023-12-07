package com.lima.eventsmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventsmicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsmicroservicesApplication.class, args);
	}

}
