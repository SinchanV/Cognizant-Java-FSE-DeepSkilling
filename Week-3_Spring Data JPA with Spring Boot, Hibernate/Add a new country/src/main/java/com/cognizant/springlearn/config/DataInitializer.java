package com.cognizant.springlearn.config;

import com.cognizant.springlearn.entity.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Data initialization component to populate initial country data
 */
@Component
@Order(1) // Ensure this runs first
public class DataInitializer implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired
    private CountryService countryService;
    
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Initializing initial country data...");
        
        // Create and add initial countries
        countryService.addCountry(new Country("IN", "India"));
        countryService.addCountry(new Country("US", "United States"));
        countryService.addCountry(new Country("GB", "United Kingdom"));
        countryService.addCountry(new Country("CA", "Canada"));
        countryService.addCountry(new Country("AU", "Australia"));
        
        LOGGER.info("Initial country data initialized successfully!");
        LOGGER.info("Total countries loaded: {}", countryService.getCountryCount());
    }
}
