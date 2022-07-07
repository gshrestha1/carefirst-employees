package com.carefirst.employees;

import com.carefirst.employees.model.User;
import com.carefirst.employees.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
public class EmployeeApplication {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("CST"));

        List<User> users = Stream.of(
                new User(101, "gopalshrestha", "password", "gshrestha1@gmail.com"),
                new User(102, "carefirst", "carefirst", "user1@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}
