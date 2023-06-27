package com.lerson.demomanager.controllers;

import com.lerson.demomanager.utils.SHA256;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void submit() {
        String username = usernameField.getText();
        String hashPassword = SHA256.parse(passwordField.getText());
    }
}
