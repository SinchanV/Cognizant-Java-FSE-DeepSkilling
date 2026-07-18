package com.cognizant.ormapping.repository;

import com.cognizant.ormapping.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Employee entity
 * Demonstrates fetching strategies and relationship queries
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    /**
     * Find employee by email
     */
    Optional<Employee> findByEmail(String email);
    
    /**
     * Find employees by department name
     * Demonstrates traversing @ManyToOne relationship
     */
    List<Employee> findByDepartmentName(String departmentName);
    
    /**
     * Find employees by department id
     */
    List<Employee> findByDepartmentId(Long departmentId);
    
    /**
     * Find employees by position
     */
    List<Employee> findByPositionIgnoreCase(String position);
    
    /**
     * Find employees with salary greater than specified amount
     */
    List<Employee> findBySalaryGreaterThan(BigDecimal salary);
    
    /**
     * Find employees who are managers (have subordinates)
     */
    @Query("SELECT DISTINCT e FROM Employee e WHERE SIZE(e.subordinates) > 0")
    List<Employee> findManagers();
    
    /**
     * Find employees who report to a specific manager
     */
    List<Employee> findByManagerId(Long managerId);
    
    /**
     * Find employees with specific address type
     */
    @Query("SELECT e FROM Employee e JOIN e.addresses a WHERE a.type = :addressType")
    List<Employee> findByAddressType(@Param("addressType") String addressType);
    
    /**
     * Find employees living in a specific city
     */
    @Query("SELECT e FROM Employee e JOIN e.addresses a WHERE a.city = :city")
    List<Employee> findByCity(@Param("city") String city);
    
    /**
     * Count employees by department
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department.name = :departmentName")
    Long countByDepartmentName(@Param("departmentName") String departmentName);
    
    /**
     * Find employees with multiple addresses
     */
    @Query("SELECT e FROM Employee e WHERE SIZE(e.addresses) > 1")
    List<Employee> findEmployeesWithMultipleAddresses();
}
