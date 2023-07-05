package com.lerson.demomanager.controllers;

import com.lerson.demomanager.entities.DBQuery;
import com.lerson.demomanager.entities.Employee;
import com.lerson.demomanager.exceptions.SceneException;
import com.lerson.demomanager.utils.FXMLPath;
import com.lerson.demomanager.utils.SHA256;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void submit() {
        String username = usernameField.getText();
        String hashPassword = SHA256.parse(passwordField.getText());

        DBQuery<Employee> employee = new Employee().find(username);
        if (employee.exists()) {
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
        Stage window = (Stage) this.usernameField.getScene().getWindow();

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
}
