package org.project.bagusna.letsponder;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.stage.Stage;
import org.project.bagusna.letsponder.controllers.LoginController;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;
import org.project.bagusna.letsponder.repositories.UserRepository;
import org.project.bagusna.letsponder.views.HomeView;
import org.project.bagusna.letsponder.views.LoginView;
import org.project.bagusna.letsponder.views.RegisterView;
import org.project.bagusna.letsponder.views.SearchView;
import org.project.bagusna.letsponder.views.View;

import java.io.IOException;

public class LetsPonderApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        PocketbaseService pocketbaseService = new PocketbaseService("https://localhost:8080");
        UserRepository userRepository = new UserRepository();

        View[] views = {
                new HomeView(),
                new LoginView(),
                new RegisterView(),
                new SearchView(),
        };

        Router router = Router.setup(stage, views);

        router.openView("login");
    }

    public static void main(String[] args) {
        launch();
    }
}
