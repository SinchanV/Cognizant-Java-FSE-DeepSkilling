package com.cognizant.countryservice.service;

import com.cognizant.countryservice.entity.Country;
import com.cognizant.countryservice.exception.CountryNotFoundException;
import com.cognizant.countryservice.exception.DuplicateCountryException;
import com.cognizant.countryservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Country operations
 */
@Service
@Transactional
public class CountryService {
    
    @Autowired
    private CountryRepository countryRepository;
    
    /**
     * Find a country by country code
     * @param countryCode the country code
     * @return Country
     * @throws CountryNotFoundException if country not found
     */
    public Country findByCountryCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode.toUpperCase())
                .orElseThrow(() -> new CountryNotFoundException("Country not found with code: " + countryCode));
    }
    
    /**
     * Add a new country
     * @param country the country to add
     * @return Country the saved country
     * @throws DuplicateCountryException if country already exists
     */
    public Country addCountry(Country country) {
        if (countryRepository.existsByCountryCode(country.getCountryCode().toUpperCase())) {
            throw new DuplicateCountryException("Country already exists with code: " + country.getCountryCode());
        }
        country.setCountryCode(country.getCountryCode().toUpperCase());
        return countryRepository.save(country);
    }
    
    /**
     * Update an existing country
     * @param countryCode the country code
     * @param updatedCountry the updated country information
     * @return Country the updated country
     * @throws CountryNotFoundException if country not found
     */
    public Country updateCountry(String countryCode, Country updatedCountry) {
        Country existingCountry = findByCountryCode(countryCode);
        existingCountry.setCountryName(updatedCountry.getCountryName());
        return countryRepository.save(existingCountry);
    }
    
    /**
     * Delete a country by country code
     * @param countryCode the country code
     * @throws CountryNotFoundException if country not found
     */
    public void deleteCountry(String countryCode) {
        Country country = findByCountryCode(countryCode);
        countryRepository.delete(country);
    }
    
    /**
     * Find countries by partial country name
     * @param partialName partial country name
     * @return List<Country>
     */
    @Transactional(readOnly = true)
    public List<Country> findCountriesByPartialName(String partialName) {
        return countryRepository.findByCountryNameContainingIgnoreCase(partialName);
    }
    
    /**
     * Get all countries
     * @return List<Country>
     */
    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAllOrderByCountryName();
    }
    
    /**
     * Get total count of countries
     * @return long
     */
    @Transactional(readOnly = true)
    public long getCountryCount() {
        return countryRepository.count();
    }
    
    /**
     * Check if country exists
     * @param countryCode the country code
     * @return boolean
     */
    @Transactional(readOnly = true)
    public boolean countryExists(String countryCode) {
        return countryRepository.existsByCountryCode(countryCode.toUpperCase());
    }
}
