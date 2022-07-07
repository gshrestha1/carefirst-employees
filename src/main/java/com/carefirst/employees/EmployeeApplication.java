package com.carefirst.employees;

import com.carefirst.employees.model.EmployeeEntity;
import com.carefirst.employees.model.Phone;
import com.carefirst.employees.model.User;
import com.carefirst.employees.repository.EmployeeRepository;
import com.carefirst.employees.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
public class EmployeeApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void initUsers() {
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("CST"));

        employeeRepository.save(new EmployeeEntity(
                 1L,
                "Gopal",
                "Shrestha",
                "gshrestha1@gmail.com",
                List.of(new Phone(1, "1234567890")),
                LocalDate.of(2000, 11, 11),
                "Employee",
                "Department",
                "Baltimore, MD",
                LocalDate.of(2020, 11, 11),
                "Manager",
                LocalDateTime.now(),
                LocalDateTime.now()));

        List<User> users = Stream.of(
                new User(101, "gopalshrestha", "password", "gshrestha1@gmail.com"),
                new User(102, "carefirst", "carefirst", "carefirst@carefirst.com")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}
