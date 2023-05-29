module com.iss.theatre {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.naming;
    requires java.sql;

    opens com.iss.theatre to javafx.fxml;
    exports com.iss.theatre;
    exports com.iss.theatre.gui;
    opens com.iss.theatre.gui to javafx.fxml;
}