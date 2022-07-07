package com.carefirst.employees.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PHONE")
public class Phone {

    @Id
    @Column(name = "ID")
    private int pid;

    @Column(name = "PHONE")
    private String phone;
}
