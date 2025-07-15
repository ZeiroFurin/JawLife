package CONTROLLERCHOSE;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLoginTrainer implements Initializable {
    userlist data;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        username.setOnAction(e -> password.requestFocus());
    }

    @FXML
    private void signin(ActionEvent event) throws IOException {
        String name = username.getText();
        String katasandi = password.getText();

        System.out.println("check login " + name + ": " + userlist.validate(name, katasandi));

        if (userlist.validate(name, katasandi)) {
            Parent scene2 = FXMLLoader.load(getClass().getResource("SurveiTrainer.fxml"));
            Scene Scene = new Scene(scene2);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(Scene);
            stage.setTitle("new window");
            stage.show();

            System.out.println("login succesfully");
        } else {
            System.out.println("username or password wrong");
        }
        username.clear();
        password.clear();
    }

    @FXML
    private void button2(ActionEvent event) throws IOException {
        Parent scene2 = FXMLLoader.load(getClass().getResource("RegistrasiTrainer.fxml"));
        Scene scene = new Scene(scene2);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("new window");
        stage.show();
        System.out.println("welcome to register menu");
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("controllerChose.fxml"));
        Scene scene = new Scene(loginPage);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }
}







