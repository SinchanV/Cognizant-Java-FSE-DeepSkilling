package com.cognizant.countryservice.exception;

/**
 * Exception thrown when a country is not found
 */
public class CountryNotFoundException extends RuntimeException {
    
    public CountryNotFoundException(String message) {
        super(message);
    }
    
    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
