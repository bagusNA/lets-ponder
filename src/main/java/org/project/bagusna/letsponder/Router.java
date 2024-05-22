package org.project.bagusna.letsponder;

import javafx.stage.Stage;
import org.project.bagusna.letsponder.views.View;

import java.io.IOException;

public class Router {
    public static Router INSTANCE;

    private final Stage stage;
    public View[] views;
    public String currentViewName;

    private Router(Stage stage, View[] views) {
        this.stage = stage;
        this.views = views;
    }

    public static Router setup(Stage stage, View[] views) {
        INSTANCE = new Router(stage, views);

        return INSTANCE;
    }

    public static Router getInstance() {
        return INSTANCE;
    }

    public void openView(String viewName) throws IOException {
        setCurrentViewName(viewName);

        for (View view: views) {
            if (view.name.equals(currentViewName)) {
                view.open(stage);
                return;
            }
        }
    }

    public void setCurrentViewName(String currentViewName) {
        this.currentViewName = currentViewName;
    }

    public View getCurrentView() {
        for (View view: views) {
            if (view.name.equals(currentViewName)) {
                return view;
            }
        }

        return null;
    }
}
