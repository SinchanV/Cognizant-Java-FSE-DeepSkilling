package com.cognizant.springlearn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Country Entity representing a country in the database
 */
@Entity
@Table(name = "country")
public class Country {
    
    @Id
    @Column(name = "co_code", length = 2)
    @NotBlank(message = "Country code cannot be blank")
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters")
    private String countryCode;
    
    @Column(name = "co_name", nullable = false)
    @NotBlank(message = "Country name cannot be blank")
    @Size(max = 100, message = "Country name cannot exceed 100 characters")
    private String countryName;
    
    // Default constructor
    public Country() {
    }
    
    // Parameterized constructor
    public Country(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }
    
    // Getters and Setters
    public String getCountryCode() {
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getCountryName() {
        return countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    @Override
    public String toString() {
        return "Country{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return countryCode != null ? countryCode.equals(country.countryCode) : country.countryCode == null;
    }
    
    @Override
    public int hashCode() {
        return countryCode != null ? countryCode.hashCode() : 0;
    }
}
