package com.cognizant.springlearn.repository;

import com.cognizant.springlearn.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Country entity operations
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    
    // Additional custom query methods can be added here if needed
    
}
