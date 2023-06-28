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

        try {
            Connection conn = DB.getConnection();

            String query = "SELECT * FROM employee" +
                    "WHERE employee.id = ?;";
            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String cpf = rs.getString("cpf");
                Date birthDate = rs.getDate("birthDate");
                String email = rs.getString("email");
                Boolean isAdmin = rs.getBoolean("isadmin");
                Double baseSalary = rs.getDouble("basesalary");
                String username = rs.getString("username");

                Employee employee = new Employee(id, name, cpf, birthDate, email, isAdmin, baseSalary, username);

                return employee;
            }

            throw new DBException("Invalid id!");
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
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
                String name = rs.getString("name");
                String cpf = rs.getString("cpf");
                Date birthDate = rs.getDate("birthDate");
                String email = rs.getString("email");
                Boolean isAdmin = rs.getBoolean("isadmin");
                Double baseSalary = rs.getDouble("basesalary");
                String username = rs.getString("username");

                Employee employee = new Employee(id, name, cpf, birthDate, email, isAdmin, baseSalary, username);
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
}
