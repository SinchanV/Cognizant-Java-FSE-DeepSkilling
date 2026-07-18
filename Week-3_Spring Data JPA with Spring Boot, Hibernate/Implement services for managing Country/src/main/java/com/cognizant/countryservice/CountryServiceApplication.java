package com.cognizant.countryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application for Country Management Service
 */
@SpringBootApplication
public class CountryServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CountryServiceApplication.class, args);
        System.out.println("Country Management Service is running on port 8080");
        System.out.println("H2 Console available at: http://localhost:8080/h2-console");
        System.out.println("API endpoints available at: http://localhost:8080/api/countries");
    }
}
