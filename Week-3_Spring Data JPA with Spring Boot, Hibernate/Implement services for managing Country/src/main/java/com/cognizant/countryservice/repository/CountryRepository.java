package com.cognizant.countryservice.repository;

import com.cognizant.countryservice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Country entity operations
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    
    /**
     * Find country by country code
     * @param countryCode the country code
     * @return Optional<Country>
     */
    Optional<Country> findByCountryCode(String countryCode);
    
    /**
     * Find countries by partial country name (case-insensitive)
     * @param partialName partial country name
     * @return List<Country>
     */
    @Query("SELECT c FROM Country c WHERE LOWER(c.countryName) LIKE LOWER(CONCAT('%', :partialName, '%'))")
    List<Country> findByCountryNameContainingIgnoreCase(@Param("partialName") String partialName);
    
    /**
     * Find countries by exact country name (case-insensitive)
     * @param countryName the country name
     * @return List<Country>
     */
    List<Country> findByCountryNameIgnoreCase(String countryName);
    
    /**
     * Check if country exists by country code
     * @param countryCode the country code
     * @return boolean
     */
    boolean existsByCountryCode(String countryCode);
    
    /**
     * Find all countries ordered by country name
     * @return List<Country>
     */
    @Query("SELECT c FROM Country c ORDER BY c.countryName ASC")
    List<Country> findAllOrderByCountryName();
}
