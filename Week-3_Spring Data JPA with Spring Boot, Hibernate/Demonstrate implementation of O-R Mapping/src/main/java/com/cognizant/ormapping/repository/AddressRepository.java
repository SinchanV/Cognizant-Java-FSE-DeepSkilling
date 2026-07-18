package com.cognizant.ormapping.repository;

import com.cognizant.ormapping.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Address entity
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    /**
     * Find addresses by type
     */
    List<Address> findByTypeIgnoreCase(String type);
    
    /**
     * Find addresses by city
     */
    List<Address> findByCityIgnoreCase(String city);
    
    /**
     * Find addresses by country
     */
    List<Address> findByCountryIgnoreCase(String country);
    
    /**
     * Find primary addresses
     */
    List<Address> findByIsPrimaryTrue();
    
    /**
     * Find addresses by employee id
     */
    List<Address> findByEmployeeId(Long employeeId);
    
    /**
     * Find addresses by employee email
     */
    @Query("SELECT a FROM Address a WHERE a.employee.email = :email")
    List<Address> findByEmployeeEmail(@Param("email") String email);
}
