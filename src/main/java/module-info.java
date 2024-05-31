module org.project.bagusna.letsponder {
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires com.google.gson;
    requires io.github.cdimascio.dotenv.java;
    requires org.apache.httpcomponents.httpclient;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    // add icon pack modules
    requires org.kordamp.ikonli.fontawesome5;
    requires javafx.graphics;


    opens org.project.bagusna.letsponder to javafx.fxml;
    exports org.project.bagusna.letsponder;
    exports org.project.bagusna.letsponder.models;
    exports org.project.bagusna.letsponder.controllers;
    exports org.project.bagusna.letsponder.core;
    exports org.project.bagusna.letsponder.dto.auth;
    exports org.project.bagusna.letsponder.dto.formrequests;
    exports org.project.bagusna.letsponder.dto.responses;
    exports org.project.bagusna.letsponder.services.auth;
    exports org.project.bagusna.letsponder.views;
    exports org.project.bagusna.letsponder.views.components;
    exports org.project.bagusna.letsponder.services.pocketbase;
    exports org.project.bagusna.letsponder.repositories;
    opens org.project.bagusna.letsponder.controllers to javafx.fxml;
    opens org.project.bagusna.letsponder.models to com.google.gson;
}