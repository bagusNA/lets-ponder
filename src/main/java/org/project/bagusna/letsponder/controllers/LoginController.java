package org.project.bagusna.letsponder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.project.bagusna.letsponder.Router;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    private Map<String, String> userAccounts;

    public LoginController() {
        // Initialize user accounts
        userAccounts = new HashMap<>();
        userAccounts.put("wawe", "wawest");
    }

    @FXML
    public void initialize() {
        loginButton.setOnAction(this::handleLogin);
        registerButton.setOnAction(this::handleRegister);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            System.out.println("Autentikasi Sukses");
            Router.getInstance().openView("home");
        } else {
            System.out.println("Autentikasi Gagal");
            showAlert("Login Gagal", "Username atau Password tidak valid");
        }
    }

    private void handleRegister(ActionEvent event) {
        showAlert("Register", "belum buat");
    }

    private boolean authenticate(String username, String password) {
        return userAccounts.containsKey(username) && userAccounts.get(username).equals(password);
    }
}
