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
import org.springframework.core.annotation.Order;

/**
 * Main Spring Boot Application for ORM Learning - Add Country Functionality
 * 
 * This application demonstrates:
 * - Adding new countries using @Transactional annotation
 * - Spring Data JPA save operations
 * - Validating country addition by searching
 * - Database verification through H2 console
 */
@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    
    @Autowired
    private CountryService countryService;
    
    public static void main(String[] args) {
        LOGGER.info("Starting ORM Learn Application - Add Country Functionality...");
        SpringApplication.run(OrmLearnApplication.class, args);
    }
    
    @Override
    @Order(2) // Run after DataInitializer
    public void run(String... args) throws Exception {
        LOGGER.info("Application started successfully!");
        LOGGER.info("H2 Console available at: http://localhost:8080/h2-console");
        LOGGER.info("JDBC URL: jdbc:h2:mem:testdb");
        LOGGER.info("Username: sa, Password: password");
        
        // Wait a moment for data initialization to complete
        Thread.sleep(2000);
        
        // Show initial countries count
        LOGGER.info("Initial countries count: {}", countryService.getCountryCount());
        
        // Run the test method to add a new country
        testAddCountry();
        
        // Show final countries count
        LOGGER.info("Final countries count: {}", countryService.getCountryCount());
        
        // Show all countries for verification
        showAllCountries();
    }
    
    /**
     * Test method to add a new country and verify its addition
     * This method demonstrates the complete flow of adding a country:
     * 1. Create new instance of country with a new code and name
     * 2. Call countryService.addCountry() passing the country created
     * 3. Invoke countryService.findCountryByCode() to verify addition
     * 4. Database verification can be done through H2 console
     */
    private void testAddCountry() {
        LOGGER.info("========== START: testAddCountry() ==========");
        
        try {
            // Step 1: Create new instance of country with a new code and name
            Country newCountry = new Country("DE", "Germany");
            LOGGER.info("Created new country: {}", newCountry);
            
            // Step 2: Call countryService.addCountry() passing the country created in the previous step
            LOGGER.info("Adding country to database...");
            countryService.addCountry(newCountry);
            LOGGER.info("✓ Country added successfully to database");
            
            // Step 3: Invoke countryService.findCountryByCode() passing the same code used when adding a new country
            LOGGER.info("Verifying country addition by searching for code: DE");
            Country retrievedCountry = countryService.findCountryByCode("DE");
            LOGGER.info("✓ Country retrieved successfully: {}", retrievedCountry);
            
            // Validate that the retrieved country matches what we added
            if ("DE".equals(retrievedCountry.getCountryCode()) && "Germany".equals(retrievedCountry.getCountryName())) {
                LOGGER.info("✓ SUCCESS: Country validation passed - Retrieved country matches added country");
            } else {
                LOGGER.error("✗ FAILURE: Country validation failed - Retrieved country does not match");
                LOGGER.error("Expected: Country{countryCode='DE', countryName='Germany'}");
                LOGGER.error("Actual: {}", retrievedCountry);
            }
            
            // Additional test - Try to add another country
            LOGGER.info("Adding second test country...");
            Country secondCountry = new Country("FR", "France");
            countryService.addCountry(secondCountry);
            Country retrievedSecond = countryService.findCountryByCode("FR");
            LOGGER.info("✓ Second country added and verified: {}", retrievedSecond);
            
        } catch (CountryNotFoundException e) {
            LOGGER.error("✗ FAILURE: Country not found after adding: {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("✗ FAILURE: Unexpected error during country addition: {}", e.getMessage(), e);
        }
        
        LOGGER.info("========== END: testAddCountry() ==========");
        LOGGER.info("");
        LOGGER.info("💡 To check in the database if the country is added:");
        LOGGER.info("   1. Open H2 Console: http://localhost:8080/h2-console");
        LOGGER.info("   2. Use JDBC URL: jdbc:h2:mem:testdb");
        LOGGER.info("   3. Username: sa, Password: password");
        LOGGER.info("   4. Execute query: SELECT * FROM COUNTRY WHERE CO_CODE IN ('DE', 'FR');");
        LOGGER.info("");
    }
    
    /**
     * Helper method to display all countries for verification
     */
    private void showAllCountries() {
        LOGGER.info("========== ALL COUNTRIES IN DATABASE ==========");
        try {
            countryService.getAllCountries().forEach(country -> 
                LOGGER.info("Country: {}", country)
            );
        } catch (Exception e) {
            LOGGER.error("Error retrieving all countries: {}", e.getMessage());
        }
        LOGGER.info("===============================================");
    }
}
