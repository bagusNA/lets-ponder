package org.project.bagusna.letsponder.core;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.project.bagusna.letsponder.views.View;

import java.io.IOException;

public class Router {
    public static Router INSTANCE;

    private final Stage stage;
    private final View[] views;
    private String currentViewName;

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
    
        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        }

    public void openView(String viewName) {
        setCurrentViewName(viewName);

        for (View view : views) {
            if (view.name.equals(currentViewName)) {
                try {
                    view.open(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert("Error", "ya error");
                }
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
