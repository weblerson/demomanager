package com.lerson.demomanager.controllers;

import com.lerson.demomanager.entities.DBQuery;
import com.lerson.demomanager.entities.Employee;
import com.lerson.demomanager.exceptions.SceneException;
import com.lerson.demomanager.session.EmployeeSession;
import com.lerson.demomanager.utils.FXMLPath;
import com.lerson.demomanager.utils.SHA256;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void initialize() {

        List<TextField> inputs = new ArrayList<>();
        inputs.add(this.usernameField);
        inputs.add(this.passwordField);

        inputs.forEach(input -> input.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && newValue.matches(".*\\s+.*")) {
                input.setText(oldValue);
            }
        }));
    }

    @FXML
    protected void submit() {
        String username = usernameField.getText();
        String hashPassword = SHA256.parse(passwordField.getText());

        if (username.length() == 0 || hashPassword.length() == 0) {
            Alert alert = new Alert(
                    Alert.AlertType.WARNING,
                    "Preencha todos os campos!",
                    ButtonType.OK
            );

            alert.show();

            return;
        }

        DBQuery<Employee> employee = new Employee().find(username);
        if (employee.exists()) {
            EmployeeSession.instance.setId(employee.first().getId());

            if (employee.first().getPassword().length() == 0) {
                raisePasswordCreationView();
                clearFields();

                return;
            }

            employee = new Employee().find(username, hashPassword);

            if (employee.exists()) {
                raiseMainView();
                clearFields();

                return;
            }
        }

        Alert alert = new Alert(
                Alert.AlertType.WARNING,
                "Usuário e/ou senha incorretos!",
                ButtonType.OK
        );

        alert.show();

        clearFields();
    }

    private void clearFields() {
        this.usernameField.setText("");
        this.passwordField.setText("");
    }

    private void raiseMainView() {
        FXMLLoader root = new FXMLLoader(getClass().getResource(FXMLPath.createFXMLPath("main-view.fxml")));
        Stage window = this.getWindow();

        try {
            Scene scene = new Scene(root.load());
            window.setTitle("Menu");
            window.setResizable(false);
            window.setScene(scene);
        }
        catch (IOException e) {
            throw new SceneException(e.getMessage());
        }
    }

    private void raisePasswordCreationView() {

        Employee employee = new Employee().get(EmployeeSession.instance.getId());

        FXMLLoader root = new FXMLLoader(getClass().getResource(
                FXMLPath.createFXMLPath("password-creation-view.fxml")
        ));
        Stage window = this.getWindow();

        try {
            Scene scene = new Scene(root.load());
            window.setTitle(String.format("Olá, %s!", employee.getName()));
            window.setResizable(false);
            window.setScene(scene);
        }
        catch (IOException e) {
            throw new SceneException(e.getMessage());
        }
    }

    private Stage getWindow() {
        return (Stage) this.usernameField.getScene().getWindow();
    }
}
