package org.project.bagusna.letsponder.controllers;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.project.bagusna.letsponder.LetsPonderApplication;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.services.auth.AuthService;

import java.net.URISyntaxException;
import java.net.URL;

public class RegisterController extends Controller {
    private final AuthService authService;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField passwordConfirmField;

    @FXML
    private TextField emailField;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    private Pane coverImageContainer;

    @FXML
    private ProgressBar loadingBar;

    public RegisterController(AuthService authService) {
        super();

        this.authService = authService;
    }

    @FXML
    public void initialize() {
        this.loadCoverImage();

        registerButton.setOnAction(this::handleRegister);
        backButton.setOnAction(ev -> this.router.openView("login"));
    }

    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String confirmPassword = passwordConfirmField.getText();

        this.thread.execute(() -> {
            Platform.runLater(() -> {
                FadeTransition opacityTransition = new FadeTransition(Duration.millis(500), loadingBar);
                opacityTransition.setFromValue(0);
                opacityTransition.setToValue(1);
                opacityTransition.play();
            });

            User user = this.authService.register(username, email, password, confirmPassword);

            if (user != null) {
                Platform.runLater(() -> this.router.openView("login"));
            } else {
                Platform.runLater(() ->
                    this.showAlert("Register Gagal", "Silahkan periksa kembali username dan/atau password Anda.")
                );
            }

            Platform.runLater(() -> {
                FadeTransition opacityTransition = new FadeTransition(Duration.millis(500), loadingBar);
                opacityTransition.setFromValue(1);
                opacityTransition.setToValue(0);
                opacityTransition.play();

                usernameField.clear();
                passwordField.clear();

                usernameField.requestFocus();
            });
        });
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
