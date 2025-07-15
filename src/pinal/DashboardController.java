package pinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private Label weightLabel;
    @FXML private Label exerciseMinutesLabel;
    @FXML private Label waterIntakeLabel;
    @FXML private Label progressPercentageLabel;
    @FXML private Rectangle weightLossProgressBar;
    @FXML private Label weightLossGoalLabel;
    @FXML private Label weightLossTimeValueLabel;
    @FXML private Label calorieIntakeValueLabel;
    @FXML private LineChart<String, Number> lccar;
    @FXML private BarChart<String, Number> bcCar;
    @FXML private Label weightLossSubtitleLabel;
    @FXML private Label calorieIntakeSubtitleLabel;

    private UserDataService dataService = UserDataService.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshDashboard();
    }

    public void refreshDashboard() {
        // Mengisi data ringkasan
        weightLabel.setText(String.format("%.1f kg", dataService.getCurrentWeightInKg()));
        exerciseMinutesLabel.setText(String.valueOf(dataService.getTotalExerciseMinutes()));
        waterIntakeLabel.setText(String.format("%.1f L", dataService.getWaterIntakeInLiters()));

        // Mengisi data label chart
        weightLossTimeValueLabel.setText("-5 lbs");
        weightLossSubtitleLabel.setText("Last 3 Months -10%");
        calorieIntakeValueLabel.setText("12,000");
        calorieIntakeSubtitleLabel.setText("Last 4 Weeks -5%");

        // Mengisi data chart
        updateLineChart();
        updateBarChart();
    }

    private void updateLineChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>(dataService.getWeightData());
        lccar.getData().clear();
        lccar.getData().add(series);
    }

    private void updateBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>(dataService.getCalorieData());
        bcCar.getData().clear();
        bcCar.getData().add(series);
    }

    @FXML private void handleOverviewClick() { System.out.println("Already on dashboard."); }
    @FXML private void handleRecipesClick() { System.out.println("Recipes clicked"); }
    @FXML private void handleExercisesClick(ActionEvent event) { navigateToPage(event, "exercise.fxml", "JawLife Exercises"); }
    @FXML private void handleProgressClick(ActionEvent event) { navigateToPage(event, "progress.fxml", "JawLife Progress"); }
    @FXML private void handleSettingsClick(ActionEvent event) { navigateToPage(event, "setting.fxml", "JawLife Settings"); }
    @FXML private void handleNotificationsClick() { System.out.println("Notifications clicked."); }
    @FXML private void handleProfileClick() { System.out.println("Profile clicked."); }

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