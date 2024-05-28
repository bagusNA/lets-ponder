package org.project.bagusna.letsponder;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.project.bagusna.letsponder.core.Router;
import org.project.bagusna.letsponder.views.AnswerView;
import org.project.bagusna.letsponder.views.HomeView;
import org.project.bagusna.letsponder.views.LoginView;
import org.project.bagusna.letsponder.views.RegisterView;
import org.project.bagusna.letsponder.views.SearchView;
import org.project.bagusna.letsponder.views.View;

public class LetsPonderApplication extends Application {
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        Image icon = new Image(getClass().getResourceAsStream("img/Lp.png"));
        stage.getIcons().add(icon);


        View[] views = {
                new HomeView(),
                new LoginView(),
                new RegisterView(),
                new SearchView(),
                new AnswerView()
        };

        Router router = Router.setup(stage, views);

        router.openView("answer");
    }

    public static void main(String[] args) {
        launch();
    }
}
