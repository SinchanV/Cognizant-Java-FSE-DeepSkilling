package com.cognizant.ormapping.controller;

import com.cognizant.ormapping.entity.Department;
import com.cognizant.ormapping.entity.Employee;
import com.cognizant.ormapping.entity.Project;
import com.cognizant.ormapping.repository.DepartmentRepository;
import com.cognizant.ormapping.repository.EmployeeRepository;
import com.cognizant.ormapping.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * REST Controller to demonstrate O/R Mapping through API endpoints
 */
@RestController
@RequestMapping("/api/demo")
@CrossOrigin(origins = "*")
public class ORMappingController {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    /**
     * Get overview of all data
     */
    @GetMapping("/overview")
    public ResponseEntity<Map<String, Object>> getOverview() {
        Map<String, Object> overview = new HashMap<>();
        
        overview.put("totalDepartments", departmentRepository.count());
        overview.put("totalEmployees", employeeRepository.count());
        overview.put("totalProjects", projectRepository.count());
        
        // Get sample data
        overview.put("departments", departmentRepository.findAll());
        overview.put("employees", employeeRepository.findAll());
        overview.put("projects", projectRepository.findAll());
        
        return ResponseEntity.ok(overview);
    }
    
    /**
     * Get department with its employees (OneToMany)
     */
    @GetMapping("/department/{id}/employees")
    public ResponseEntity<Map<String, Object>> getDepartmentWithEmployees(@PathVariable Long id) {
        return departmentRepository.findById(id)
                .map(department -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("department", department);
                    response.put("employees", department.getEmployees());
                    response.put("employeeCount", department.getEmployees().size());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get employee with department and addresses (ManyToOne and OneToMany)
     */
    @GetMapping("/employee/{id}/details")
    public ResponseEntity<Map<String, Object>> getEmployeeDetails(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("employee", employee);
                    response.put("department", employee.getDepartment());
                    response.put("addresses", employee.getAddresses());
                    response.put("manager", employee.getManager());
                    response.put("subordinates", employee.getSubordinates());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get project with departments and employees (ManyToMany)
     */
    @GetMapping("/project/{id}/relationships")
    public ResponseEntity<Map<String, Object>> getProjectRelationships(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("project", project);
                    response.put("departments", project.getDepartments());
                    response.put("assignedEmployees", project.getAssignedEmployees());
                    response.put("departmentCount", project.getDepartments().size());
                    response.put("employeeCount", project.getAssignedEmployees().size());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get all managers (employees with subordinates)
     */
    @GetMapping("/managers")
    public ResponseEntity<List<Employee>> getManagers() {
        List<Employee> managers = employeeRepository.findManagers();
        return ResponseEntity.ok(managers);
    }
    
    /**
     * Get employees by department
     */
    @GetMapping("/department/{name}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String name) {
        List<Employee> employees = employeeRepository.findByDepartmentName(name);
        return ResponseEntity.ok(employees);
    }
    
    /**
     * Get projects by department
     */
    @GetMapping("/department/{name}/projects")
    public ResponseEntity<List<Project>> getProjectsByDepartment(@PathVariable String name) {
        List<Project> projects = projectRepository.findByDepartmentName(name);
        return ResponseEntity.ok(projects);
    }
    
    /**
     * Get relationship statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        // Basic counts
        stats.put("departmentCount", departmentRepository.count());
        stats.put("employeeCount", employeeRepository.count());
        stats.put("projectCount", projectRepository.count());
        
        // Relationship statistics
        stats.put("managersCount", employeeRepository.findManagers().size());
        stats.put("employeesWithMultipleAddresses", employeeRepository.findEmployeesWithMultipleAddresses().size());
        stats.put("activeProjects", projectRepository.findActiveProjects().size());
        
        // Department statistics
        List<Department> departments = departmentRepository.findAll();
        Map<String, Integer> deptEmployeeCount = new HashMap<>();
        for (Department dept : departments) {
            deptEmployeeCount.put(dept.getName(), dept.getEmployees().size());
        }
        stats.put("employeesPerDepartment", deptEmployeeCount);
        
        return ResponseEntity.ok(stats);
    }
}
