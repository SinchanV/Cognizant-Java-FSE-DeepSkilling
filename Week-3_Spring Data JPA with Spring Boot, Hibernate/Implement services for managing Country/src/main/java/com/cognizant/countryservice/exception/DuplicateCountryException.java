package com.cognizant.countryservice.exception;

/**
 * Exception thrown when trying to add a duplicate country
 */
public class DuplicateCountryException extends RuntimeException {
    
    public DuplicateCountryException(String message) {
        super(message);
    }
    
    public DuplicateCountryException(String message, Throwable cause) {
        super(message, cause);
    }
}
