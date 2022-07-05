package com.carefirst.employees.controller;

import com.carefirst.employees.exception.EmployeeNotFoundException;
import com.carefirst.employees.model.EmployeeEntity;
import com.carefirst.employees.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    //Retrieve all Employees
    @GetMapping("/employees")
    public List<EmployeeEntity> getAllEmployees() {
        LOGGER.info("Getting all Employees...");
        return repository.findAll();
    }

    // Get details of a specific employee
    @GetMapping("/employees/{id}")
    public EmployeeEntity one(@PathVariable Long id) {
        LOGGER.info("Getting details of an employee with id = {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //Update an employee details
    @PutMapping("/employees/{id}")
    public EmployeeEntity replaceEmployee(@RequestBody EmployeeEntity newEmployeeEntity, @PathVariable Long id) {

        LOGGER.info("Updating an employee with id = {}", id);

        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployeeEntity.getFirstName());
                    employee.setLastName(newEmployeeEntity.getLastName());
                    employee.setBirthDate(newEmployeeEntity.getBirthDate());
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
    public EmployeeEntity newEmployee(@RequestBody EmployeeEntity newEmployeeEntity) {
        LOGGER.info("Creating an employee...");

        return repository.save(newEmployeeEntity);
    }
}
