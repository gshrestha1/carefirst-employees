package com.carefirst.employees.mock;

import com.carefirst.employees.model.EmployeeEntity;

import java.time.LocalDate;

public class Mocks {

    public static EmployeeEntity getEmployeeEntity() {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(101L);
        employeeEntity.setFirstName("John");
        employeeEntity.setLastName("Doe");
        employeeEntity.setEmailAddress("john.doe@email.com");
        employeeEntity.setPhone("2311321231");
        employeeEntity.setJobTitle("Worker");
        employeeEntity.setDepartment("Department");
        employeeEntity.setLocation("Baltimore, MD");
        employeeEntity.setStartDate(LocalDate.of(2020, 01, 01));
        employeeEntity.setManagerReporting("Alex");
        employeeEntity.setBirthDate(LocalDate.of(2000, 12, 12));

        return employeeEntity;
    }
}
