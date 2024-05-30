package org.project.bagusna.letsponder;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.project.bagusna.letsponder.core.Router;
import org.project.bagusna.letsponder.views.*;

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
                new AnswerView(),
                new QuestionView(),
        };

        Router router = Router.setup(stage, views);

        router.openView("login");
    }

    public static void main(String[] args) {
        launch();
    }
}
