package com.cognizant.querymethods.config;

import com.cognizant.querymethods.entity.Employee;
import com.cognizant.querymethods.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data initialization component to populate sample employee data for demonstrating query methods
 */
@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired
    private EmployeeService employeeService;
    
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Initializing sample employee data for Query Methods demonstration...");
        
        // IT Department Employees
        employeeService.saveEmployee(new Employee(
            "John", "Smith", "john.smith@company.com", "IT", 
            new BigDecimal("75000"), LocalDate.of(2020, 1, 15), 28, "New York", true));
            
        employeeService.saveEmployee(new Employee(
            "Jane", "Johnson", "jane.johnson@company.com", "IT", 
            new BigDecimal("85000"), LocalDate.of(2019, 3, 20), 32, "San Francisco", true));
            
        employeeService.saveEmployee(new Employee(
            "Mike", "Wilson", "mike.wilson@company.com", "IT", 
            new BigDecimal("70000"), LocalDate.of(2021, 7, 10), 26, "Seattle", true));
            
        employeeService.saveEmployee(new Employee(
            "Sarah", "Davis", "sarah.davis@company.com", "IT", 
            new BigDecimal("90000"), LocalDate.of(2018, 11, 5), 35, "Austin", true));
        
        // HR Department Employees
        employeeService.saveEmployee(new Employee(
            "Emily", "Brown", "emily.brown@company.com", "HR", 
            new BigDecimal("65000"), LocalDate.of(2020, 6, 1), 29, "Chicago", true));
            
        employeeService.saveEmployee(new Employee(
            "David", "Miller", "david.miller@company.com", "HR", 
            new BigDecimal("72000"), LocalDate.of(2019, 9, 15), 31, "Boston", true));
            
        employeeService.saveEmployee(new Employee(
            "Lisa", "Taylor", "lisa.taylor@company.com", "HR", 
            new BigDecimal("68000"), LocalDate.of(2021, 2, 28), 27, "Miami", false));
        
        // Finance Department Employees
        employeeService.saveEmployee(new Employee(
            "Robert", "Anderson", "robert.anderson@company.com", "Finance", 
            new BigDecimal("80000"), LocalDate.of(2019, 12, 10), 33, "Denver", true));
            
        employeeService.saveEmployee(new Employee(
            "Jennifer", "Thomas", "jennifer.thomas@company.com", "Finance", 
            new BigDecimal("95000"), LocalDate.of(2018, 8, 22), 36, "Portland", true));
            
        employeeService.saveEmployee(new Employee(
            "Kevin", "Jackson", "kevin.jackson@company.com", "Finance", 
            new BigDecimal("77000"), LocalDate.of(2020, 4, 12), 30, "Phoenix", true));
        
        // Marketing Department Employees
        employeeService.saveEmployee(new Employee(
            "Amanda", "White", "amanda.white@company.com", "Marketing", 
            new BigDecimal("62000"), LocalDate.of(2021, 1, 8), 25, "Las Vegas", true));
            
        employeeService.saveEmployee(new Employee(
            "Christopher", "Harris", "christopher.harris@company.com", "Marketing", 
            new BigDecimal("69000"), LocalDate.of(2020, 10, 3), 28, "Atlanta", true));
            
        employeeService.saveEmployee(new Employee(
            "Michelle", "Martin", "michelle.martin@company.com", "Marketing", 
            new BigDecimal("64000"), LocalDate.of(2021, 5, 17), 26, "Dallas", false));
        
        // Sales Department Employees
        employeeService.saveEmployee(new Employee(
            "Daniel", "Garcia", "daniel.garcia@company.com", "Sales", 
            new BigDecimal("73000"), LocalDate.of(2019, 6, 25), 29, "Houston", true));
            
        employeeService.saveEmployee(new Employee(
            "Jessica", "Rodriguez", "jessica.rodriguez@company.com", "Sales", 
            new BigDecimal("71000"), LocalDate.of(2020, 8, 14), 31, "Philadelphia", true));
            
        employeeService.saveEmployee(new Employee(
            "Matthew", "Lewis", "matthew.lewis@company.com", "Sales", 
            new BigDecimal("76000"), LocalDate.of(2021, 3, 1), 27, "San Diego", true));
        
        // Additional employees for better demonstration
        employeeService.saveEmployee(new Employee(
            "Ashley", "Walker", "ashley.walker@company.com", "IT", 
            new BigDecimal("82000"), LocalDate.of(2022, 1, 10), 24, "Nashville", true));
            
        employeeService.saveEmployee(new Employee(
            "James", "Hall", "james.hall@company.com", "Engineering", 
            new BigDecimal("98000"), LocalDate.of(2017, 5, 18), 38, "San Jose", true));
            
        employeeService.saveEmployee(new Employee(
            "Nicole", "Allen", "nicole.allen@company.com", "Engineering", 
            new BigDecimal("93000"), LocalDate.of(2018, 2, 14), 34, "Columbus", true));
            
        employeeService.saveEmployee(new Employee(
            "Ryan", "Young", "ryan.young@company.com", "Engineering", 
            new BigDecimal("87000"), LocalDate.of(2020, 11, 8), 30, "Indianapolis", false));
            
        employeeService.saveEmployee(new Employee(
            "Stephanie", "King", "stephanie.king@company.com", "Operations", 
            new BigDecimal("66000"), LocalDate.of(2021, 9, 20), 28, "Charlotte", true));
        
        LOGGER.info("Sample employee data initialized successfully!");
        LOGGER.info("Total employees loaded: {}", employeeService.getTotalEmployeeCount());
        
        // Log department counts
        LOGGER.info("Department breakdown:");
        LOGGER.info("IT: {} employees", employeeService.getEmployeeCountByDepartment("IT"));
        LOGGER.info("HR: {} employees", employeeService.getEmployeeCountByDepartment("HR"));
        LOGGER.info("Finance: {} employees", employeeService.getEmployeeCountByDepartment("Finance"));
        LOGGER.info("Marketing: {} employees", employeeService.getEmployeeCountByDepartment("Marketing"));
        LOGGER.info("Sales: {} employees", employeeService.getEmployeeCountByDepartment("Sales"));
        LOGGER.info("Engineering: {} employees", employeeService.getEmployeeCountByDepartment("Engineering"));
        LOGGER.info("Operations: {} employees", employeeService.getEmployeeCountByDepartment("Operations"));
        LOGGER.info("Active employees: {}", employeeService.getActiveEmployeeCount());
    }
}
