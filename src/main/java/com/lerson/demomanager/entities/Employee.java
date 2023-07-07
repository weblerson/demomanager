package com.lerson.demomanager.entities;

import com.lerson.demomanager.dao.EmployeeDAO;
import com.lerson.demomanager.impl.EmployeeDAOJDBC;

import java.util.Date;

public class Employee {

    private Integer id;
    private String name;
    private String cpf;
    private Date birthDate;
    private String email;
    private Boolean isAdmin;
    private Double baseSalary;
    private String username;
    private String password;
    private EmployeeDAO employeeDao = new EmployeeDAOJDBC();

    public Employee(String name, String cpf, Date birthDate, String email,
                    Boolean isAdmin, Double baseSalary, String username) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.email = email;
        this.isAdmin = isAdmin;
        this.baseSalary = baseSalary;
        this.username = username;
    }

    public Employee() {

    }

    public Employee(Integer id, String name, String cpf, Date birthDate,
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

    public Date getBirthDate() {
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

    public String getPassword() {
        return this.password;
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

    public void setPassword(String password) {
        this.password = password;
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

    public Employee get(Integer id) {
        Employee employee = this.employeeDao.getById(id);

        return employee;
    }

    public DBQuery<Employee> find(Integer id) {
        DBQuery<Employee> employees = this.employeeDao.findById(id);

        return employees;
    }

    public DBQuery<Employee> find(String username) {
        DBQuery<Employee> employees = this.employeeDao.findByUsername(username);

        return employees;
    }

    public DBQuery<Employee> find(String username, String password) {
        DBQuery<Employee> employees = this.employeeDao.findByUsernameAndPassword(username, password);

        return employees;
    }

    public UpdateResult createEmployee() {
        UpdateResult updateResult = this.employeeDao.create(this);

        return updateResult;
    }

    public Integer update() {
        Integer rowsAffected = this.employeeDao.update(this);

        return rowsAffected;
    }

    public Integer updatePassword() {
        Integer rowsAffected = this.employeeDao.updatePassword(this);

        return rowsAffected;
    }

    public Integer delete() {
        Integer rowsAffected = this.employeeDao.delete(this.id);

        return rowsAffected;
    }
}
