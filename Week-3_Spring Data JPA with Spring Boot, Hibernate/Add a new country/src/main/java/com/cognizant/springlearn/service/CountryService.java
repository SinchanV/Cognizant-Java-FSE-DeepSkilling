package com.cognizant.springlearn.service;

import com.cognizant.springlearn.entity.Country;
import com.cognizant.springlearn.repository.CountryRepository;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Country operations
 * 
 * The @Transactional annotation is crucial for database operations:
 * - Spring manages the Hibernate session automatically
 * - Ensures proper transaction boundaries
 * - Handles rollback in case of exceptions
 * - Manages connection pooling and resource cleanup
 */
@Service
public class CountryService {
    
    @Autowired
    private CountryRepository countryRepository;
    
    /**
     * Find a country by country code
     * 
     * @param countryCode the country code to search for
     * @return Country object if found
     * @throws CountryNotFoundException if country not found
     */
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        // Get the country based on findById() built in method
        Optional<Country> result = countryRepository.findById(countryCode);
        
        // From the result, check if a country is found. If not found, throw CountryNotFoundException
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found with code: " + countryCode);
        }
        
        // Use get() method to return the country fetched
        Country country = result.get();
        return country;
    }
    
    /**
     * Add a new country to the database
     * 
     * @Transactional annotation ensures:
     * - Spring creates and manages the Hibernate session
     * - Transaction is automatically committed if successful
     * - Transaction is rolled back if any exception occurs
     * - Database connection is properly managed
     * 
     * @param country the country to add
     */
    @Transactional
    public void addCountry(Country country) {
        // Invoke save() method of repository to get the country added
        countryRepository.save(country);
    }
    
    /**
     * Get all countries from the database
     * @return List of all countries
     */
    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
    
    /**
     * Get count of all countries
     * @return total number of countries
     */
    @Transactional(readOnly = true)
    public long getCountryCount() {
        return countryRepository.count();
    }
}
