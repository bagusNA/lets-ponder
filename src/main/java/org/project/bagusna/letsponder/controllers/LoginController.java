package org.project.bagusna.letsponder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.project.bagusna.letsponder.LetsPonderApplication;
import org.project.bagusna.letsponder.core.Router;
import org.project.bagusna.letsponder.services.auth.AuthService;

import java.net.URISyntaxException;
import java.net.URL;

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

    @FXML
    private Pane coverImageContainer;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @FXML
    public void initialize() {
        this.loadCoverImage();

        loginButton.setOnAction(this::handleLogin);
        registerButton.setOnAction(this::handleRegister);
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadCoverImage() {
        URL imgResource = LetsPonderApplication.class.getResource("img/login-cover.jpg");

        try {
            Image image = new Image(imgResource.toURI().toString());

            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1, 1, true, true, true, true)
            );
            Background background = new Background(backgroundImage);
            this.coverImageContainer.setBackground(background);
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
