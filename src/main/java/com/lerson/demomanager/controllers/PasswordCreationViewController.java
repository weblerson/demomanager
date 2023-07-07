package com.lerson.demomanager.controllers;

import com.lerson.demomanager.entities.Employee;
import com.lerson.demomanager.session.EmployeeSession;
import com.lerson.demomanager.utils.SHA256;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PasswordCreationViewController {

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField confirmPasswordPasswordField;

    @FXML
    protected void initialize() {
        this.passwordField.setVisible(false);
        this.confirmPasswordField.setVisible(false);
    }

    @FXML
    protected void seePasswordAction() {
        if (this.passwordPasswordField.isVisible()) {
            this.passwordPasswordField.setVisible(false);

            this.passwordField.setText(this.passwordPasswordField.getText());
            this.passwordField.setVisible(true);

            return;
        }

        this.passwordField.setVisible(false);

        this.passwordPasswordField.setText(this.passwordField.getText());
        this.passwordPasswordField.setVisible(true);
    }

    @FXML
    protected void seeConfirmPasswordAction() {
        if (this.confirmPasswordPasswordField.isVisible()) {
            this.confirmPasswordPasswordField.setVisible(false);

            this.confirmPasswordField.setText(this.confirmPasswordPasswordField.getText());
            this.confirmPasswordField.setVisible(true);

            return;
        }

        this.confirmPasswordField.setVisible(false);

        this.confirmPasswordPasswordField.setText(this.confirmPasswordField.getText());
        this.confirmPasswordPasswordField.setVisible(true);
    }

    @FXML
    protected void createAction() {
        String password = this.passwordField.isVisible()
                ? this.passwordField.getText() : this.passwordPasswordField.getText();

        String confirmPassword = this.confirmPasswordField.isVisible()
                ? this.confirmPasswordField.getText() : this.confirmPasswordPasswordField.getText();

        if (password.length() == 0 || confirmPassword.length() == 0) {
            Alert alert = new Alert(
                    Alert.AlertType.WARNING,
                    "Preencha todos os campos!",
                    ButtonType.OK
            );

            alert.show();
            clearFields();

            return;
        }

        if (! password.equals(confirmPassword)) {
            Alert alert = new Alert(
                    Alert.AlertType.WARNING,
                    "As senhas precisam ser iguais!",
                    ButtonType.OK
            );

            alert.show();
            clearFields();

            return;
        }

        Employee employee = new Employee().get(EmployeeSession.instance.getId());
        employee.setPassword(SHA256.parse(password));
        employee.updatePassword();
    }

    private void clearFields() {
        this.passwordField.clear();
        this.confirmPasswordField.clear();
        this.passwordPasswordField.clear();
        this.confirmPasswordPasswordField.clear();
    }
}
