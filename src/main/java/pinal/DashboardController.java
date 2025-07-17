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
        // Perbarui data ringkasan
        weightLabel.setText(String.format("%.1f kg", dataService.getCurrentWeightInKg()));
        exerciseMinutesLabel.setText(String.valueOf(dataService.getTotalExerciseMinutes()));
        waterIntakeLabel.setText(String.format("%.1f L", dataService.getWaterIntakeInLiters()));

        // Perbarui data grafik
        updateLineChart();
        updateBarChart();

        // baru di update
        updateChartLabels();
    }

    private void updateLineChart() {
        // Pastikan data grafik berat badan diambil dari UserDataService
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weight Over Time");

        if (!dataService.getWeightData().isEmpty()) {
            series.getData().addAll(dataService.getWeightData());
        } else {
            // Tambahkan data default jika kosong
            series.getData().add(new XYChart.Data<>("No Data", 0));
        }

        lccar.getData().clear();
        lccar.getData().add(series);
    }

    private void updateBarChart() {
        // Pastikan data grafik kalori diambil dari UserDataService
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weekly Calorie Intake");

        if (!dataService.getCalorieData().isEmpty()) {
            series.getData().addAll(dataService.getCalorieData());
        } else {
            // Tambahkan data default jika kosong
            series.getData().add(new XYChart.Data<>("No Data", 0));
        }
        bcCar.getData().clear();
        bcCar.getData().add(series);
    }
//baru di update
    private void updateChartLabels() {
        // Perbarui label untuk "Weight Loss Over Time"
        if (!dataService.getWeightData().isEmpty()) {
            double latestWeight = dataService.getWeightData().get(dataService.getWeightData().size() - 1).getYValue().doubleValue();
            double initialWeight = dataService.getWeightData().get(0).getYValue().doubleValue();
            double weightChange = latestWeight - initialWeight;

            weightLossTimeValueLabel.setText(String.format("%.1f kg", weightChange));
            weightLossSubtitleLabel.setText("From Start to Now");
        } else {
            weightLossTimeValueLabel.setText("No Data");
            weightLossSubtitleLabel.setText("No weight data available");
        }

        // Perbarui label untuk "Weekly Calorie Intake"
        if (!dataService.getCalorieData().isEmpty()) {
            double totalCalories = dataService.getCalorieData().stream()
                .mapToDouble(data -> data.getYValue().doubleValue())
                .sum();

            calorieIntakeValueLabel.setText(String.format("%.0f kcal", totalCalories));
            calorieIntakeSubtitleLabel.setText("Total Weekly Intake");
        } else {
            calorieIntakeValueLabel.setText("No Data");
            calorieIntakeSubtitleLabel.setText("No calorie data available");
        }
    }

    @FXML private void handleOverviewClick() { System.out.println("Already on dashboard."); }
    @FXML private void handleExercisesClick(ActionEvent event) { navigateToPage(event, "exercise.fxml", "JawLife Exercises"); }
    @FXML private void handleProgressClick(ActionEvent event) { navigateToPage(event, "progress.fxml", "JawLife Progress"); }
    @FXML private void handleSettingsClick(ActionEvent event) { navigateToPage(event, "setting.fxml", "JawLife Settings"); }
    @FXML
    private void handleBillingClick(ActionEvent event) {
        navigateToPage(event, "billing.fxml", "JawLife Billing");
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