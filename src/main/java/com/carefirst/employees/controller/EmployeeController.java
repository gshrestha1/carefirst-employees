package com.carefirst.employees.controller;

import com.carefirst.employees.exception.EmployeeNotFoundException;
import com.carefirst.employees.model.AuthRequest;
import com.carefirst.employees.model.EmployeeEntity;
import com.carefirst.employees.repository.EmployeeRepository;
import com.carefirst.employees.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Retrieve all Employees
    @GetMapping("/employees")
    public List<EmployeeEntity> getAllEmployees() {
        LOGGER.info("Getting all Employees...");
        return repository.findAll();
    }

    // Get details of a specific employee
    @GetMapping("/employees/{id}")
    public EmployeeEntity getEmployeeDetails(@PathVariable Long id) {
        LOGGER.info("Getting details of an employee with id = {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //Update an employee details
    @PutMapping("/employees/{id}")
    public EmployeeEntity updateEmployeeDetails(@RequestBody EmployeeEntity newEmployeeEntity, @PathVariable Long id) {

        LOGGER.info("Updating an employee with id = {}", id);

        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployeeEntity.getFirstName());
                    employee.setLastName(newEmployeeEntity.getLastName());
                    employee.setEmailAddress(newEmployeeEntity.getEmailAddress());
                    employee.setPhone(newEmployeeEntity.getPhone());
                    employee.setBirthDate(newEmployeeEntity.getBirthDate());
                    employee.setJobTitle(newEmployeeEntity.getJobTitle());
                    employee.setDepartment(newEmployeeEntity.getDepartment());
                    employee.setLocation(newEmployeeEntity.getLocation());
                    employee.setStartDate(newEmployeeEntity.getStartDate());
                    employee.setManagerReporting(newEmployeeEntity.getManagerReporting());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployeeEntity.setEmployeeId(id);
                    return repository.save(newEmployeeEntity);
                });
    }

    // Delete an employee
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        LOGGER.info("Deleting an employee with id = {}", id);

        repository.deleteById(id);
    }

    // Create a new employee
    @PostMapping("/employees")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity newEmployeeEntity) {
        LOGGER.info("Creating an employee...");

        return repository.save(newEmployeeEntity);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
