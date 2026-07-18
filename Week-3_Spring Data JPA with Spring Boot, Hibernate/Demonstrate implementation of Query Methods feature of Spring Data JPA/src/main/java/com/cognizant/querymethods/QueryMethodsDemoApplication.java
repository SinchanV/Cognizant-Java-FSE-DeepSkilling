package com.cognizant.querymethods;

import com.cognizant.querymethods.entity.Employee;
import com.cognizant.querymethods.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Main Spring Boot Application demonstrating comprehensive Spring Data JPA Query Methods
 * 
 * This application demonstrates various query method features:
 * - Search by containing text
 * - Sorting
 * - Filter with starting/ending text
 * - Fetch between dates
 * - Greater than/less than comparisons
 * - Top/First queries
 * - Combined conditions
 * - Pagination
 * - Custom queries
 */
@SpringBootApplication
public class QueryMethodsDemoApplication implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMethodsDemoApplication.class);
    
    @Autowired
    private EmployeeService employeeService;
    
    public static void main(String[] args) {
        LOGGER.info("Starting Spring Data JPA Query Methods Demonstration Application...");
        SpringApplication.run(QueryMethodsDemoApplication.class, args);
    }
    
    @Override
    @Order(2)
    public void run(String... args) throws Exception {
        LOGGER.info("Application started successfully!");
        LOGGER.info("H2 Console available at: http://localhost:8080/h2-console");
        LOGGER.info("JDBC URL: jdbc:h2:mem:querydb");
        LOGGER.info("Username: sa, Password: password");
        
        // Wait for data initialization
        Thread.sleep(2000);
        
        LOGGER.info("=".repeat(80));
        LOGGER.info("SPRING DATA JPA QUERY METHODS DEMONSTRATION");
        LOGGER.info("=".repeat(80));
        
        // Demonstrate all query method features
        demonstrateContainingTextSearch();
        demonstrateSortingQueries();
        demonstrateStartingWithQueries();
        demonstrateDateRangeQueries();
        demonstrateComparisonQueries();
        demonstrateTopFirstQueries();
        demonstrateCombinedConditions();
        demonstratePaginationQueries();
        demonstrateCustomQueries();
        demonstrateCountAndExists();
        
        LOGGER.info("=".repeat(80));
        LOGGER.info("QUERY METHODS DEMONSTRATION COMPLETED");
        LOGGER.info("=".repeat(80));
    }
    
    /**
     * Demonstrate CONTAINING text search queries
     */
    private void demonstrateContainingTextSearch() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("1. CONTAINING TEXT SEARCH QUERIES");
        LOGGER.info("=".repeat(50));
        
        // Search by first name containing
        LOGGER.info("🔍 Searching employees with first name containing 'John'");
        List<Employee> johnEmployees = employeeService.searchByFirstNameContaining("John");
        johnEmployees.forEach(emp -> LOGGER.info("  Found: {}", emp));
        
        // Search by department containing
        LOGGER.info("🔍 Searching employees in departments containing 'IT'");
        List<Employee> itEmployees = employeeService.searchByDepartmentContaining("IT");
        LOGGER.info("  Found {} IT employees", itEmployees.size());
        
        // Search by city containing
        LOGGER.info("🔍 Searching employees in cities containing 'San'");
        List<Employee> sanCityEmployees = employeeService.searchByCityContaining("San");
        sanCityEmployees.forEach(emp -> LOGGER.info("  Found: {} in {}", emp.getFirstName() + " " + emp.getLastName(), emp.getCity()));
    }
    
    /**
     * Demonstrate SORTING queries
     */
    private void demonstrateSortingQueries() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("2. SORTING QUERIES");
        LOGGER.info("=".repeat(50));
        
        // Sort by first name
        LOGGER.info("📊 All employees sorted by first name (ascending)");
        List<Employee> sortedByFirstName = employeeService.getAllEmployeesSortedByFirstName();
        sortedByFirstName.subList(0, Math.min(5, sortedByFirstName.size()))
                .forEach(emp -> LOGGER.info("  {}", emp.getFirstName() + " " + emp.getLastName()));
        
        // Sort by salary descending
        LOGGER.info("📊 Top 5 employees by salary (descending)");
        List<Employee> sortedBySalary = employeeService.getAllEmployeesSortedBySalaryDesc();
        sortedBySalary.subList(0, Math.min(5, sortedBySalary.size()))
                .forEach(emp -> LOGGER.info("  {} - ${}", emp.getFirstName() + " " + emp.getLastName(), emp.getSalary()));
        
        // Sort IT employees by hire date
        LOGGER.info("📊 IT employees sorted by hire date");
        List<Employee> itByHireDate = employeeService.getEmployeesByDepartmentSortedByHireDate("IT");
        itByHireDate.forEach(emp -> LOGGER.info("  {} hired on {}", emp.getFirstName() + " " + emp.getLastName(), emp.getHireDate()));
        
        // Custom sort by department
        LOGGER.info("📊 Finance employees sorted by salary (descending)");
        List<Employee> financeCustomSort = employeeService.getEmployeesByDepartmentWithCustomSort("Finance", "salary", "DESC");
        financeCustomSort.forEach(emp -> LOGGER.info("  {} - ${}", emp.getFirstName() + " " + emp.getLastName(), emp.getSalary()));
    }
    
    /**
     * Demonstrate STARTING WITH queries
     */
    private void demonstrateStartingWithQueries() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("3. STARTING WITH / ENDING WITH QUERIES");
        LOGGER.info("=".repeat(50));
        
        // First name starting with
        LOGGER.info("🎯 Employees with first name starting with 'J'");
        List<Employee> jNames = employeeService.searchByFirstNameStartingWith("J");
        jNames.forEach(emp -> LOGGER.info("  {}", emp.getFirstName() + " " + emp.getLastName()));
        
        // Last name starting with
        LOGGER.info("🎯 Employees with last name starting with 'S'");
        List<Employee> sLastNames = employeeService.searchByLastNameStartingWith("S");
        sLastNames.forEach(emp -> LOGGER.info("  {}", emp.getFirstName() + " " + emp.getLastName()));
        
        // Email ending with
        LOGGER.info("🎯 Employees with email ending with '@company.com'");
        List<Employee> companyEmails = employeeService.searchByEmailEndingWith("@company.com");
        LOGGER.info("  Found {} employees with company email", companyEmails.size());
    }
    
    /**
     * Demonstrate DATE RANGE queries
     */
    private void demonstrateDateRangeQueries() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("4. DATE RANGE QUERIES");
        LOGGER.info("=".repeat(50));
        
        // Employees hired between dates
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 12, 31);
        LOGGER.info("📅 Employees hired between {} and {}", startDate, endDate);
        List<Employee> hiredIn2020 = employeeService.getEmployeesHiredBetween(startDate, endDate);
        hiredIn2020.forEach(emp -> LOGGER.info("  {} hired on {}", emp.getFirstName() + " " + emp.getLastName(), emp.getHireDate()));
        
        // Employees hired after specific date
        LocalDate afterDate = LocalDate.of(2021, 1, 1);
        LOGGER.info("📅 Employees hired after {}", afterDate);
        List<Employee> hiredAfter2021 = employeeService.getEmployeesHiredAfter(afterDate);
        LOGGER.info("  Found {} employees hired after {}", hiredAfter2021.size(), afterDate);
        
        // Employees hired before specific date
        LocalDate beforeDate = LocalDate.of(2019, 1, 1);
        LOGGER.info("📅 Employees hired before {}", beforeDate);
        List<Employee> hiredBefore2019 = employeeService.getEmployeesHiredBefore(beforeDate);
        hiredBefore2019.forEach(emp -> LOGGER.info("  {} hired on {}", emp.getFirstName() + " " + emp.getLastName(), emp.getHireDate()));
    }
    
    /**
     * Demonstrate COMPARISON queries (greater than, less than)
     */
    private void demonstrateComparisonQueries() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("5. COMPARISON QUERIES (GREATER THAN / LESS THAN)");
        LOGGER.info("=".repeat(50));
        
        // Salary greater than
        BigDecimal highSalary = new BigDecimal("80000");
        LOGGER.info("💰 Employees with salary greater than ${}", highSalary);
        List<Employee> highPaidEmployees = employeeService.getEmployeesWithSalaryGreaterThan(highSalary);
        highPaidEmployees.forEach(emp -> LOGGER.info("  {} - ${}", emp.getFirstName() + " " + emp.getLastName(), emp.getSalary()));
        
        // Age greater than
        Integer ageThreshold = 30;
        LOGGER.info("👴 Employees older than {}", ageThreshold);
        List<Employee> olderEmployees = employeeService.getEmployeesWithAgeGreaterThan(ageThreshold);
        LOGGER.info("  Found {} employees older than {}", olderEmployees.size(), ageThreshold);
        
        // Age less than
        Integer youngAgeThreshold = 28;
        LOGGER.info("👶 Employees younger than {}", youngAgeThreshold);
        List<Employee> youngerEmployees = employeeService.getEmployeesWithAgeLessThan(youngAgeThreshold);
        youngerEmployees.forEach(emp -> LOGGER.info("  {} - age {}", emp.getFirstName() + " " + emp.getLastName(), emp.getAge()));
        
        // Age between with department
        LOGGER.info("🎯 IT employees aged between 25 and 35");
        List<Employee> itAgeRange = employeeService.getEmployeesWithAgeBetween(25, 35, "IT");
        itAgeRange.forEach(emp -> LOGGER.info("  {} - age {}", emp.getFirstName() + " " + emp.getLastName(), emp.getAge()));
    }
    
    /**
     * Demonstrate TOP and FIRST queries
     */
    private void demonstrateTopFirstQueries() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("6. TOP / FIRST QUERIES");
        LOGGER.info("=".repeat(50));
        
        // Top 5 by salary
        LOGGER.info("🏆 Top 5 highest paid employees");
        List<Employee> top5BySalary = employeeService.getTop5EmployeesBySalary();
        for (int i = 0; i < top5BySalary.size(); i++) {
            Employee emp = top5BySalary.get(i);
            LOGGER.info("  {}. {} - ${}", i + 1, emp.getFirstName() + " " + emp.getLastName(), emp.getSalary());
        }
        
        // Top 3 youngest
        LOGGER.info("🏆 Top 3 youngest employees");
        List<Employee> top3Youngest = employeeService.getTop3YoungestEmployees();
        for (int i = 0; i < top3Youngest.size(); i++) {
            Employee emp = top3Youngest.get(i);
            LOGGER.info("  {}. {} - age {}", i + 1, emp.getFirstName() + " " + emp.getLastName(), emp.getAge());
        }
        
        // Top paid employee in department
        LOGGER.info("🏆 Top paid employee in Engineering department");
        Optional<Employee> topEngEmployee = employeeService.getTopPaidEmployeeInDepartment("Engineering");
        topEngEmployee.ifPresent(emp -> LOGGER.info("  {} - ${}", emp.getFirstName() + " " + emp.getLastName(), emp.getSalary()));
        
        // First hired employee
        LOGGER.info("🏆 First hired employee");
        Optional<Employee> firstHired = employeeService.getFirstHiredEmployee();
        firstHired.ifPresent(emp -> LOGGER.info("  {} hired on {}", emp.getFirstName() + " " + emp.getLastName(), emp.getHireDate()));
    }
    
    /**
     * Demonstrate COMBINED CONDITIONS
     */
    private void demonstrateCombinedConditions() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("7. COMBINED CONDITIONS (AND / OR)");
        LOGGER.info("=".repeat(50));
        
        // Department AND active status
        LOGGER.info("🔗 Active employees in IT department");
        List<Employee> activeIT = employeeService.getEmployeesByDepartmentAndActiveStatus("IT", true);
        activeIT.forEach(emp -> LOGGER.info("  {} - Active: {}", emp.getFirstName() + " " + emp.getLastName(), emp.getActive()));
        
        // Department OR city
        LOGGER.info("🔗 Employees in HR department OR living in Boston");
        List<Employee> hrOrBoston = employeeService.getEmployeesByDepartmentOrCity("HR", "Boston");
        hrOrBoston.forEach(emp -> LOGGER.info("  {} - Dept: {}, City: {}", emp.getFirstName() + " " + emp.getLastName(), emp.getDepartment(), emp.getCity()));
        
        // High paid employees in specific department
        LOGGER.info("🔗 Finance employees earning more than $75,000");
        List<Employee> highPaidFinance = employeeService.getHighPaidEmployeesInDepartment("Finance", new BigDecimal("75000"));
        highPaidFinance.forEach(emp -> LOGGER.info("  {} - ${}", emp.getFirstName() + " " + emp.getLastName(), emp.getSalary()));
        
        // Active employees hired after date
        LocalDate recentDate = LocalDate.of(2020, 6, 1);
        LOGGER.info("🔗 Active employees hired after {}", recentDate);
        List<Employee> activeRecent = employeeService.getActiveEmployeesHiredAfter(recentDate);
        LOGGER.info("  Found {} active employees hired after {}", activeRecent.size(), recentDate);
    }
    
    /**
     * Demonstrate PAGINATION queries
     */
    private void demonstratePaginationQueries() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("8. PAGINATION QUERIES");
        LOGGER.info("=".repeat(50));
        
        // Paginated employees by department
        LOGGER.info("📄 IT employees - Page 1 (size 3)");
        Page<Employee> itPage1 = employeeService.getEmployeesByDepartmentPaginated("IT", 0, 3);
        LOGGER.info("  Total IT employees: {}, Pages: {}", itPage1.getTotalElements(), itPage1.getTotalPages());
        itPage1.getContent().forEach(emp -> LOGGER.info("  {}", emp.getFirstName() + " " + emp.getLastName()));
        
        // Paginated high paid employees
        LOGGER.info("📄 High paid employees (>$70,000) - Page 1 (size 5, sorted by salary)");
        Page<Employee> highPaidPage = employeeService.getHighPaidEmployeesPaginated(new BigDecimal("70000"), 0, 5, "salary");
        LOGGER.info("  Total high paid employees: {}", highPaidPage.getTotalElements());
        highPaidPage.getContent().forEach(emp -> LOGGER.info("  {} - ${}", emp.getFirstName() + " " + emp.getLastName(), emp.getSalary()));
    }
    
    /**
     * Demonstrate CUSTOM QUERIES
     */
    private void demonstrateCustomQueries() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("9. CUSTOM QUERIES");
        LOGGER.info("=".repeat(50));
        
        // Search by full name
        LOGGER.info("🔍 Employees with full name containing 'John Smith'");
        List<Employee> fullNameSearch = employeeService.searchByFullName("John Smith");
        fullNameSearch.forEach(emp -> LOGGER.info("  {}", emp.getFirstName() + " " + emp.getLastName()));
        
        // Salary range and department
        LOGGER.info("🔍 IT employees with salary between $70,000 and $90,000");
        List<Employee> salaryRangeIT = employeeService.getEmployeesBySalaryRangeAndDepartment(
            new BigDecimal("70000"), new BigDecimal("90000"), "IT");
        salaryRangeIT.forEach(emp -> LOGGER.info("  {} - ${}", emp.getFirstName() + " " + emp.getLastName(), emp.getSalary()));
    }
    
    /**
     * Demonstrate COUNT and EXISTS queries
     */
    private void demonstrateCountAndExists() {
        LOGGER.info("\n" + "=".repeat(50));
        LOGGER.info("10. COUNT AND EXISTS QUERIES");
        LOGGER.info("=".repeat(50));
        
        // Check if email exists
        String testEmail = "john.smith@company.com";
        boolean emailExists = employeeService.isEmailExists(testEmail);
        LOGGER.info("📧 Email '{}' exists: {}", testEmail, emailExists);
        
        // Count by department
        LOGGER.info("📊 Employee count by department:");
        String[] departments = {"IT", "HR", "Finance", "Marketing", "Sales", "Engineering"};
        for (String dept : departments) {
            long count = employeeService.getEmployeeCountByDepartment(dept);
            LOGGER.info("  {}: {} employees", dept, count);
        }
        
        // Count active employees
        long activeCount = employeeService.getActiveEmployeeCount();
        LOGGER.info("📊 Total active employees: {}", activeCount);
        
        // Count employees hired after date
        LocalDate countAfterDate = LocalDate.of(2020, 1, 1);
        long countAfter = employeeService.getEmployeeCountHiredAfter(countAfterDate);
        LOGGER.info("📊 Employees hired after {}: {}", countAfterDate, countAfter);
        
        // Native query count
        Long nativeCount = employeeService.getEmployeeCountByDepartmentNative("IT");
        LOGGER.info("📊 IT employees (using native query): {}", nativeCount);
    }
}
