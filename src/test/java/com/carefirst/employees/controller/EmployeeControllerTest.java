package com.carefirst.employees.controller;

import com.carefirst.employees.exception.EmployeeNotFoundException;
import com.carefirst.employees.mock.Mocks;
import com.carefirst.employees.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeControllerMock;


    @Mock
    private EmployeeRepository repositoryMock;

    @BeforeEach
    public void init() {
    }

    @Test
    public void testGetAllEmployees() {
        Mockito.when(repositoryMock.findAll()).thenReturn(Arrays.asList(Mocks.getEmployeeEntity()));
        employeeControllerMock.getAllEmployees();
    }

    @Test
    public void testGetEmployeeDetails_EmployeeFound() {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(Mocks.getEmployeeEntity()));
        employeeControllerMock.getEmployeeDetails(Mockito.anyLong());
    }

    @Test
    public void testGetEmployeeDetails_EmployeeNotFound() {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeControllerMock.getEmployeeDetails(Mockito.anyLong()));

        assertTrue(exception.getMessage().contains("Could not find employee"));
    }
}
