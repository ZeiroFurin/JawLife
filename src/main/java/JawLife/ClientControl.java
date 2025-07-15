package JawLife;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientControl implements Initializable {
    @FXML
    private TableView<ClientModel> clientTable;
    @FXML
    private TableColumn<ClientModel, String> nameColumn;
    @FXML
    private TableColumn<ClientModel, String> classColumn;
    @FXML
    private Label clientNameTitle;
    @FXML
    private Label clientProgramSubtitle;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label goalLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private TextField searchName;
    private ObservableList<ClientModel> masterClient = FXCollections.observableArrayList();

    @FXML
    public void handleBackToDashboard(ActionEvent event) {
        try {
            Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/JawLife/1JLDash.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(dashboardRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void buttomProgress(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JawLife/dclientProgres.fxml"));
            Parent root = loader.load();

            ClientProgress controller = loader.getController();
            ClientModel selected = clientTable.getSelectionModel().getSelectedItem();
            controller.setClientData(selected);

            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupClientTableColumns();
        loadClientList();

        FilteredList<ClientModel> filteredData = new FilteredList<>(masterClient, p -> true);
        searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return client.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });
        clientTable.setItems(filteredData);
        clientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayClientDetails(newValue)
        );

        displayClientDetails(null);
    }

    private void setupClientTableColumns() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        classColumn.setCellValueFactory(cellData -> cellData.getValue().goalClassProperty());
    }
    private void loadClientList() {
        masterClient.addAll(
                (new ClientModel("Jaenab", "Women", "Weight Loss", "sophia.carter@email.com", "08458239452", "Jalan Julun", "Lose 20 lbs", "2023-01-15", "2023-07-15")),
                (new ClientModel("Sumanto eatpipol", "Man", "Muscle Gain", "ethan.b@email.com", "08238359383", "jalan Tulus", "Gain 10 lbs muscle", "2023-02-01", "2023-08-01")),
                (new ClientModel("Putri Sumanto", "Women", "Weight Loss", "olivia.h@email.com", "081583492335", "Jalan Pack", "Improve cardio", "2023-03-20", "2023-09-20"))
        );
    }

    private void displayClientDetails(ClientModel client) {
        System.out.println("DisplayClient called");

        if (client != null) {
            System.out.println("Claint call:" + client.getName());
            clientNameTitle.setText(client.getName());
            clientProgramSubtitle.setText(client.getGoal());

            emailLabel.setText(client.getName().toLowerCase().replace(" ", ".") + "@email.com");
            phoneLabel.setText(" " + client.getPhone());
            addressLabel.setText(" " + client.getAddress());
            goalLabel.setText(client.getGoal());
            startDateLabel.setText(" " + client.getStartDate());
            endDateLabel.setText(" " + client.getEndDate());

        } else {
            clientNameTitle.setText("No Client Selected");
            clientProgramSubtitle.setText("");
            emailLabel.setText("");
            phoneLabel.setText("");
            addressLabel.setText("");
            goalLabel.setText("");
            startDateLabel.setText("");
            endDateLabel.setText("");
        }
    }
}
