module JawLife {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens pinal to javafx.fxml; 
    exports pinal;
    
    requires org.controlsfx.controls;
    requires java.desktop;

    opens JawLife to javafx.fxml;
    exports JawLife;
    exports CONTROLLERCHOSE;
    opens CONTROLLERCHOSE to javafx.fxml;
}