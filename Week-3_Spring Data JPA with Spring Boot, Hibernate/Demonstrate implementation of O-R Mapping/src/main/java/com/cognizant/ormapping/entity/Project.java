package com.cognizant.ormapping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Project Entity demonstrating @ManyToMany relationship
 * This entity shows:
 * - @ManyToMany relationship with Department
 * - @ManyToMany relationship with Employee (through project assignments)
 * - mappedBy attribute for bidirectional relationships
 * - Different cascade strategies for different relationships
 */
@Entity
@Table(name = "project")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Project name cannot be blank")
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "start_date")
    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "budget", precision = 12, scale = 2)
    private BigDecimal budget;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.PLANNING;
    
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private ProjectPriority priority = ProjectPriority.MEDIUM;
    
    /**
     * @ManyToMany relationship with Department
     * - A project can involve multiple departments
     * - A department can work on multiple projects
     * - mappedBy = "projects" refers to the 'projects' field in Department entity
     * - This is the inverse side of the relationship (Department owns the relationship)
     */
    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    @JsonBackReference("department-projects")
    private Set<Department> departments = new HashSet<>();
    
    /**
     * @ManyToMany relationship with Employee through ProjectAssignment
     * - A project can have multiple employees assigned
     * - An employee can work on multiple projects
     * - @JoinTable defines the join table for this relationship
     * - This is the owning side of the relationship
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "project_employee",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> assignedEmployees = new HashSet<>();
    
    // Enum definitions
    public enum ProjectStatus {
        PLANNING, IN_PROGRESS, ON_HOLD, COMPLETED, CANCELLED
    }
    
    public enum ProjectPriority {
        LOW, MEDIUM, HIGH, CRITICAL
    }
    
    // Constructors
    public Project() {
    }
    
    public Project(String name, String description, LocalDate startDate, LocalDate endDate, 
                  BigDecimal budget, ProjectStatus status, ProjectPriority priority) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.status = status;
        this.priority = priority;
    }
    
    // Helper methods for managing bidirectional relationships
    
    /**
     * Helper method to add a department to this project
     * Maintains bidirectional relationship integrity
     */
    public void addDepartment(Department department) {
        departments.add(department);
        department.getProjects().add(this);
    }
    
    /**
     * Helper method to remove a department from this project
     * Maintains bidirectional relationship integrity
     */
    public void removeDepartment(Department department) {
        departments.remove(department);
        department.getProjects().remove(this);
    }
    
    /**
     * Helper method to assign an employee to this project
     * Maintains bidirectional relationship integrity
     */
    public void assignEmployee(Employee employee) {
        assignedEmployees.add(employee);
    }
    
    /**
     * Helper method to unassign an employee from this project
     * Maintains bidirectional relationship integrity
     */
    public void unassignEmployee(Employee employee) {
        assignedEmployees.remove(employee);
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public BigDecimal getBudget() {
        return budget;
    }
    
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
    
    public ProjectStatus getStatus() {
        return status;
    }
    
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
    
    public ProjectPriority getPriority() {
        return priority;
    }
    
    public void setPriority(ProjectPriority priority) {
        this.priority = priority;
    }
    
    public Set<Department> getDepartments() {
        return departments;
    }
    
    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    
    public Set<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }
    
    public void setAssignedEmployees(Set<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }
    
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budget=" + budget +
                ", status=" + status +
                ", priority=" + priority +
                ", departmentCount=" + (departments != null ? departments.size() : 0) +
                ", assignedEmployeeCount=" + (assignedEmployees != null ? assignedEmployees.size() : 0) +
                '}';
    }
}
