package org.project.bagusna.letsponder.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.project.bagusna.letsponder.LetsPonderApplication;

import java.io.IOException;
import java.net.URL;

public abstract class View {
    public String title;
    public String name;
    public String templateName;

    public View(String name, String templateName) {
        this.name = name;
        this.templateName = templateName;
    }

    public void open(Stage stage) throws IOException {
        URL layoutResource = LetsPonderApplication.class.getResource(this.getFileName());
        FXMLLoader layout = new FXMLLoader(layoutResource);
        Scene scene = new Scene(layout.load(), 800, 600);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFileName() {
        return "views/" + templateName + ".fxml";
    }
}
