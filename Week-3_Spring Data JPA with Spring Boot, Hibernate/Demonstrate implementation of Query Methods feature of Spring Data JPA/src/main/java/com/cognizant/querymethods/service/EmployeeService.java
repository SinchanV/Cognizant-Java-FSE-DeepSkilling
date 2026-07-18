package com.cognizant.querymethods.service;

import com.cognizant.querymethods.entity.Employee;
import com.cognizant.querymethods.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class to demonstrate Spring Data JPA Query Methods
 */
@Service
@Transactional
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    // ========== BASIC CRUD OPERATIONS ==========
    
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    
    public long getTotalEmployeeCount() {
        return employeeRepository.count();
    }
    
    // ========== CONTAINING TEXT SEARCH ==========
    
    public List<Employee> searchByFirstNameContaining(String firstName) {
        return employeeRepository.findByFirstNameContainingIgnoreCase(firstName);
    }
    
    public List<Employee> searchByLastNameContaining(String lastName) {
        return employeeRepository.findByLastNameContaining(lastName);
    }
    
    public List<Employee> searchByDepartmentContaining(String department) {
        return employeeRepository.findByDepartmentContaining(department);
    }
    
    public List<Employee> searchByCityContaining(String city) {
        return employeeRepository.findByCityContaining(city);
    }
    
    // ========== STARTING WITH SEARCH ==========
    
    public List<Employee> searchByFirstNameStartingWith(String prefix) {
        return employeeRepository.findByFirstNameStartingWith(prefix);
    }
    
    public List<Employee> searchByLastNameStartingWith(String prefix) {
        return employeeRepository.findByLastNameStartingWith(prefix);
    }
    
    public List<Employee> searchByEmailStartingWith(String prefix) {
        return employeeRepository.findByEmailStartingWith(prefix);
    }
    
    public List<Employee> searchByEmailEndingWith(String suffix) {
        return employeeRepository.findByEmailEndingWith(suffix);
    }
    
    // ========== SORTING ==========
    
    public List<Employee> getAllEmployeesSortedByFirstName() {
        return employeeRepository.findAllByOrderByFirstNameAsc();
    }
    
    public List<Employee> getAllEmployeesSortedBySalaryDesc() {
        return employeeRepository.findAllByOrderBySalaryDesc();
    }
    
    public List<Employee> getEmployeesByDepartmentSortedByHireDate(String department) {
        return employeeRepository.findByDepartmentOrderByHireDateAsc(department);
    }
    
    public List<Employee> getActiveEmployeesSorted() {
        return employeeRepository.findByActiveOrderByLastNameAscFirstNameAsc(true);
    }
    
    public List<Employee> getEmployeesByDepartmentWithCustomSort(String department, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return employeeRepository.findByDepartment(department, sort);
    }
    
    // ========== DATE RANGE QUERIES ==========
    
    public List<Employee> getEmployeesHiredBetween(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findByHireDateBetween(startDate, endDate);
    }
    
    public List<Employee> getEmployeesHiredBetweenSorted(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findByHireDateBetweenOrderByHireDateDesc(startDate, endDate);
    }
    
    public List<Employee> getEmployeesHiredAfter(LocalDate date) {
        return employeeRepository.findByHireDateAfter(date);
    }
    
    public List<Employee> getEmployeesHiredBefore(LocalDate date) {
        return employeeRepository.findByHireDateBefore(date);
    }
    
    // ========== SALARY RANGE QUERIES ==========
    
    public List<Employee> getEmployeesWithSalaryGreaterThan(BigDecimal salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
    
    public List<Employee> getEmployeesWithSalaryLessThan(BigDecimal salary) {
        return employeeRepository.findBySalaryLessThan(salary);
    }
    
    public List<Employee> getEmployeesWithSalaryBetween(BigDecimal minSalary, BigDecimal maxSalary) {
        return employeeRepository.findBySalaryRangeAndDepartment(minSalary, maxSalary, null);
    }
    
    // ========== AGE QUERIES ==========
    
    public List<Employee> getEmployeesWithAgeGreaterThan(Integer age) {
        return employeeRepository.findByAgeGreaterThan(age);
    }
    
    public List<Employee> getEmployeesWithAgeLessThan(Integer age) {
        return employeeRepository.findByAgeLessThan(age);
    }
    
    public List<Employee> getEmployeesWithAgeBetween(Integer minAge, Integer maxAge, String department) {
        return employeeRepository.findByAgeBetweenAndDepartment(minAge, maxAge, department);
    }
    
    // ========== TOP / FIRST QUERIES ==========
    
    public List<Employee> getTop5EmployeesBySalary() {
        return employeeRepository.findTop5ByOrderBySalaryDesc();
    }
    
    public List<Employee> getTop3YoungestEmployees() {
        return employeeRepository.findTop3ByOrderByAgeAsc();
    }
    
    public List<Employee> getFirst10EmployeesByDepartment(String department) {
        return employeeRepository.findFirst10ByDepartmentOrderByHireDateAsc(department);
    }
    
    public Optional<Employee> getTopPaidEmployeeInDepartment(String department) {
        return employeeRepository.findTopByDepartmentOrderBySalaryDesc(department);
    }
    
    public Optional<Employee> getFirstHiredEmployee() {
        return employeeRepository.findFirstByOrderByHireDateAsc();
    }
    
    // ========== COMBINED CONDITIONS ==========
    
    public List<Employee> getEmployeesByDepartmentAndActiveStatus(String department, Boolean active) {
        return employeeRepository.findByDepartmentAndActive(department, active);
    }
    
    public List<Employee> getEmployeesByDepartmentOrCity(String department, String city) {
        return employeeRepository.findByDepartmentOrCity(department, city);
    }
    
    public List<Employee> getHighPaidEmployeesInDepartment(String department, BigDecimal minSalary) {
        return employeeRepository.findByDepartmentAndSalaryGreaterThan(department, minSalary);
    }
    
    public List<Employee> getActiveEmployeesHiredAfter(LocalDate date) {
        return employeeRepository.findByActiveAndHireDateAfter(true, date);
    }
    
    // ========== PAGINATION ==========
    
    public Page<Employee> getEmployeesByDepartmentPaginated(String department, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findByDepartment(department, pageable);
    }
    
    public Page<Employee> getHighPaidEmployeesPaginated(BigDecimal minSalary, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return employeeRepository.findBySalaryGreaterThan(minSalary, pageable);
    }
    
    // ========== CUSTOM QUERIES ==========
    
    public List<Employee> searchByFullName(String fullName) {
        return employeeRepository.findByFullNameContaining(fullName);
    }
    
    public List<Employee> getEmployeesBySalaryRangeAndDepartment(BigDecimal minSalary, BigDecimal maxSalary, String department) {
        return employeeRepository.findBySalaryRangeAndDepartment(minSalary, maxSalary, department);
    }
    
    // ========== COUNT AND EXISTS ==========
    
    public boolean isEmailExists(String email) {
        return employeeRepository.existsByEmail(email);
    }
    
    public long getEmployeeCountByDepartment(String department) {
        return employeeRepository.countByDepartment(department);
    }
    
    public long getActiveEmployeeCount() {
        return employeeRepository.countByActive(true);
    }
    
    public long getEmployeeCountHiredAfter(LocalDate date) {
        return employeeRepository.countByHireDateAfter(date);
    }
    
    public Long getEmployeeCountByDepartmentNative(String department) {
        return employeeRepository.countEmployeesByDepartment(department);
    }
}
