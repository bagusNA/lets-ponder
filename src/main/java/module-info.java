module org.project.bagusna.letsponder {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;


    opens org.project.bagusna.letsponder to javafx.fxml;
    exports org.project.bagusna.letsponder;
    exports org.project.bagusna.letsponder.contrrollers;
    opens org.project.bagusna.letsponder.contrrollers to javafx.fxml;
}