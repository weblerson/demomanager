package com.lerson.demomanager.dao;

import com.lerson.demomanager.entities.DBQuery;
import com.lerson.demomanager.entities.Employee;
import com.lerson.demomanager.entities.UpdateResult;

public interface EmployeeDAO {

    Employee getById(Integer id);

    DBQuery<Employee> findById(Integer id);

    DBQuery<Employee> findByUsername(String username);

    UpdateResult create(Employee employee);

    Integer update(Employee employee);

    Integer delete(Integer id);
}
