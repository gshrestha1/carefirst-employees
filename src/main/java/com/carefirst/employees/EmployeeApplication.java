package com.carefirst.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.TimeZone;

@SpringBootApplication
public class EmployeeApplication {

    @PostConstruct
    public void init() {
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("CST"));
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
        System.out.println("TIME::" + LocalDateTime.now());
    }
}
