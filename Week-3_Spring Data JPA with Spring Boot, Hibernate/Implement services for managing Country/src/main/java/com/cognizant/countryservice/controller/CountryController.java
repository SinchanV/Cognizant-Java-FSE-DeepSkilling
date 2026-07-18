package com.cognizant.countryservice.controller;

import com.cognizant.countryservice.entity.Country;
import com.cognizant.countryservice.exception.CountryNotFoundException;
import com.cognizant.countryservice.exception.DuplicateCountryException;
import com.cognizant.countryservice.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Country operations
 */
@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "*")
public class CountryController {
    
    @Autowired
    private CountryService countryService;
    
    /**
     * Get all countries
     * @return List<Country>
     */
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }
    
    /**
     * Get country by country code
     * @param countryCode the country code
     * @return Country
     */
    @GetMapping("/{countryCode}")
    public ResponseEntity<Country> getCountryByCode(@PathVariable String countryCode) {
        try {
            Country country = countryService.findByCountryCode(countryCode);
            return ResponseEntity.ok(country);
        } catch (CountryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Add a new country
     * @param country the country to add
     * @return Country
     */
    @PostMapping
    public ResponseEntity<Country> addCountry(@Valid @RequestBody Country country) {
        try {
            Country savedCountry = countryService.addCountry(country);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCountry);
        } catch (DuplicateCountryException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    /**
     * Update an existing country
     * @param countryCode the country code
     * @param country the updated country information
     * @return Country
     */
    @PutMapping("/{countryCode}")
    public ResponseEntity<Country> updateCountry(@PathVariable String countryCode, 
                                               @Valid @RequestBody Country country) {
        try {
            Country updatedCountry = countryService.updateCountry(countryCode, country);
            return ResponseEntity.ok(updatedCountry);
        } catch (CountryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Delete a country
     * @param countryCode the country code
     * @return ResponseEntity
     */
    @DeleteMapping("/{countryCode}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String countryCode) {
        try {
            countryService.deleteCountry(countryCode);
            return ResponseEntity.noContent().build();
        } catch (CountryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Search countries by partial name
     * @param name partial country name
     * @return List<Country>
     */
    @GetMapping("/search")
    public ResponseEntity<List<Country>> searchCountriesByName(@RequestParam String name) {
        List<Country> countries = countryService.findCountriesByPartialName(name);
        return ResponseEntity.ok(countries);
    }
    
    /**
     * Get total count of countries
     * @return long
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getCountryCount() {
        long count = countryService.getCountryCount();
        return ResponseEntity.ok(count);
    }
    
    /**
     * Check if country exists
     * @param countryCode the country code
     * @return boolean
     */
    @GetMapping("/{countryCode}/exists")
    public ResponseEntity<Boolean> countryExists(@PathVariable String countryCode) {
        boolean exists = countryService.countryExists(countryCode);
        return ResponseEntity.ok(exists);
    }
}
