package pinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MealsController {

    @FXML private Label lblMonthYear;
    @FXML private Button btnPrevMonth;
    @FXML private Button btnNextMonth;
    @FXML private VBox calendarDaysGrid;

    private LocalDate currentMonth;
    private Label activeDayLabel = null;
    private LocalDate selectedDate = LocalDate.now();

    @FXML
    public void initialize() {
        currentMonth = LocalDate.now();
        displayCalendar();
    }

    private void displayCalendar() {
        calendarDaysGrid.getChildren().clear();

        lblMonthYear.setText(currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"))));

        LocalDate firstDayOfMonth = currentMonth.withDayOfMonth(1);
        int dayOfWeekValue = firstDayOfMonth.getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday
        int daysInMonth = currentMonth.lengthOfMonth();
        int startOffset = (dayOfWeekValue == 7) ? 0 : dayOfWeekValue;

        HBox currentRow = new HBox();
        currentRow.getStyleClass().add("calendar-week-row");


        LocalDate prevMonthDate = currentMonth.minusMonths(1);
        int daysInPrevMonth = prevMonthDate.lengthOfMonth();
        for (int i = 0; i < startOffset; i++) {
            LocalDate date = prevMonthDate.withDayOfMonth(daysInPrevMonth - startOffset + i + 1);
            Label dayLabel = createDayLabel(date, true);
            currentRow.getChildren().add(dayLabel);
        }

        // Tambahkan hari-hari bulan ini
        for (int day = 1; day <= daysInMonth; day++) {
            if (currentRow.getChildren().size() == 7) {
                calendarDaysGrid.getChildren().add(currentRow);
                currentRow = new HBox();
                currentRow.getStyleClass().add("calendar-week-row");
            }

            LocalDate date = currentMonth.withDayOfMonth(day);
            Label dayLabel = createDayLabel(date, false);

            if (date.isEqual(selectedDate)) {
                dayLabel.getStyleClass().add("active");
                activeDayLabel = dayLabel;
            }

            currentRow.getChildren().add(dayLabel);
        }

        int remainingSlots = 7 - currentRow.getChildren().size();
        for (int i = 1; i <= remainingSlots; i++) {
            LocalDate date = currentMonth.plusMonths(1).withDayOfMonth(i);
            Label dayLabel = createDayLabel(date, true);
            currentRow.getChildren().add(dayLabel);
        }

        if (!currentRow.getChildren().isEmpty()) {
            calendarDaysGrid.getChildren().add(currentRow);
        }
    }

    private Label createDayLabel(LocalDate date, boolean isDisabled) {
        Label dayLabel = new Label(String.valueOf(date.getDayOfMonth()));
        dayLabel.getStyleClass().add("calendar-day-label");
        dayLabel.setPrefSize(40, 40);
        dayLabel.setAlignment(javafx.geometry.Pos.CENTER);

        if (isDisabled) {
            dayLabel.getStyleClass().add("disabled");
        }

        dayLabel.setOnMouseClicked(e -> handleDateClick(date, dayLabel));

        return dayLabel;
    }

    @FXML
    private void handlePrevMonth(ActionEvent event) {
        currentMonth = currentMonth.minusMonths(1);
        selectedDate = currentMonth.withDayOfMonth(1);
        displayCalendar();
    }

    @FXML
    private void handleNextMonth(ActionEvent event) {
        currentMonth = currentMonth.plusMonths(1);
        selectedDate = currentMonth.withDayOfMonth(1);
        displayCalendar();
    }

    private void handleDateClick(LocalDate date, Label clickedLabel) {
        if (activeDayLabel != null) {
            activeDayLabel.getStyleClass().remove("active");
        }


        clickedLabel.getStyleClass().add("active");
        activeDayLabel = clickedLabel;


        selectedDate = date;
        System.out.println("Tanggal dipilih: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));


        if (date.getMonth() != currentMonth.getMonth() || date.getYear() != currentMonth.getYear()) {
            currentMonth = date.withDayOfMonth(1);
            displayCalendar();
        }

    }

    @FXML
    public void handleDashboardClick(ActionEvent event) {
        System.out.println("'Dashboard' button clicked! Navigating back to Dashboard.");
        navigateToPage(event, "/pinal/c.fxml", "JawLife Dashboard");
    }

    @FXML
    public void handleMealsClick(ActionEvent event) {
        System.out.println("'Meals' button clicked! (Already on Meals page)");

    }

    @FXML
    public void handleExerciseClick(ActionEvent event) {
        System.out.println("'Exercise' button clicked! (Implement navigation here)");
        navigateToPage(event, "/pinal/exercise.fxml", "JawLife Exercise");
    }

    @FXML
    public void handleProgressClick(ActionEvent event) {
        System.out.println("'Progress' button clicked! (Implement navigation here)");
        navigateToPage(event, "/pinal/proggress.fxml", "JawLife Progress");
    }

    @FXML
    public void handleSettingsClick(ActionEvent event) {
        System.out.println("'Settings' button clicked! (Implement navigation here)");
        navigateToPage(event, "/pinal/setting.fxml", "JawLife Settings");
    }



    private void  navigateToPage(ActionEvent event, String fxmlPath, String title) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load " + fxmlPath + ": " + e.getMessage());
        }
    }
}