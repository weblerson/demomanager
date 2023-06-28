package com.lerson.demomanager.entities;

import java.time.LocalDate;

public class Employee {

    private Integer id;
    private final String name;
    private final String cpf;
    private final LocalDate birthDate;
    private String email;
    private Boolean isAdmin;
    private Double baseSalary;
    private String username;

    public Employee(String name, String cpf, LocalDate birthDate, String email,
                    Boolean isAdmin, Double baseSalary, String username) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.email = email;
        this.isAdmin = isAdmin;
        this.baseSalary = baseSalary;
        this.username = username;
    }

    public Employee(Integer id, String name, String cpf, LocalDate birthDate,
                    String email, Boolean isAdmin, Double baseSalary, String username) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.email = email;
        this.isAdmin = isAdmin;
        this.baseSalary = baseSalary;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Employee(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", baseSalary=" + baseSalary +
                ", username='" + username + '\'' +
                ')';
    }
}
