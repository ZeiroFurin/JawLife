package pinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class UserDataService {

    private static final UserDataService instance = new UserDataService();

    private int totalExerciseMinutes;
    private double currentWeightInKg;
    private double waterIntakeInLiters;

    private ObservableList<XYChart.Data<String, Number>> weightData;
    private ObservableList<XYChart.Data<String, Number>> calorieData;

    private UserDataService() {
        totalExerciseMinutes = 1900;
        currentWeightInKg = 90.0;
        waterIntakeInLiters = 2.5;

        weightData = FXCollections.observableArrayList(
                new XYChart.Data<>("W1", 300.0),
                new XYChart.Data<>("W2", 200.0),
                new XYChart.Data<>("W3", 50.0),
                new XYChart.Data<>("W4", 90.0)

        );

        // --- SOLUSI: DATA AWAL UNTUK BAR CHART DITAMBAHKAN DI SINI ---
        calorieData = FXCollections.observableArrayList(
                new XYChart.Data<>("W1", 10000),
                new XYChart.Data<>("W2", 9000),
                new XYChart.Data<>("W3", 5000),
                new XYChart.Data<>("W4", 8000)
        );
    }

    public static UserDataService getInstance() {
        return instance;
    }

    public void addExerciseSession(int seconds, double caloriesBurned) {
        this.totalExerciseMinutes += (seconds / 60);
        double weightLossKg = (caloriesBurned / 3500.0) * 0.45;
        this.currentWeightInKg -= weightLossKg;

        if (weightData.size() > 5) { weightData.remove(0); }
        weightData.add(new XYChart.Data<>("New", this.currentWeightInKg));

        // Menambahkan data baru juga ke bar chart
        calorieData.add(new XYChart.Data<>("New", caloriesBurned));
        if (calorieData.size() > 5) { calorieData.remove(0); }
    }

    public int getTotalExerciseMinutes() { return totalExerciseMinutes; }
    public double getCurrentWeightInKg() { return currentWeightInKg; }
    public double getWaterIntakeInLiters() { return waterIntakeInLiters; }
    public ObservableList<XYChart.Data<String, Number>> getWeightData() { return weightData; }
    public ObservableList<XYChart.Data<String, Number>> getCalorieData() { return calorieData; }
}