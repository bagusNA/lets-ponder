module org.project.bagusna.letsponder {
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;


    opens org.project.bagusna.letsponder to javafx.fxml;
    exports org.project.bagusna.letsponder;
    exports org.project.bagusna.letsponder.controllers;
    opens org.project.bagusna.letsponder.controllers to javafx.fxml;
}