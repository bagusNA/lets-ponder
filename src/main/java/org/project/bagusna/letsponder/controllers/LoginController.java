package org.project.bagusna.letsponder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.project.bagusna.letsponder.Router;
import org.project.bagusna.letsponder.services.auth.AuthService;

public class LoginController {
    private final AuthService authService;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    public LoginController(AuthService authService) {
        this.authService = authService;
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

        if (this.authService.authenticate(username, password)) {
            Router.getInstance().openView("home");
        } else {
            showAlert("Login Gagal", "Silahkan periksa kembali username dan/atau password Anda.");
        }
    }

    private void handleRegister(ActionEvent event) {
        showAlert("Register", "belum buat");
    }
}
