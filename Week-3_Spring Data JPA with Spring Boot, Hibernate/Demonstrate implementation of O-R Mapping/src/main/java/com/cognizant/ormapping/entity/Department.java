package com.cognizant.ormapping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Department Entity demonstrating @OneToMany relationship
 * This is the "One" side of the One-to-Many relationship with Employee
 */
@Entity
@Table(name = "department")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Department name cannot be blank")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "budget")
    private Double budget;
    
    /**
     * @OneToMany relationship with Employee
     * - mappedBy = "department" refers to the 'department' field in Employee entity
     * - cascade = CascadeType.ALL means all operations will cascade to employees
     * - orphanRemoval = true means if an employee is removed from this collection, it will be deleted
     * - fetch = FetchType.LAZY means employees are loaded on-demand (default for @OneToMany)
     */
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("department-employees")
    private List<Employee> employees = new ArrayList<>();
    
    /**
     * @ManyToMany relationship with Project
     * - A department can work on multiple projects
     * - A project can involve multiple departments
     * - This is the owning side of the relationship (defines the join table)
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "department_project",
        joinColumns = @JoinColumn(name = "department_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @JsonManagedReference("department-projects")
    private Set<Project> projects = new HashSet<>();
    
    // Constructors
    public Department() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Department(String name, String description, String location, Double budget) {
        this();
        this.name = name;
        this.description = description;
        this.location = location;
        this.budget = budget;
    }
    
    // Helper methods for managing bidirectional relationships
    
    /**
     * Helper method to add an employee to this department
     * Maintains bidirectional relationship integrity
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }
    
    /**
     * Helper method to remove an employee from this department
     * Maintains bidirectional relationship integrity
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }
    
    /**
     * Helper method to add a project to this department
     * Maintains bidirectional relationship integrity
     */
    public void addProject(Project project) {
        projects.add(project);
        project.getDepartments().add(this);
    }
    
    /**
     * Helper method to remove a project from this department
     * Maintains bidirectional relationship integrity
     */
    public void removeProject(Project project) {
        projects.remove(project);
        project.getDepartments().remove(this);
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public Double getBudget() {
        return budget;
    }
    
    public void setBudget(Double budget) {
        this.budget = budget;
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
    
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    public Set<Project> getProjects() {
        return projects;
    }
    
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", budget=" + budget +
                ", employeeCount=" + (employees != null ? employees.size() : 0) +
                ", projectCount=" + (projects != null ? projects.size() : 0) +
                '}';
    }
}
