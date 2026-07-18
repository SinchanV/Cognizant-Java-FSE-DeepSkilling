package com.cognizant.ormapping.repository;

import com.cognizant.ormapping.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Project entity
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    /**
     * Find project by name
     */
    Optional<Project> findByNameIgnoreCase(String name);
    
    /**
     * Find projects by status
     */
    List<Project> findByStatus(Project.ProjectStatus status);
    
    /**
     * Find projects by priority
     */
    List<Project> findByPriority(Project.ProjectPriority priority);
    
    /**
     * Find projects with budget greater than specified amount
     */
    List<Project> findByBudgetGreaterThan(BigDecimal budget);
    
    /**
     * Find projects starting after specific date
     */
    List<Project> findByStartDateAfter(LocalDate date);
    
    /**
     * Find projects ending before specific date
     */
    List<Project> findByEndDateBefore(LocalDate date);
    
    /**
     * Find active projects (not completed or cancelled)
     */
    @Query("SELECT p FROM Project p WHERE p.status NOT IN ('COMPLETED', 'CANCELLED')")
    List<Project> findActiveProjects();
    
    /**
     * Find projects by department name
     */
    @Query("SELECT p FROM Project p JOIN p.departments d WHERE d.name = :departmentName")
    List<Project> findByDepartmentName(@Param("departmentName") String departmentName);
    
    /**
     * Find projects with specific employee assigned
     */
    @Query("SELECT p FROM Project p JOIN p.assignedEmployees e WHERE e.id = :employeeId")
    List<Project> findByAssignedEmployeeId(@Param("employeeId") Long employeeId);
    
    /**
     * Count projects by status
     */
    @Query("SELECT COUNT(p) FROM Project p WHERE p.status = :status")
    Long countByStatus(@Param("status") Project.ProjectStatus status);
}
