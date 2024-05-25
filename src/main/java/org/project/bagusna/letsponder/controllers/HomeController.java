package org.project.bagusna.letsponder.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.project.bagusna.letsponder.Router;

public class HomeController {
    private final Router router;

    public HomeController() {
        this.router = Router.getInstance();
    }

    @FXML
    public void onSearchInputClicked(MouseEvent event) {
        router.openView("search");
    }
}