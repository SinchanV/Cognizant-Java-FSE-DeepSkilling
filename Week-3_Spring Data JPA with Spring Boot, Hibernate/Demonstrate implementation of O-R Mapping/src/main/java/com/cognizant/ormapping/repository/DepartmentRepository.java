package com.cognizant.ormapping.repository;

import com.cognizant.ormapping.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Department entity
 * Demonstrates Spring Data REST integration
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    /**
     * Find department by name (case-insensitive)
     */
    Optional<Department> findByNameIgnoreCase(String name);
    
    /**
     * Find departments by location
     */
    List<Department> findByLocationIgnoreCase(String location);
    
    /**
     * Find departments with budget greater than specified amount
     */
    List<Department> findByBudgetGreaterThan(Double budget);
    
    /**
     * Find departments with at least the specified number of employees
     */
    @Query("SELECT d FROM Department d WHERE SIZE(d.employees) >= :minEmployees")
    List<Department> findDepartmentsWithMinimumEmployees(@Param("minEmployees") int minEmployees);
    
    /**
     * Find departments working on at least one project
     */
    @Query("SELECT d FROM Department d WHERE SIZE(d.projects) > 0")
    List<Department> findDepartmentsWithProjects();
    
    /**
     * Count total employees across all departments
     */
    @Query("SELECT SUM(SIZE(d.employees)) FROM Department d")
    Long countTotalEmployees();
}
