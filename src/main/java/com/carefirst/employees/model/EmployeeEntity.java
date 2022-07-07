package com.carefirst.employees.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @OneToMany(targetEntity = Phone.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="EMPLOYEE_ID_FK",referencedColumnName = "ID")
    private List<Phone> phones;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "MANAGER_REPORTING")
    private String managerReporting;

    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "LAST_UPDATE_DATE")
    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;
}
