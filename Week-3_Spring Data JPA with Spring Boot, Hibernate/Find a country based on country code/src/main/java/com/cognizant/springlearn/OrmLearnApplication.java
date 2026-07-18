package com.cognizant.springlearn;

import com.cognizant.springlearn.entity.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application for ORM Learning
 * 
 * This application demonstrates:
 * - Spring Data JPA usage
 * - @Transactional annotation importance
 * - Custom exception handling
 * - Country entity management
 */
@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    
    @Autowired
    private CountryService countryService;
    
    public static void main(String[] args) {
        LOGGER.info("Starting ORM Learn Application...");
        SpringApplication.run(OrmLearnApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Application started successfully!");
        LOGGER.info("H2 Console available at: http://localhost:8080/h2-console");
        LOGGER.info("JDBC URL: jdbc:h2:mem:testdb");
        LOGGER.info("Username: sa, Password: password");
        
        // Wait a moment for data initialization to complete
        Thread.sleep(2000);
        
        // Run the test methods
        getAllCountriesTest();
        findCountryByCodeTest();
        testCountryNotFound();
    }
    
    /**
     * Test method to find a country based on country code
     * This method demonstrates the usage of @Transactional annotation
     * and custom exception handling
     */
    private void getAllCountriesTest() {
        LOGGER.info("Start - getAllCountriesTest");
        try {
            // Find country by code "IN" (India)
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
            
            // Validate the country name
            if ("India".equals(country.getCountryName())) {
                LOGGER.info("✓ Country validation successful - Found India with code IN");
            } else {
                LOGGER.error("✗ Country validation failed - Expected: India, Found: {}", country.getCountryName());
            }
            
        } catch (CountryNotFoundException e) {
            LOGGER.error("✗ Country not found: {}", e.getMessage());
        }
        LOGGER.info("End - getAllCountriesTest");
    }
    
    /**
     * Additional test method to demonstrate finding different countries
     */
    private void findCountryByCodeTest() {
        LOGGER.info("Start - findCountryByCodeTest");
        
        String[] countryCodes = {"US", "GB", "CA", "AU", "DE"};
        String[] expectedNames = {"United States", "United Kingdom", "Canada", "Australia", "Germany"};
        
        for (int i = 0; i < countryCodes.length; i++) {
            try {
                Country country = countryService.findCountryByCode(countryCodes[i]);
                LOGGER.debug("Country:{}", country);
                
                if (expectedNames[i].equals(country.getCountryName())) {
                    LOGGER.info("✓ Country validation successful - Found {} with code {}", 
                              country.getCountryName(), countryCodes[i]);
                } else {
                    LOGGER.error("✗ Country validation failed - Expected: {}, Found: {}", 
                               expectedNames[i], country.getCountryName());
                }
                
            } catch (CountryNotFoundException e) {
                LOGGER.error("✗ Country not found: {}", e.getMessage());
            }
        }
        
        LOGGER.info("End - findCountryByCodeTest");
    }
    
    /**
     * Test method to demonstrate exception handling when country is not found
     */
    private void testCountryNotFound() {
        LOGGER.info("Start - testCountryNotFound");
        try {
            // Try to find a country that doesn't exist
            Country country = countryService.findCountryByCode("XX");
            LOGGER.error("✗ Expected CountryNotFoundException but found country: {}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.info("✓ CountryNotFoundException handled correctly: {}", e.getMessage());
        }
        LOGGER.info("End - testCountryNotFound");
    }
}
