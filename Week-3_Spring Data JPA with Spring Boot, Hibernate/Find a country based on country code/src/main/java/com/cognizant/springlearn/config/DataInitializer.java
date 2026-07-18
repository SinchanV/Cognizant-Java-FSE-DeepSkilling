package com.cognizant.springlearn.config;

import com.cognizant.springlearn.entity.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Data initialization component to populate sample country data
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired
    private CountryService countryService;
    
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Initializing sample country data...");
        
        // Create and save sample countries
        countryService.saveCountry(new Country("IN", "India"));
        countryService.saveCountry(new Country("US", "United States"));
        countryService.saveCountry(new Country("GB", "United Kingdom"));
        countryService.saveCountry(new Country("CA", "Canada"));
        countryService.saveCountry(new Country("AU", "Australia"));
        countryService.saveCountry(new Country("DE", "Germany"));
        countryService.saveCountry(new Country("FR", "France"));
        countryService.saveCountry(new Country("JP", "Japan"));
        countryService.saveCountry(new Country("CN", "China"));
        countryService.saveCountry(new Country("BR", "Brazil"));
        
        LOGGER.info("Sample country data initialized successfully!");
        LOGGER.info("Total countries loaded: {}", countryService.getAllCountries().size());
    }
}
