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
import org.project.bagusna.letsponder.core.Router;
import org.project.bagusna.letsponder.dto.auth.AuthRecord;
import org.project.bagusna.letsponder.services.auth.AuthService;
import org.project.bagusna.letsponder.stores.AuthStore;

import java.net.URISyntaxException;
import java.net.URL;

public class LoginController extends Controller {
    private final AuthService authService;
    private final AuthStore authStore;

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

    @FXML
    private ProgressBar loadingBar;

    public LoginController(AuthService authService) {
        super();

        this.authService = authService;
        this.authStore = AuthStore.getInstance();
    }

    @FXML
    public void initialize() {
        this.loadCoverImage();

        loginButton.setOnAction(this::handleLogin);
        registerButton.setOnAction(ev -> this.router.openView("register"));
    }

    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        this.thread.execute(() -> {
            Platform.runLater(() -> {
                FadeTransition opacityTransition = new FadeTransition(Duration.millis(500), loadingBar);
                opacityTransition.setFromValue(0);
                opacityTransition.setToValue(1);
                opacityTransition.play();
            });

            AuthRecord authData = this.authService.authenticate(username, password);

            if (authData != null) {
                this.authStore.set(authData);

                Platform.runLater(() -> Router.getInstance().openView("home"));
            } else {
                Platform.runLater(() ->
                    showAlert("Login Gagal", "Silahkan periksa kembali username dan/atau password Anda.")
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
