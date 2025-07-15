package pinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExercisePageController {

    // --- Event Handlers untuk Tombol Latihan (Sudah dimodifikasi) ---
    @FXML private void handleRunningClick(ActionEvent event) { navigateToTimerPage(event, "Running", 30); }
    @FXML private void handleCyclingClick(ActionEvent event) { navigateToTimerPage(event, "Cycling", 20); }
    @FXML private void handleSwimmingClick(ActionEvent event) { navigateToTimerPage(event, "Swimming", 25); }
    @FXML private void handlePushupsClick(ActionEvent event) { navigateToTimerPage(event, "Push-ups", 5); } // Durasi contoh
    @FXML private void handleSquatsClick(ActionEvent event) { navigateToTimerPage(event, "Squats", 1); } // Durasi contoh
    @FXML private void handleLungesClick(ActionEvent event) { navigateToTimerPage(event, "Lunges", 10); } // Durasi contoh
    @FXML private void handleYogaClick(ActionEvent event) { navigateToTimerPage(event, "Yoga", 15); }
    @FXML private void handleStretchingClick(ActionEvent event) { navigateToTimerPage(event, "Stretching", 10); }

    // --- Event Handlers untuk Navigasi Atas ---
    @FXML private void handleProgressClick(ActionEvent event) { navigateToPage(event, "progress.fxml", "JawLife Dashboard"); }
    @FXML private void handleRecipesClick() { System.out.println("Recipes navigation clicked."); }
    @FXML private void handleSettingsClick(ActionEvent event) { navigateToPage(event, "setting.fxml", "JawLife Settings"); }
    @FXML private void handleNotificationsClick() { System.out.println("Notifications icon clicked."); }
    @FXML private void handleProfileClick() { System.out.println("Profile icon clicked."); }
    @FXML private void dashboard(ActionEvent event) { navigateToPage(event, "c.fxml", "JawLife dashboard"); }



    // Metode baru untuk navigasi ke halaman timer dengan membawa data
    private void navigateToTimerPage(ActionEvent event, String exerciseName, int durationInMinutes) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pinal/timer.fxml"));
            Parent root = loader.load();

            // Dapatkan controller dari halaman timer dan kirim data
            TimerController timerController = loader.getController();
            timerController.initData(exerciseName, durationInMinutes);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(exerciseName + " Timer");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metode bantuan untuk navigasi ke halaman biasa (tanpa data)
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