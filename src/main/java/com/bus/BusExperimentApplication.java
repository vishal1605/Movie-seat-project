package com.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BusExperimentApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusExperimentApplication.class, args);
	}

}
