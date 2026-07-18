package com.cognizant.querymethods.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.math.BigDecimal;

/**
 * Employee Entity for demonstrating Spring Data JPA Query Methods
 */
@Entity
@Table(name = "employee")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email cannot be blank")
    private String email;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "salary", precision = 10, scale = 2)
    @Positive(message = "Salary must be positive")
    private BigDecimal salary;
    
    @Column(name = "hire_date")
    @NotNull(message = "Hire date cannot be null")
    private LocalDate hireDate;
    
    @Column(name = "age")
    @Positive(message = "Age must be positive")
    private Integer age;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "active")
    private Boolean active = true;
    
    // Default constructor
    public Employee() {
    }
    
    // Constructor with all fields
    public Employee(String firstName, String lastName, String email, String department, 
                   BigDecimal salary, LocalDate hireDate, Integer age, String city, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
        this.age = age;
        this.city = city;
        this.active = active;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public BigDecimal getSalary() {
        return salary;
    }
    
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    public LocalDate getHireDate() {
        return hireDate;
    }
    
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", active=" + active +
                '}';
    }
}
