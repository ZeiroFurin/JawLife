package JawLife;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Label totalClientsLabel;
    @FXML
    private Label activeClientsLabel;
    @FXML
    private Label newClientsLabel;
    @FXML
    private TableView<JawLifeModel> activityTable;
    @FXML
    private TableColumn<JawLifeModel, String> clientNameColumn;
    @FXML
    private TableColumn<JawLifeModel, String> programColumn;
    @FXML
    private TableColumn<JawLifeModel, String> lastActivityColumn;
    @FXML
    private Label greetingLabel;
    @FXML
    private TableColumn<JawLifeModel, String> statusColumn;
    private ObservableList<JawLifeModel> masterActivity = FXCollections.observableArrayList();

    private void calculateAndDisplayStatistics() {
        int totalClients = masterActivity.size();
        totalClientsLabel.setText(String.valueOf(totalClients));

        long activeClients = masterActivity.stream()
                .filter(client -> "Active".equalsIgnoreCase(client.getStatus()))
                .count();
        activeClientsLabel.setText(String.valueOf(activeClients));

        YearMonth currentMonth = YearMonth.now();
        long newClientsThisMonth = masterActivity.stream()
                .filter(client -> {
                    try {
                        LocalDate joinDate = LocalDate.parse(client.getLastActivity());
                        return YearMonth.from(joinDate).equals(currentMonth);
                    } catch (DateTimeParseException e) {
                        return false;
                    }
                })
                .count();
        newClientsLabel.setText(String.valueOf(newClientsThisMonth));
    }
    private void setGreeting() {
        int currentHour = java.time.LocalTime.now().getHour();
        String greeting;

        if (currentHour >= 4 && currentHour < 11) {
            greeting = "Good Morning";
        } else if (currentHour >= 11 && currentHour < 15) {
            greeting = "Good Evening";
        } else if (currentHour >= 15 && currentHour < 18) {
            greeting = "Good Afternoon";
        } else {
            greeting = "Good Night";
        }

        String userName = "Arip Sudirman"; // Nantinya ini diambil dari data login
        greetingLabel.setText(greeting + ", " + userName);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        loadDashboardData();
        calculateAndDisplayStatistics();
        setGreeting();
    }

    private void setupTableColumns() {
        clientNameColumn.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        programColumn.setCellValueFactory(cellData -> cellData.getValue().programProperty());
        lastActivityColumn.setCellValueFactory(cellData -> cellData.getValue().lastActivityProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
    }

    private void loadDashboardData() {
        masterActivity.addAll(
                new JawLifeModel("Emily Carter", "Weight Loss", "2 days ago", "Active"),
                new JawLifeModel("David Lee", "Muscle Gain", "1 week ago", "Inactive"),
                new JawLifeModel("Olivia Brown", "Maintenance", "3 days ago", "Active"),
                new JawLifeModel("Ethan Clark", "Weight Loss", "5 days ago", "Active"),
                new JawLifeModel("Sophia Green", "Muscle Gain", "2 weeks ago", "Inactive")
        );

        activityTable.setItems(masterActivity);
    }

    @FXML
    public void handleClientsNavigation(ActionEvent event) {
        try {
            Parent clientRoot = FXMLLoader.load(getClass().getResource("/JawLife/ClintJl.fxml"));
            Scene scene = new Scene(clientRoot);
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
