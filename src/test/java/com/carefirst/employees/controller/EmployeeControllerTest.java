package com.carefirst.employees.controller;

import com.carefirst.employees.exception.EmployeeNotFoundException;
import com.carefirst.employees.mock.Mocks;
import com.carefirst.employees.model.EmployeeEntity;
import com.carefirst.employees.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

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

        Mockito.verify(repositoryMock, Mockito.times(1))
                .findAll();
    }

    @Test
    public void testGetEmployeeDetails_EmployeeFound() {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(Mocks.getEmployeeEntity()));
        employeeControllerMock.getEmployeeDetails(Mockito.anyLong());

        Mockito.verify(repositoryMock, Mockito.times(1))
                .findById(anyLong());
    }

    @Test
    public void testGetEmployeeDetails_EmployeeNotFound() {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeControllerMock.getEmployeeDetails(Mockito.anyLong()));

        assertTrue(exception.getMessage().contains("Could not find employee"));
    }

    @Test
    public void testUpdateEmployeeDetails_EmployeeAvailable() {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(Mocks.getEmployeeEntity()));

        employeeControllerMock.updateEmployeeDetails(Mocks.getEmployeeEntity(), anyLong());

        Mockito.verify(repositoryMock, Mockito.times(2))
                .save(any(EmployeeEntity.class));
    }

    @Test
    public void testUpdateEmployeeDetails_EmployeeNotAvailable() {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        employeeControllerMock.updateEmployeeDetails(Mocks.getEmployeeEntity(), anyLong());

        Mockito.verify(repositoryMock, Mockito.times(1))
                .save(any(EmployeeEntity.class));
    }

    @Test
    public void testDeleteEmployee() {

        employeeControllerMock.deleteEmployee(anyLong());

        Mockito.verify(repositoryMock, Mockito.times(1))
                .deleteById(anyLong());
    }

    @Test
    public void testCreateEmployee() {

        employeeControllerMock.createEmployee(Mocks.getEmployeeEntity());

        Mockito.verify(repositoryMock, Mockito.times(1))
                .save(any(EmployeeEntity.class));
    }
}
