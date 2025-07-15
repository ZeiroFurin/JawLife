package CONTROLLERCHOSE;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChose implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    @FXML
    private void CustomerBTN(ActionEvent event) throws IOException {
        Parent scene2 = FXMLLoader.load(getClass().getResource("LoginCustomer.fxml"));
        Scene Scene = new Scene(scene2);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);
        stage.setTitle("new window");
        stage.show();
        System.out.println("stage2 is clicked");
    }

    @FXML
    private void TrainerBTN(ActionEvent event) throws IOException {
        Parent scene2 = FXMLLoader.load(getClass().getResource("loginTrainer.fxml"));
        Scene Scene = new Scene(scene2);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);
        stage.setTitle("new window");
        stage.show();
        System.out.println("stage2 is clicked");
    }
}
