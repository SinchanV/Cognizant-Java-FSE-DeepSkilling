package com.cognizant.ormapping.service;

import com.cognizant.ormapping.entity.Address;
import com.cognizant.ormapping.entity.Department;
import com.cognizant.ormapping.entity.Employee;
import com.cognizant.ormapping.entity.Project;
import com.cognizant.ormapping.repository.AddressRepository;
import com.cognizant.ormapping.repository.DepartmentRepository;
import com.cognizant.ormapping.repository.EmployeeRepository;
import com.cognizant.ormapping.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class to demonstrate O/R Mapping relationships and their behaviors
 */
@Service
@Transactional
public class DemoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    /**
     * Demonstrates all O/R mapping relationships
     */
    public void demonstrateAllRelationships() {
        LOGGER.info("Starting O/R Mapping Demonstrations...");
        
        demonstrateManyToOne();
        demonstrateOneToMany();
        demonstrateManyToMany();
        demonstrateFetchTypes();
        demonstrateCascadeOperations();
        demonstrateBidirectionalRelationships();
        demonstrateJoinOperations();
        
        LOGGER.info("All O/R Mapping demonstrations completed!");
    }
    
    /**
     * Demonstrates @ManyToOne relationships
     */
    private void demonstrateManyToOne() {
        LOGGER.info("\n" + "=".repeat(60));
        LOGGER.info("1. DEMONSTRATING @ManyToOne RELATIONSHIPS");
        LOGGER.info("=".repeat(60));
        
        // Create department
        Department itDept = new Department("Information Technology", "IT Department", "Building A", 500000.0);
        departmentRepository.save(itDept);
        LOGGER.info("✅ Created Department: {}", itDept);
        
        // Create employees with @ManyToOne relationship to department
        Employee emp1 = new Employee("John", "Doe", "john.doe@company.com", 
                                   new BigDecimal("75000"), LocalDate.of(2020, 1, 15), 
                                   "+1-555-0101", "Senior Developer", itDept);
        
        Employee emp2 = new Employee("Jane", "Smith", "jane.smith@company.com", 
                                   new BigDecimal("85000"), LocalDate.of(2019, 6, 20), 
                                   "+1-555-0102", "Tech Lead", itDept);
        
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        
        LOGGER.info("✅ Created Employee with @ManyToOne: {}", emp1);
        LOGGER.info("✅ Created Employee with @ManyToOne: {}", emp2);
        
        // Demonstrate self-referencing @ManyToOne (Manager-Employee relationship)
        emp2.setManager(emp1); // John is Jane's manager
        employeeRepository.save(emp2);
        LOGGER.info("✅ Set Manager relationship: {} reports to {}", emp2.getFullName(), emp1.getFullName());
        
        // Create addresses with @ManyToOne relationship to employee
        Address homeAddr = new Address("HOME", "123 Main St", "New York", "NY", "10001", "USA", true, emp1);
        Address workAddr = new Address("WORK", "456 Corporate Blvd", "New York", "NY", "10002", "USA", false, emp1);
        
        addressRepository.save(homeAddr);
        addressRepository.save(workAddr);
        
        LOGGER.info("✅ Created Address with @ManyToOne: {}", homeAddr);
        LOGGER.info("✅ Created Address with @ManyToOne: {}", workAddr);
    }
    
    /**
     * Demonstrates @OneToMany relationships
     */
    private void demonstrateOneToMany() {
        LOGGER.info("\n" + "=".repeat(60));
        LOGGER.info("2. DEMONSTRATING @OneToMany RELATIONSHIPS");
        LOGGER.info("=".repeat(60));
        
        // Find department and demonstrate @OneToMany with employees
        Department itDept = departmentRepository.findByNameIgnoreCase("Information Technology").orElse(null);
        if (itDept != null) {
            LOGGER.info("📋 Department: {} has {} employees", itDept.getName(), itDept.getEmployees().size());
            
            // Demonstrate lazy loading
            for (Employee emp : itDept.getEmployees()) {
                LOGGER.info("  👤 Employee: {} - {}", emp.getFullName(), emp.getPosition());
            }
        }
        
        // Find employee and demonstrate @OneToMany with addresses
        Employee emp = employeeRepository.findByEmail("john.doe@company.com").orElse(null);
        if (emp != null) {
            LOGGER.info("📋 Employee: {} has {} addresses", emp.getFullName(), emp.getAddresses().size());
            
            for (Address addr : emp.getAddresses()) {
                LOGGER.info("  🏠 Address: {} - {}", addr.getType(), addr.getFullAddress());
            }
        }
        
        // Demonstrate self-referencing @OneToMany (Manager-Subordinates relationship)
        Employee manager = employeeRepository.findByEmail("john.doe@company.com").orElse(null);
        if (manager != null && !manager.getSubordinates().isEmpty()) {
            LOGGER.info("📋 Manager: {} has {} subordinates", manager.getFullName(), manager.getSubordinates().size());
            
            for (Employee subordinate : manager.getSubordinates()) {
                LOGGER.info("  👤 Subordinate: {} - {}", subordinate.getFullName(), subordinate.getPosition());
            }
        }
    }
    
    /**
     * Demonstrates @ManyToMany relationships
     */
    private void demonstrateManyToMany() {
        LOGGER.info("\n" + "=".repeat(60));
        LOGGER.info("3. DEMONSTRATING @ManyToMany RELATIONSHIPS");
        LOGGER.info("=".repeat(60));
        
        // Create projects
        Project project1 = new Project("E-Commerce Platform", "Build a new e-commerce platform", 
                                     LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), 
                                     new BigDecimal("500000"), Project.ProjectStatus.IN_PROGRESS, 
                                     Project.ProjectPriority.HIGH);
        
        Project project2 = new Project("Mobile App Development", "Develop mobile applications", 
                                     LocalDate.of(2024, 3, 1), LocalDate.of(2024, 9, 30), 
                                     new BigDecimal("300000"), Project.ProjectStatus.PLANNING, 
                                     Project.ProjectPriority.MEDIUM);
        
        projectRepository.save(project1);
        projectRepository.save(project2);
        
        LOGGER.info("✅ Created Project: {}", project1);
        LOGGER.info("✅ Created Project: {}", project2);
        
        // Create additional departments
        Department hrDept = new Department("Human Resources", "HR Department", "Building B", 200000.0);
        Department financeDept = new Department("Finance", "Finance Department", "Building C", 300000.0);
        
        departmentRepository.save(hrDept);
        departmentRepository.save(financeDept);
        
        // Demonstrate @ManyToMany relationship between Department and Project
        Department itDept = departmentRepository.findByNameIgnoreCase("Information Technology").orElse(null);
        if (itDept != null) {
            itDept.addProject(project1);
            itDept.addProject(project2);
            hrDept.addProject(project1); // HR also works on project1
            
            departmentRepository.save(itDept);
            departmentRepository.save(hrDept);
            
            LOGGER.info("✅ Added projects to departments - demonstrating @ManyToMany");
        }
        
        // Demonstrate @ManyToMany relationship between Project and Employee
        List<Employee> employees = employeeRepository.findByDepartmentName("Information Technology");
        for (Employee emp : employees) {
            project1.assignEmployee(emp);
            if (emp.getEmail().equals("jane.smith@company.com")) {
                project2.assignEmployee(emp); // Jane works on both projects
            }
        }
        
        projectRepository.save(project1);
        projectRepository.save(project2);
        
        LOGGER.info("✅ Assigned employees to projects - demonstrating @ManyToMany");
    }
    
    /**
     * Demonstrates different fetch types (EAGER vs LAZY)
     */
    private void demonstrateFetchTypes() {
        LOGGER.info("\n" + "=".repeat(60));
        LOGGER.info("4. DEMONSTRATING FETCH TYPES (EAGER vs LAZY)");
        LOGGER.info("=".repeat(60));
        
        // Find employee to demonstrate EAGER loading of department
        Employee emp = employeeRepository.findByEmail("john.doe@company.com").orElse(null);
        if (emp != null) {
            LOGGER.info("🔥 EAGER Loading: Employee's department is loaded immediately");
            LOGGER.info("  Employee: {} works in {}", emp.getFullName(), emp.getDepartment().getName());
            
            LOGGER.info("❄️ LAZY Loading: Employee's addresses are loaded on-demand");
            LOGGER.info("  Employee: {} has {} addresses (loaded when accessed)", 
                       emp.getFullName(), emp.getAddresses().size());
        }
        
        // Find department to demonstrate LAZY loading of employees
        Department dept = departmentRepository.findByNameIgnoreCase("Information Technology").orElse(null);
        if (dept != null) {
            LOGGER.info("❄️ LAZY Loading: Department's employees are loaded on-demand");
            LOGGER.info("  Department: {} has {} employees (loaded when accessed)", 
                       dept.getName(), dept.getEmployees().size());
            
            LOGGER.info("❄️ LAZY Loading: Department's projects are loaded on-demand");
            LOGGER.info("  Department: {} works on {} projects (loaded when accessed)", 
                       dept.getName(), dept.getProjects().size());
        }
    }
    
    /**
     * Demonstrates cascade operations
     */
    private void demonstrateCascadeOperations() {
        LOGGER.info("\n" + "=".repeat(60));
        LOGGER.info("5. DEMONSTRATING CASCADE OPERATIONS");
        LOGGER.info("=".repeat(60));
        
        // Create a new department with employees to demonstrate cascade
        Department marketingDept = new Department("Marketing", "Marketing Department", "Building D", 250000.0);
        
        Employee emp3 = new Employee("Bob", "Johnson", "bob.johnson@company.com", 
                                   new BigDecimal("65000"), LocalDate.of(2021, 3, 10), 
                                   "+1-555-0103", "Marketing Manager", marketingDept);
        
        Employee emp4 = new Employee("Alice", "Williams", "alice.williams@company.com", 
                                   new BigDecimal("55000"), LocalDate.of(2021, 8, 5), 
                                   "+1-555-0104", "Marketing Specialist", marketingDept);
        
        // Add employees to department using helper method
        marketingDept.addEmployee(emp3);
        marketingDept.addEmployee(emp4);
        
        // Save department - should cascade to employees due to CascadeType.ALL
        departmentRepository.save(marketingDept);
        
        LOGGER.info("✅ CASCADE PERSIST: Saved department with cascading to employees");
        LOGGER.info("  Department: {} with {} employees", marketingDept.getName(), marketingDept.getEmployees().size());
        
        // Add addresses to employees - should cascade when employee is updated
        Address bobHome = new Address("HOME", "789 Oak Ave", "Boston", "MA", "02101", "USA", true, emp3);
        Address aliceHome = new Address("HOME", "321 Pine St", "Boston", "MA", "02102", "USA", true, emp4);
        
        emp3.addAddress(bobHome);
        emp4.addAddress(aliceHome);
        
        employeeRepository.save(emp3);
        employeeRepository.save(emp4);
        
        LOGGER.info("✅ CASCADE PERSIST: Added addresses to employees with cascading");
    }
    
    /**
     * Demonstrates bidirectional relationships and their management
     */
    private void demonstrateBidirectionalRelationships() {
        LOGGER.info("\n" + "=".repeat(60));
        LOGGER.info("6. DEMONSTRATING BIDIRECTIONAL RELATIONSHIPS");
        LOGGER.info("=".repeat(60));
        
        // Find department and employee to demonstrate bidirectional navigation
        Department itDept = departmentRepository.findByNameIgnoreCase("Information Technology").orElse(null);
        Employee emp = employeeRepository.findByEmail("john.doe@company.com").orElse(null);
        
        if (itDept != null && emp != null) {
            LOGGER.info("🔄 Bidirectional Navigation:");
            LOGGER.info("  From Department to Employee: {} -> {}", itDept.getName(), emp.getFullName());
            LOGGER.info("  From Employee to Department: {} -> {}", emp.getFullName(), emp.getDepartment().getName());
            
            // Demonstrate project-department bidirectional relationship
            if (!itDept.getProjects().isEmpty()) {
                Project project = itDept.getProjects().iterator().next();
                LOGGER.info("  From Department to Project: {} -> {}", itDept.getName(), project.getName());
                LOGGER.info("  From Project to Department: {} -> {}", project.getName(), 
                           project.getDepartments().iterator().next().getName());
            }
        }
        
        // Demonstrate manager-subordinate bidirectional relationship
        Employee manager = employeeRepository.findByEmail("john.doe@company.com").orElse(null);
        if (manager != null && !manager.getSubordinates().isEmpty()) {
            Employee subordinate = manager.getSubordinates().get(0);
            LOGGER.info("🔄 Manager-Subordinate Bidirectional:");
            LOGGER.info("  From Manager to Subordinate: {} -> {}", manager.getFullName(), subordinate.getFullName());
            LOGGER.info("  From Subordinate to Manager: {} -> {}", subordinate.getFullName(), 
                       subordinate.getManager() != null ? subordinate.getManager().getFullName() : "None");
        }
    }
    
    /**
     * Demonstrates JOIN operations and complex queries
     */
    private void demonstrateJoinOperations() {
        LOGGER.info("\n" + "=".repeat(60));
        LOGGER.info("7. DEMONSTRATING JOIN OPERATIONS");
        LOGGER.info("=".repeat(60));
        
        // Demonstrate repository queries that use joins
        LOGGER.info("🔗 JOIN Queries:");
        
        // Find employees by department name (implicit join)
        List<Employee> itEmployees = employeeRepository.findByDepartmentName("Information Technology");
        LOGGER.info("  Employees in IT Department: {}", itEmployees.size());
        itEmployees.forEach(emp -> LOGGER.info("    - {}", emp.getFullName()));
        
        // Find employees by city (join with address)
        List<Employee> nyEmployees = employeeRepository.findByCity("New York");
        LOGGER.info("  Employees in New York: {}", nyEmployees.size());
        nyEmployees.forEach(emp -> LOGGER.info("    - {}", emp.getFullName()));
        
        // Find projects by department name (join through many-to-many)
        List<Project> itProjects = projectRepository.findByDepartmentName("Information Technology");
        LOGGER.info("  Projects involving IT Department: {}", itProjects.size());
        itProjects.forEach(project -> LOGGER.info("    - {}", project.getName()));
        
        // Find employees with multiple addresses
        List<Employee> multiAddressEmployees = employeeRepository.findEmployeesWithMultipleAddresses();
        LOGGER.info("  Employees with multiple addresses: {}", multiAddressEmployees.size());
        multiAddressEmployees.forEach(emp -> LOGGER.info("    - {} has {} addresses", 
                                                       emp.getFullName(), emp.getAddresses().size()));
        
        // Find managers (employees with subordinates)
        List<Employee> managers = employeeRepository.findManagers();
        LOGGER.info("  Managers (employees with subordinates): {}", managers.size());
        managers.forEach(manager -> LOGGER.info("    - {} manages {} people", 
                                              manager.getFullName(), manager.getSubordinates().size()));
    }
}
