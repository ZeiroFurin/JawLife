package pinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BillingController {

    public void initialize(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("billing.fxml")); // Pastikan path FXML benar
        Parent root = fxmlLoader.load();

        // Buat Scene dengan ukuran yang baru
        Scene scene = new Scene(root, 1280, 800);

        stage.setTitle("JawLife");
        stage.setScene(scene);
        stage.show();
    }
    @FXML private void handleOverviewClick(ActionEvent event){navigateToPage(event,"c.fxml","Already on dashboard."); }
    @FXML private void handleExercisesClick(ActionEvent event) { navigateToPage(event, "exercise.fxml", "JawLife Exercises"); }
    @FXML private void handleProgressClick(ActionEvent event) { navigateToPage(event, "progress.fxml", "JawLife Progress"); }
    @FXML private void handleSettingsClick(ActionEvent event) { navigateToPage(event, "setting.fxml", "JawLife Settings"); }

    public void handleUpdatePayment(ActionEvent event) {
    }

    public void handleDownloadInvoice(ActionEvent event) {
    }

    private void navigateToPage(ActionEvent event, String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pinal/" + fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


