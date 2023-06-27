package com.lerson.demomanager.db;

import com.lerson.demomanager.db.exceptions.DBException;
import com.lerson.demomanager.db.exceptions.DBPropertiesNotFound;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        }

        Properties props = getProperties();
        String url = props.getProperty("url");

        try {
            conn = DriverManager.getConnection(url, props);
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn == null) {
            throw new DBException("Connection was never started!");
        }

        try {
            conn.close();
            conn = null;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private static Properties getProperties() {
        try (BufferedReader br = new BufferedReader(new FileReader("db.properties"))) {
            Properties props = new Properties();
            props.load(br);

            return props;
        }
        catch (IOException e) {
            throw new DBPropertiesNotFound(e.getMessage());
        }
    }
}
