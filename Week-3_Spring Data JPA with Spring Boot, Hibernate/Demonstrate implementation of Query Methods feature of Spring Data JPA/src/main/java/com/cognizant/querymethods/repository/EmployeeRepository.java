package com.cognizant.querymethods.repository;

import com.cognizant.querymethods.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface demonstrating comprehensive Spring Data JPA Query Methods
 * 
 * Query Method Keywords Reference:
 * - Containing: for text search within a field
 * - StartingWith/EndingWith: for prefix/suffix matching
 * - Between: for range queries
 * - GreaterThan/LessThan: for comparison queries
 * - OrderBy: for sorting
 * - Top/First: for limiting results
 * - And/Or: for combining conditions
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // ========== CONTAINING TEXT SEARCH ==========
    
    /**
     * Find employees by first name containing specified text (case-insensitive)
     */
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
    
    /**
     * Find employees by last name containing specified text
     */
    List<Employee> findByLastNameContaining(String lastName);
    
    /**
     * Find employees by email containing specified text
     */
    List<Employee> findByEmailContaining(String email);
    
    /**
     * Find employees by department containing specified text
     */
    List<Employee> findByDepartmentContaining(String department);
    
    /**
     * Find employees by city containing specified text
     */
    List<Employee> findByCityContaining(String city);
    
    // ========== STARTING WITH / ENDING WITH ==========
    
    /**
     * Find employees whose first name starts with specified text
     */
    List<Employee> findByFirstNameStartingWith(String prefix);
    
    /**
     * Find employees whose last name starts with specified text
     */
    List<Employee> findByLastNameStartingWith(String prefix);
    
    /**
     * Find employees whose email starts with specified text
     */
    List<Employee> findByEmailStartingWith(String prefix);
    
    /**
     * Find employees whose email ends with specified text
     */
    List<Employee> findByEmailEndingWith(String suffix);
    
    // ========== SORTING ==========
    
    /**
     * Find all employees ordered by first name ascending
     */
    List<Employee> findAllByOrderByFirstNameAsc();
    
    /**
     * Find all employees ordered by salary descending
     */
    List<Employee> findAllByOrderBySalaryDesc();
    
    /**
     * Find employees by department ordered by hire date
     */
    List<Employee> findByDepartmentOrderByHireDateAsc(String department);
    
    /**
     * Find employees by active status ordered by last name and first name
     */
    List<Employee> findByActiveOrderByLastNameAscFirstNameAsc(Boolean active);
    
    /**
     * Find all employees with custom sorting (using Sort parameter)
     */
    List<Employee> findByDepartment(String department, Sort sort);
    
    // ========== BETWEEN DATES ==========
    
    /**
     * Find employees hired between two dates
     */
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);
    
    /**
     * Find employees hired between dates with sorting
     */
    List<Employee> findByHireDateBetweenOrderByHireDateDesc(LocalDate startDate, LocalDate endDate);
    
    // ========== GREATER THAN / LESS THAN ==========
    
    /**
     * Find employees with salary greater than specified amount
     */
    List<Employee> findBySalaryGreaterThan(BigDecimal salary);
    
    /**
     * Find employees with salary less than specified amount
     */
    List<Employee> findBySalaryLessThan(BigDecimal salary);
    
    /**
     * Find employees with salary greater than or equal to specified amount
     */
    List<Employee> findBySalaryGreaterThanEqual(BigDecimal salary);
    
    /**
     * Find employees with salary less than or equal to specified amount
     */
    List<Employee> findBySalaryLessThanEqual(BigDecimal salary);
    
    /**
     * Find employees with age greater than specified value
     */
    List<Employee> findByAgeGreaterThan(Integer age);
    
    /**
     * Find employees with age less than specified value
     */
    List<Employee> findByAgeLessThan(Integer age);
    
    /**
     * Find employees hired after specified date
     */
    List<Employee> findByHireDateAfter(LocalDate date);
    
    /**
     * Find employees hired before specified date
     */
    List<Employee> findByHireDateBefore(LocalDate date);
    
    // ========== TOP / FIRST ==========
    
    /**
     * Find top 5 employees by salary (descending)
     */
    List<Employee> findTop5ByOrderBySalaryDesc();
    
    /**
     * Find top 3 employees by age (ascending)
     */
    List<Employee> findTop3ByOrderByAgeAsc();
    
    /**
     * Find first 10 employees by department ordered by hire date
     */
    List<Employee> findFirst10ByDepartmentOrderByHireDateAsc(String department);
    
    /**
     * Find top employee by salary in a department
     */
    Optional<Employee> findTopByDepartmentOrderBySalaryDesc(String department);
    
    /**
     * Find first employee by hire date
     */
    Optional<Employee> findFirstByOrderByHireDateAsc();
    
    // ========== COMBINED CONDITIONS ==========
    
    /**
     * Find employees by department and active status
     */
    List<Employee> findByDepartmentAndActive(String department, Boolean active);
    
    /**
     * Find employees by department or city
     */
    List<Employee> findByDepartmentOrCity(String department, String city);
    
    /**
     * Find employees by department and salary greater than specified amount
     */
    List<Employee> findByDepartmentAndSalaryGreaterThan(String department, BigDecimal salary);
    
    /**
     * Find active employees hired after specified date
     */
    List<Employee> findByActiveAndHireDateAfter(Boolean active, LocalDate date);
    
    /**
     * Find employees by age between range and department
     */
    List<Employee> findByAgeBetweenAndDepartment(Integer minAge, Integer maxAge, String department);
    
    // ========== PAGINATION ==========
    
    /**
     * Find employees by department with pagination
     */
    Page<Employee> findByDepartment(String department, Pageable pageable);
    
    /**
     * Find employees with salary greater than specified amount with pagination
     */
    Page<Employee> findBySalaryGreaterThan(BigDecimal salary, Pageable pageable);
    
    // ========== CUSTOM QUERIES ==========
    
    /**
     * Custom query to find employees by full name containing text
     */
    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName) LIKE %:fullName%")
    List<Employee> findByFullNameContaining(@Param("fullName") String fullName);
    
    /**
     * Custom query to find employees with salary in specific range and department
     */
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary AND e.department = :department")
    List<Employee> findBySalaryRangeAndDepartment(@Param("minSalary") BigDecimal minSalary, 
                                                  @Param("maxSalary") BigDecimal maxSalary, 
                                                  @Param("department") String department);
    
    /**
     * Native SQL query to find employees count by department
     */
    @Query(value = "SELECT COUNT(*) FROM employee WHERE department = :department", nativeQuery = true)
    Long countEmployeesByDepartment(@Param("department") String department);
    
    // ========== EXISTS AND COUNT ==========
    
    /**
     * Check if employee exists by email
     */
    boolean existsByEmail(String email);
    
    /**
     * Count employees by department
     */
    long countByDepartment(String department);
    
    /**
     * Count active employees
     */
    long countByActive(Boolean active);
    
    /**
     * Count employees hired after specified date
     */
    long countByHireDateAfter(LocalDate date);
}
