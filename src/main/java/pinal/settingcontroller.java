package pinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class settingcontroller {
    @FXML
    public void initialize() {
        System.out.println("Meals Page initialized!");
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        System.out.println("Back to Dashboard button clicked!");
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pinal/c.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("JawLife Dashboard");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Dashboard.fxml: " + e.getMessage());
        }
    }
}
