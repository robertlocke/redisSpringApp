package com.rlocke.redisSpringApp.model;

import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Long salary;

    public User(){}

    public User(Integer id, String name, Long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
