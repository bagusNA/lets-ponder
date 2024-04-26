module org.project.bagusna.letsponder {
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;


    opens org.project.bagusna.letsponder to javafx.fxml;
    exports org.project.bagusna.letsponder;
    exports org.project.bagusna.letsponder.controllers;
    opens org.project.bagusna.letsponder.controllers to javafx.fxml;
    opens org.project.bagusna.letsponder.models to com.google.gson;
}