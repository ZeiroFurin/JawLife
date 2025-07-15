module JawLife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens JawLife to javafx.fxml;
    exports JawLife;
}