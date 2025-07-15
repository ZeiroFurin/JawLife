package CONTROLLERCHOSE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        userlist.initializeUserList(); // Panggil ini untuk memuat pengguna saat aplikasi dimulai
        Parent root = FXMLLoader.load(getClass().getResource("/CONTROLLERCHOSE/controllerChose.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}