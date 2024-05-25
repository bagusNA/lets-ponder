package org.project.bagusna.letsponder;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.stage.Stage;
import org.project.bagusna.letsponder.views.HomeView;
import org.project.bagusna.letsponder.views.LoginView;
import org.project.bagusna.letsponder.views.RegisterView;
import org.project.bagusna.letsponder.views.SearchView;
import org.project.bagusna.letsponder.views.View;

public class LetsPonderApplication extends Application {
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

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
