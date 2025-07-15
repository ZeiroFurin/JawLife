package CONTROLLERCHOSE;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegistrationTrainer implements Initializable {



    @FXML
    private TextField tfusername;

    @FXML
    private TextField tfpassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        tfusername.setOnAction(e -> tfpassword.requestFocus());
    }

    @FXML
    private void register(ActionEvent event) throws IOException {
        String uname = tfusername.getText();
        String pwd = tfpassword.getText();

        if (uname.isEmpty() || pwd.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan Registrasi", "Username dan Password harus diisi!");
            System.out.println("Semua data harus diisi!");
            return; // Hentikan eksekusi
        }

        // Validasi kedua: Pastikan username menggunakan simbol '@' (format email sederhana)
        if (!uname.contains("@")) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan Registrasi", "Username harus berupa alamat email yang valid (mengandung simbol '@').");
            System.out.println("Username harus berupa email!");
            return; // Hentikan eksekusi
        }

        System.out.println("before addUser: " + userlist.validate(uname, pwd));

        // Pengecekan apakah username (email) sudah terdaftar
        if (userlist.validate(uname, pwd) || userlist.isUsernameExists(uname)) { // Perlu metode isUsernameExists di userlist
            showAlert(Alert.AlertType.WARNING, "Registrasi Gagal", "Username (Email) ini sudah terdaftar. Mohon gunakan email lain.");
            System.out.println("Username (Email) sudah terdaftar: " + uname);
            return;
        }


        userlist.addUser(uname, pwd);

        System.out.println("after addUser: " + userlist.validate(uname, pwd));
        System.out.println("User registered: " + uname);

        // Tampilkan pesan sukses
        showAlert(Alert.AlertType.INFORMATION, "Registrasi Berhasil", "Akun Anda berhasil didaftarkan! Silakan login.");

        Parent loginPage = FXMLLoader.load(getClass().getResource("LoginTrainer.fxml"));
        Scene scene = new Scene(loginPage);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page Costumer"); // Ubah judul
        stage.show();
      }
    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("LoginTrainer.fxml"));
        Scene scene = new Scene(loginPage);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }
private void showAlert(Alert.AlertType type, String title, String message) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null); // Tidak ada header khusus
    alert.setContentText(message);
    alert.showAndWait();
  }
}
