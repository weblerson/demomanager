package com.lerson.demomanager.impl;

import com.lerson.demomanager.dao.EmployeeDAO;
import com.lerson.demomanager.db.DB;
import com.lerson.demomanager.db.exceptions.DBDateParseException;
import com.lerson.demomanager.db.exceptions.DBException;
import com.lerson.demomanager.entities.DBQuery;
import com.lerson.demomanager.entities.Employee;
import com.lerson.demomanager.entities.UpdateResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeDAOJDBC implements EmployeeDAO {

    @Override
    public Employee getById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Employee employee = null;

        try {
            Connection conn = DB.getConnection();

            String query = "SELECT * FROM employee " +
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

            String query = "SELECT * FROM employee " +
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
    public UpdateResult create(Employee employee) {
        PreparedStatement st = null;
        ResultSet rs = null;
        UpdateResult updateResult = new UpdateResult();

        try {
            Connection conn = DB.getConnection();

            String query = "INSERT INTO employee " +
                    "(name, cpf, birthdate, email, isadmin, basesalary, username) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            st = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, employee.getName());
            st.setString(2, employee.getCpf());
            st.setDate(3, new java.sql.Date(
                    new SimpleDateFormat("dd/MM/yyyy")
                            .parse(new SimpleDateFormat("dd/MM/yyyy").format(employee.getBirthDate()))
                            .getTime()
            ));
            st.setBoolean(4, employee.getAdmin());
            st.setDouble(5, employee.getBaseSalary());
            st.setString(6, employee.getUsername());

            Integer rowsAffected = st.executeUpdate();
            rs = st.getGeneratedKeys();

            while (rs.next()) {
                updateResult = new UpdateResult(rowsAffected, rs.getInt(1));
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        catch (ParseException e) {
            throw new DBDateParseException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }

        return updateResult;
    }

    @Override
    public Integer update(Employee employee) {
        PreparedStatement st = null;
        Integer rowsAffected;

        try {
            Connection conn = DB.getConnection();

            String query = "UPDATE employee " +
                            "SET email = ?, isadmin = ?, basesalary = ?, username = ?;";
            st = conn.prepareStatement(query);
            st.setString(1, employee.getEmail());
            st.setBoolean(2, employee.getAdmin());
            st.setDouble(3, employee.getBaseSalary());
            st.setString(4, employee.getUsername());

            rowsAffected = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

        return rowsAffected;
    }

    @Override
    public Integer delete(Integer id) {
        PreparedStatement st = null;
        Integer rowsAffected;

        try {
            Connection conn = DB.getConnection();

            String query = "DELETE employee " +
                            "WHERE employee.id = ?;";
            st = conn.prepareStatement(query);
            st.setInt(1, id);

            rowsAffected = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

        return rowsAffected;
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
