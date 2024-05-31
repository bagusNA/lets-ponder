package org.project.bagusna.letsponder.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.project.bagusna.letsponder.LetsPonderApplication;
import org.project.bagusna.letsponder.services.Service;
import org.project.bagusna.letsponder.views.View;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Router {
    public static Router INSTANCE;

    private final Stage stage;
    private final HashMap<String, View> views;
    private final HashMap<String, Scene> instanciatedScenes;
    private String currentViewName;

    private Router(Stage stage, View[] views) {
        this.stage = stage;
        this.views = new HashMap<>();
        this.instanciatedScenes = new HashMap<>();

        for (View view: views) {
            this.views.put(view.name, view);
        }
    }
    
    public static Router setup(Stage stage, View[] views) {
        INSTANCE = new Router(stage, views);
        return INSTANCE;
    }
    
    public static Router getInstance() {
        return INSTANCE;
    }

    public void openView(String viewName) {
        if (viewName.equals(this.currentViewName)) {
            return;
        }

        setCurrentViewName(viewName);

        Scene scene;
        View view = this.views.get(viewName);

        if (this.instanciatedScenes.containsKey(viewName)) {
            scene = this.instanciatedScenes.get(viewName);
        }
        else {
            URL layoutResource = LetsPonderApplication.class.getResource(view.getFileName());
            FXMLLoader layout = new FXMLLoader(layoutResource);

            Service.inject(layout);

            try {
                scene = new Scene(layout.load(), 800, 600);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            this.instanciatedScenes.put(view.name, scene);
        }

        stage.setTitle(view.getTitle());
        stage.setScene(scene);
        stage.show();
    }

    public void setCurrentViewName(String currentViewName) {
        this.currentViewName = currentViewName;
    }

    public Stage getStage() {
        return this.stage;
    }
}
