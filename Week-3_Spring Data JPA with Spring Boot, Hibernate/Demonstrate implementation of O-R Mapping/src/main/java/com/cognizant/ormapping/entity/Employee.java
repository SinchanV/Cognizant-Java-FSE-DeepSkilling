package com.cognizant.ormapping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee Entity demonstrating @ManyToOne and @OneToMany relationships
 * This entity shows:
 * - @ManyToOne relationship with Department (Many employees belong to One department)
 * - @OneToMany relationship with Address (One employee can have Many addresses)
 * - @JoinColumn annotation for foreign key specification
 * - Different fetch types (EAGER and LAZY)
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
    
    @Column(name = "salary", precision = 10, scale = 2)
    @Positive(message = "Salary must be positive")
    private BigDecimal salary;
    
    @Column(name = "hire_date")
    @NotNull(message = "Hire date cannot be null")
    private LocalDate hireDate;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "position")
    private String position;
    
    /**
     * @ManyToOne relationship with Department
     * - Many employees belong to One department
     * - @JoinColumn specifies the foreign key column name
     * - fetch = FetchType.EAGER means department is loaded immediately with employee
     * - This is the owning side of the relationship (contains the foreign key)
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference("department-employees")
    private Department department;
    
    /**
     * @OneToMany relationship with Address
     * - One employee can have Many addresses (home, work, etc.)
     * - mappedBy = "employee" refers to the 'employee' field in Address entity
     * - cascade = CascadeType.ALL means all operations cascade to addresses
     * - orphanRemoval = true means if address is removed from collection, it's deleted
     * - fetch = FetchType.LAZY means addresses are loaded on-demand
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("employee-addresses")
    private List<Address> addresses = new ArrayList<>();
    
    /**
     * @ManyToOne relationship with Manager (Self-referencing relationship)
     * - Many employees report to One manager
     * - Manager is also an Employee (self-referencing)
     * - fetch = FetchType.LAZY to avoid circular loading issues
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonBackReference("manager-subordinates")
    private Employee manager;
    
    /**
     * @OneToMany relationship for subordinates (Self-referencing relationship)
     * - One manager can have Many subordinates
     * - mappedBy = "manager" refers to the 'manager' field above
     * - fetch = FetchType.LAZY to avoid performance issues
     */
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    @JsonManagedReference("manager-subordinates")
    private List<Employee> subordinates = new ArrayList<>();
    
    // Constructors
    public Employee() {
    }
    
    public Employee(String firstName, String lastName, String email, BigDecimal salary, 
                   LocalDate hireDate, String phone, String position, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.hireDate = hireDate;
        this.phone = phone;
        this.position = position;
        this.department = department;
    }
    
    // Helper methods for managing bidirectional relationships
    
    /**
     * Helper method to add an address to this employee
     * Maintains bidirectional relationship integrity
     */
    public void addAddress(Address address) {
        addresses.add(address);
        address.setEmployee(this);
    }
    
    /**
     * Helper method to remove an address from this employee
     * Maintains bidirectional relationship integrity
     */
    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setEmployee(null);
    }
    
    /**
     * Helper method to add a subordinate to this manager
     * Maintains bidirectional relationship integrity
     */
    public void addSubordinate(Employee subordinate) {
        subordinates.add(subordinate);
        subordinate.setManager(this);
    }
    
    /**
     * Helper method to remove a subordinate from this manager
     * Maintains bidirectional relationship integrity
     */
    public void removeSubordinate(Employee subordinate) {
        subordinates.remove(subordinate);
        subordinate.setManager(null);
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
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
    public Employee getManager() {
        return manager;
    }
    
    public void setManager(Employee manager) {
        this.manager = manager;
    }
    
    public List<Employee> getSubordinates() {
        return subordinates;
    }
    
    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", department=" + (department != null ? department.getName() : "null") +
                ", addressCount=" + (addresses != null ? addresses.size() : 0) +
                ", subordinateCount=" + (subordinates != null ? subordinates.size() : 0) +
                '}';
    }
}
