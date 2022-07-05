package com.carefirst.employees.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeNotFoundException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeNotFoundException.class);

    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
        LOGGER.error("Employee with id {} was not found.", id);
    }
}
