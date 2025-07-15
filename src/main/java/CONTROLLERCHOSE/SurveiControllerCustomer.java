package CONTROLLERCHOSE;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SurveiControllerCustomer {

    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField emailField;
    @FXML
    private ToggleGroup genderToggleGroup; // Untuk jenis kelamin
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;

    // Tambahan untuk fitur baru
    @FXML
    private TextArea medicalHistoryArea;
    @FXML
    private TextField filePathField; // Untuk menampilkan path file yang diunggah
    @FXML
    private TextArea goalsArea; // Untuk hal yang ingin dicapai
    @FXML
    private SplitMenuButton classSelectionButton; // Untuk pemilihan kelas

    // New field for username
    @FXML
    private TextField usernameField; // Add this line

    private File selectedFile; // Untuk menyimpan referensi file yang dipilih
    private String selectedClass = "Belum Dipilih"; // Untuk menyimpan kelas yang dipilih

    @FXML
    private void handleSubmit() {
        // Mengambil nilai dari setiap input
        String username = usernameField.getText(); // Get username
        String name = nameField.getText();
        String age = ageField.getText();
        String height = heightField.getText();
        String weight = weightField.getText();
        String email = emailField.getText();
        String medicalHistory = medicalHistoryArea.getText();
        String goals = goalsArea.getText(); // Mengambil input hal yang ingin dicapai

        // Mengambil nilai dari RadioButton untuk jenis kelamin
        String gender = null;
        if (genderToggleGroup.getSelectedToggle() != null) {
            gender = ((RadioButton) genderToggleGroup.getSelectedToggle()).getText();
        }

        // Mengambil path file yang diunggah
        String uploadedFilePath = (selectedFile != null) ? selectedFile.getAbsolutePath() : "No File Upload";

        // Lakukan sesuatu dengan data yang dikumpulkan (misalnya, tampilkan di konsol, simpan ke database, dll.)
        System.out.println("--- Customer Data ---");
        System.out.println("Username: " + username); // Print username
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + " cm");
        System.out.println("weight: " + weight + " kg");
        System.out.println("gender: " + gender);
        System.out.println("Email: " + email);
        System.out.println("Medical History/Allergies: " + medicalHistory);
        System.out.println("File upload: " + uploadedFilePath);
        System.out.println("What You Want to Achieve: " + goals);
        System.out.println("Selected Class: " + selectedClass); // Menggunakan variabel selectedClass

        // Contoh: Tampilkan alert sederhana
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Survei Selesai");
        alert.setHeaderText(null);
        alert.setContentText("Terima kasih, " + name + "! Survei Anda telah dikirim.\n" +
                "Kelas yang Anda pilih: " + selectedClass);
        alert.showAndWait();

        // Anda bisa juga membersihkan formulir setelah pengiriman
        // nameField.clear();
        // ageField.clear();
        // dll.
    }

    @FXML
    private void handleBrowseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih File Bukti Medis");
        // Filter ekstensi file yang diizinkan (opsional)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Mendapatkan Stage dari tombol yang memicu event
        Stage stage = (Stage) filePathField.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            filePathField.setText(selectedFile.getAbsolutePath());
        } else {
            filePathField.setText(""); // Bersihkan jika tidak ada file yang dipilih
        }
    }

    // Metode untuk MenuItem SplitMenuButton
    @FXML
    private void selectRegularClass() {
        selectedClass = "Beginner";
        classSelectionButton.setText("Class: Beginner"); // Update teks tombol utama
    }

    @FXML
    private void selectPremiumClass() {
        selectedClass = "Intermediate";
        classSelectionButton.setText("Class: Intermediate"); // Update teks tombol utama
    }

    @FXML
    private void selectVIPClass() {
        selectedClass = "Expert";
        classSelectionButton.setText("Class: Expert"); // Update teks tombol utama
    }
}