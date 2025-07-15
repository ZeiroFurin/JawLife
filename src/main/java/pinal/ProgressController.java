package pinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProgressController {

    @FXML private Label weightLossValueLabel;
    @FXML private Label weightLossPeriodLabel;
    @FXML private Label weightLossPercentageLabel;
    // @FXML private SVGPath weightLossChartPath; <-- Baris ini dihapus karena SVGPath sudah tidak ada

    @FXML
    public void initialize() {
        // Isi dengan data contoh untuk label
        weightLossValueLabel.setText("-5 lbs");
        weightLossPeriodLabel.setText("Last 30 Days");
        weightLossPercentageLabel.setText("-10%");

        // Baris untuk mengisi data SVG Path dihapus
        // String chartData = "M0,80 C50,40 80,120 150,90 S250,30 300,60 S400,110 450,80 S550,50 600,70";
        // weightLossChartPath.setContent(chartData);
    }

    // --- Event Handlers untuk Navigasi Atas ---
    @FXML private void handleOverviewClick(ActionEvent event) {
        navigateToPage(event, "c.fxml", "JawLife Dashboard");
    }
    @FXML private void handleMealPlanClick(ActionEvent event) {
        System.out.println("Navigating to Meal Plan...");
    }
    @FXML private void handleExercisesClick(ActionEvent event) {
        navigateToPage(event, "exercise.fxml", "JawLife Exercises");
    }
    @FXML private void handleCommunityClick(ActionEvent event) {
        System.out.println("Navigating to Community...");
    }
    @FXML private void handleSettingsClick(ActionEvent event) {
        navigateToPage(event, "setting.fxml", "JawLife Settings");
    }
    @FXML private void handleNotificationsClick() {
        System.out.println("Notifications icon clicked.");
    }
    @FXML private void handleProfileClick() {
        System.out.println("Profile icon clicked.");
    }


    // Metode bantuan untuk berpindah halaman
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