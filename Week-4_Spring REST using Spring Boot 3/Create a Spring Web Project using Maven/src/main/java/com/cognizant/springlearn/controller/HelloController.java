package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample REST Controller to demonstrate Spring Web functionality
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * Simple endpoint to verify the application is running
     * 
     * @return Welcome message
     */
    @GetMapping("/")
    public String home() {
        logger.info("Home endpoint accessed");
        return "Welcome to Spring Learn Application! The application is running successfully.";
    }

    /**
     * Health check endpoint
     * 
     * @return Health status
     */
    @GetMapping("/health")
    public String health() {
        logger.info("Health check endpoint accessed");
        return "Application is UP and running!";
    }

    /**
     * Sample API endpoint
     * 
     * @return JSON response
     */
    @GetMapping("/api/info")
    public String info() {
        logger.info("Info endpoint accessed");
        return "{\n" +
                "  \"application\": \"Spring Learn\",\n" +
                "  \"version\": \"1.0.0\",\n" +
                "  \"framework\": \"Spring Boot 3.2.0\",\n" +
                "  \"java\": \"17\",\n" +
                "  \"status\": \"Active\"\n" +
                "}";
    }

    /**
     * Hello World RESTful Web Service
     * 
     * Method: GET
     * URL: /hello
     * @return Hello World message
     */
    @GetMapping("/hello")
    public String sayHello() {
        // Start log
        logger.info("START - sayHello() method called - Hello World RESTful Web Service");
        
        // Method Implementation: return hard coded string "Hello World!!"
        String response = "Hello World!!";
        
        // End log
        logger.info("END - sayHello() method completed - Returning: " + response);
        
        return response;
    }
}
