package com.lerson.demomanager.impl;

import com.lerson.demomanager.dao.EmployeeDAO;
import com.lerson.demomanager.db.DB;
import com.lerson.demomanager.db.exceptions.DBException;
import com.lerson.demomanager.entities.DBQuery;
import com.lerson.demomanager.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeDAOJDBC implements EmployeeDAO {

    @Override
    public Employee getById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Employee employee = null;

        try {
            Connection conn = DB.getConnection();

            String query = "SELECT * FROM employee" +
                    "WHERE employee.id = ?;";
            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            while (rs.next()) {
                employee = createEmployee(rs);
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }

        if (employee == null) {
            throw new DBException("Invalid ID!");
        }

        return employee;
    }

    @Override
    public DBQuery<Employee> findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        DBQuery dbQuery =  new DBQuery();

        try {
            Connection conn = DB.getConnection();

            String query = "SELECT * FROM employee" +
                    "WHERE employee.id = ?;";
            st = conn.prepareStatement(query);
            st.setInt(1, id);

            rs = st.executeQuery();
            while (rs.next()) {
                Employee employee = createEmployee(rs);
                dbQuery.add(employee);
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }

        return dbQuery;
    }

    @Override
    public Integer create(Employee employee) {
        return null;
    }

    @Override
    public Integer update(Employee employee) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    private static Employee createEmployee(ResultSet rs) {
        try {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String cpf = rs.getString("cpf");
            Date birthDate = rs.getDate("birthDate");
            String email = rs.getString("email");
            Boolean isAdmin = rs.getBoolean("isadmin");
            Double baseSalary = rs.getDouble("basesalary");
            String username = rs.getString("username");

            return new Employee(id, name, cpf, birthDate, email, isAdmin, baseSalary, username);
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
