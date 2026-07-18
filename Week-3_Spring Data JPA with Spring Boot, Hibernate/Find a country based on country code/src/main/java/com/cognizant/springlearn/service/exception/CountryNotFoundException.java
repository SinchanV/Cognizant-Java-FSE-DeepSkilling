package com.cognizant.springlearn.service.exception;

/**
 * Exception thrown when a country is not found
 * This exception is used in the CountryService when a country 
 * with the given country code does not exist in the database.
 */
public class CountryNotFoundException extends Exception {
    
    public CountryNotFoundException(String message) {
        super(message);
    }
    
    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
