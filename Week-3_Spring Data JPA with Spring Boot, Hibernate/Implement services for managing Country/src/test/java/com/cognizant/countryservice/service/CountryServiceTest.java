package com.cognizant.countryservice.service;

import com.cognizant.countryservice.entity.Country;
import com.cognizant.countryservice.exception.CountryNotFoundException;
import com.cognizant.countryservice.exception.DuplicateCountryException;
import com.cognizant.countryservice.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CountryService
 */
@ExtendWith(MockitoExtension.class)
class CountryServiceTest {
    
    @Mock
    private CountryRepository countryRepository;
    
    @InjectMocks
    private CountryService countryService;
    
    private Country testCountry;
    
    @BeforeEach
    void setUp() {
        testCountry = new Country("US", "United States");
    }
    
    @Test
    void testFindByCountryCode_Success() {
        when(countryRepository.findByCountryCode("US")).thenReturn(Optional.of(testCountry));
        
        Country result = countryService.findByCountryCode("US");
        
        assertNotNull(result);
        assertEquals("US", result.getCountryCode());
        assertEquals("United States", result.getCountryName());
        verify(countryRepository).findByCountryCode("US");
    }
    
    @Test
    void testFindByCountryCode_NotFound() {
        when(countryRepository.findByCountryCode("XX")).thenReturn(Optional.empty());
        
        assertThrows(CountryNotFoundException.class, () -> {
            countryService.findByCountryCode("XX");
        });
        
        verify(countryRepository).findByCountryCode("XX");
    }
    
    @Test
    void testAddCountry_Success() {
        when(countryRepository.existsByCountryCode("US")).thenReturn(false);
        when(countryRepository.save(any(Country.class))).thenReturn(testCountry);
        
        Country result = countryService.addCountry(testCountry);
        
        assertNotNull(result);
        assertEquals("US", result.getCountryCode());
        verify(countryRepository).existsByCountryCode("US");
        verify(countryRepository).save(testCountry);
    }
    
    @Test
    void testAddCountry_Duplicate() {
        when(countryRepository.existsByCountryCode("US")).thenReturn(true);
        
        assertThrows(DuplicateCountryException.class, () -> {
            countryService.addCountry(testCountry);
        });
        
        verify(countryRepository).existsByCountryCode("US");
        verify(countryRepository, never()).save(any());
    }
    
    @Test
    void testUpdateCountry_Success() {
        Country updatedCountry = new Country("US", "United States of America");
        when(countryRepository.findByCountryCode("US")).thenReturn(Optional.of(testCountry));
        when(countryRepository.save(any(Country.class))).thenReturn(updatedCountry);
        
        Country result = countryService.updateCountry("US", updatedCountry);
        
        assertNotNull(result);
        assertEquals("United States of America", result.getCountryName());
        verify(countryRepository).findByCountryCode("US");
        verify(countryRepository).save(any(Country.class));
    }
    
    @Test
    void testDeleteCountry_Success() {
        when(countryRepository.findByCountryCode("US")).thenReturn(Optional.of(testCountry));
        
        countryService.deleteCountry("US");
        
        verify(countryRepository).findByCountryCode("US");
        verify(countryRepository).delete(testCountry);
    }
    
    @Test
    void testFindCountriesByPartialName() {
        List<Country> countries = Arrays.asList(
            new Country("US", "United States"),
            new Country("GB", "United Kingdom")
        );
        when(countryRepository.findByCountryNameContainingIgnoreCase("Unit")).thenReturn(countries);
        
        List<Country> result = countryService.findCountriesByPartialName("Unit");
        
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(countryRepository).findByCountryNameContainingIgnoreCase("Unit");
    }
    
    @Test
    void testGetAllCountries() {
        List<Country> countries = Arrays.asList(testCountry);
        when(countryRepository.findAllOrderByCountryName()).thenReturn(countries);
        
        List<Country> result = countryService.getAllCountries();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(countryRepository).findAllOrderByCountryName();
    }
    
    @Test
    void testCountryExists() {
        when(countryRepository.existsByCountryCode("US")).thenReturn(true);
        
        boolean result = countryService.countryExists("US");
        
        assertTrue(result);
        verify(countryRepository).existsByCountryCode("US");
    }
}
