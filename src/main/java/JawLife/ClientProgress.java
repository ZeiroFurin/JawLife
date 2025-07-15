package JawLife;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClientProgress implements Initializable {
    @FXML
    private Label clientNameLabel;
    @FXML
    private Label programLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private LineChart<String, Number> weightChart;
    @FXML
    private BarChart<String, Number> calorieChart;

    private ClientModel client;

    @FXML
    private void handleBackToClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JawLife/ClintJl.fxml")); // Sesuaikan nama file FXML Client
            Parent clientRoot = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(clientRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClientData(ClientModel client) {
        this.client = client;
        updateView();
    }

    private void updateView() {
        if (client != null) {
            clientNameLabel.setText(client.getName());
            programLabel.setText(client.getProgram());
            startDateLabel.setText(client.getStartDate());
            endDateLabel.setText(client.getEndDate());
        } else {
            clientNameLabel.setText("-");
            programLabel.setText("-");
            startDateLabel.setText("-");
            endDateLabel.setText("-");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}

