package com.cognizant.ormapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.cognizant.ormapping.service.DemoService;

/**
 * Main Spring Boot Application demonstrating comprehensive O/R Mapping with JPA
 * 
 * This application demonstrates:
 * - @ManyToOne relationships
 * - @OneToMany relationships
 * - @ManyToMany relationships
 * - @JoinColumn annotations
 * - @JoinTable annotations
 * - FetchType.EAGER and FetchType.LAZY
 * - mappedBy attribute
 * - Bidirectional relationships
 * - Cascade operations
 * - Orphan removal
 */
@SpringBootApplication
public class ORMappingDemoApplication implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ORMappingDemoApplication.class);
    
    @Autowired
    private DemoService demoService;
    
    public static void main(String[] args) {
        LOGGER.info("Starting Spring Data JPA O/R Mapping Demonstration Application...");
        SpringApplication.run(ORMappingDemoApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Application started successfully!");
        LOGGER.info("H2 Console available at: http://localhost:8081/h2-console");
        LOGGER.info("JDBC URL: jdbc:h2:mem:ormappingdb");
        LOGGER.info("Username: sa, Password: password");
        LOGGER.info("REST API available at: http://localhost:8081/api");
        
        // Wait for data initialization
        Thread.sleep(2000);
        
        LOGGER.info("=".repeat(80));
        LOGGER.info("SPRING DATA JPA O/R MAPPING DEMONSTRATION");
        LOGGER.info("=".repeat(80));
        
        // Demonstrate all O/R mapping features
        demoService.demonstrateAllRelationships();
        
        LOGGER.info("=".repeat(80));
        LOGGER.info("O/R MAPPING DEMONSTRATION COMPLETED");
        LOGGER.info("=".repeat(80));
        LOGGER.info("You can now explore the H2 Console and REST API endpoints!");
    }
}
