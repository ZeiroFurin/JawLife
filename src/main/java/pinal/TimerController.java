package pinal;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class TimerController {

    @FXML private Label exerciseNameLabel;
    @FXML private Label timerLabel;
    @FXML private Button startButton;
    @FXML private Button stopButton;  // Tombol STOP baru
    @FXML private Button finishButton;
    @FXML private Button backButton;    // Tombol BACK baru

    private Timeline timeline;
    private int timeInSeconds;
    private AudioClip alarmSound;

    @FXML
    public void initialize() {
        // Atur kondisi awal tombol
        startButton.setVisible(true);
        startButton.setManaged(true);
        backButton.setVisible(true);
        backButton.setManaged(true);

        stopButton.setVisible(false);
        stopButton.setManaged(false);
        finishButton.setVisible(false);
        finishButton.setManaged(false);

        // Muat suara alarm
        try {
            URL soundUrl = getClass().getResource("/pinal/assets/alarm.wav");
            if (soundUrl != null) {
                alarmSound = new AudioClip(soundUrl.toExternalForm());
            } else {
                System.err.println("File suara 'alarm.wav' tidak ditemukan di /pinal/assets/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metode ini tetap sama, dipanggil dari ExercisePageController
    public void initData(String exerciseName, int durationInMinutes) {
        exerciseNameLabel.setText(exerciseName);
        this.timeInSeconds = durationInMinutes * 60;
        updateTimerLabel();
    }

    @FXML
    private void handleStartClick() {
        // Sembunyikan tombol Start dan Back, tampilkan tombol Stop
        startButton.setVisible(false);
        startButton.setManaged(false);
        backButton.setVisible(false);
        backButton.setManaged(false);
        stopButton.setVisible(true);
        stopButton.setManaged(true);

        // Mulai countdown
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeInSeconds--;
            updateTimerLabel();
            if (timeInSeconds <= 0) {
                timeline.stop();
                handleTimerFinish();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Aksi baru untuk tombol STOP
    @FXML
    private void handleStopClick(ActionEvent event) {
        if (timeline != null) {
            timeline.stop();
        }
        System.out.println("Timer stopped by user.");
        goBackToExercisePage(event);
    }

    // Aksi baru untuk tombol BACK
    @FXML
    private void handleBackClick(ActionEvent event) {
        System.out.println("Returning to exercise page.");
        goBackToExercisePage(event);
    }

    // Aksi untuk tombol FINISH (setelah timer selesai)
    @FXML
    private void handleFinishClick(ActionEvent event) {
        if (alarmSound != null) {
            alarmSound.stop();
        }
        goBackToExercisePage(event);
    }

    private void updateTimerLabel() {
        long minutes = timeInSeconds / 60;
        long seconds = timeInSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void handleTimerFinish() {
        timerLabel.setText("FINISH");
        timerLabel.setStyle("-fx-font-size: 80px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");

        // Sembunyikan tombol Stop, tampilkan tombol Finish
        stopButton.setVisible(false);
        stopButton.setManaged(false);
        finishButton.setVisible(true);
        finishButton.setManaged(true);

        // Mainkan suara alarm
        if (alarmSound != null) {
            alarmSound.play();
        }

        // Perbarui data di UserDataService
        UserDataService dataService = UserDataService.getInstance();
        double caloriesBurned = calculateCaloriesBurned();
        double waterLost = calculateWaterLost();
        dataService.addExerciseSession(timeInSeconds, caloriesBurned, waterLost);

        // Kembali ke halaman exercise
        goBackToExercisePage(null);
    }

    private double calculateCaloriesBurned() {
        return (timeInSeconds / 60.0) * 10; // 10 kalori per menit
    }

    private double calculateWaterLost() {
        return (timeInSeconds / 60.0) * 0.1; // 0.1 liter per menit
    }

    private void goBackToExercisePage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pinal/exercise.fxml"));
            Stage stage = (event != null)
                ? (Stage) ((Node) event.getSource()).getScene().getWindow()
                : (Stage) timerLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("JawLife Exercises");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}