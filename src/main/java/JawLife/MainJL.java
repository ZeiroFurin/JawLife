package JawLife;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainJL extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/JawLife/1JLDash.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/JawLife/Style.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/JawLife/Clientisi.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/JawLife/ClinetProg.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/JawLife/Dashboard.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/JawLife/GlobCl.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/JawLife/WO.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("JawLife - Dashboard");
            primaryStage.setResizable(false); // Optional
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
