package com.cognizant.ormapping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Address Entity demonstrating @ManyToOne relationship
 * This is the "Many" side of the One-to-Many relationship with Employee
 * Shows how to use @JoinColumn for foreign key specification
 */
@Entity
@Table(name = "address")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "type", nullable = false)
    @NotBlank(message = "Address type cannot be blank")
    private String type; // HOME, WORK, BILLING, etc.
    
    @Column(name = "street_address", nullable = false)
    @NotBlank(message = "Street address cannot be blank")
    private String streetAddress;
    
    @Column(name = "city", nullable = false)
    @NotBlank(message = "City cannot be blank")
    private String city;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    @Column(name = "country", nullable = false)
    @NotBlank(message = "Country cannot be blank")
    private String country;
    
    @Column(name = "is_primary")
    private Boolean isPrimary = false;
    
    /**
     * @ManyToOne relationship with Employee
     * - Many addresses belong to One employee
     * - @JoinColumn specifies the foreign key column name
     * - fetch = FetchType.LAZY means employee is loaded on-demand
     * - This is the owning side of the relationship (contains the foreign key)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference("employee-addresses")
    private Employee employee;
    
    // Constructors
    public Address() {
    }
    
    public Address(String type, String streetAddress, String city, String state, 
                  String postalCode, String country, Boolean isPrimary, Employee employee) {
        this.type = type;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.isPrimary = isPrimary;
        this.employee = employee;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getStreetAddress() {
        return streetAddress;
    }
    
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Boolean getIsPrimary() {
        return isPrimary;
    }
    
    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(streetAddress);
        if (city != null) sb.append(", ").append(city);
        if (state != null) sb.append(", ").append(state);
        if (postalCode != null) sb.append(" ").append(postalCode);
        if (country != null) sb.append(", ").append(country);
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", isPrimary=" + isPrimary +
                ", employee=" + (employee != null ? employee.getFullName() : "null") +
                '}';
    }
}
