package CONTROLLERCHOSE;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SurveiControllerTrainer {

    @FXML
    private TextField namaField;
    @FXML
    private TextField umurField;
    @FXML
    private TextField pengalamanField;
    @FXML
    private Label buktiUploadStatus;
    @FXML
    private TextArea spesialisasiArea;

    // Mengganti CheckBox dengan SplitMenuButton
    @FXML
    private SplitMenuButton kelasDiambilButton;

    // New fields for gender
    @FXML
    private ToggleGroup genderToggleGroup;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;

    private File uploadedFile; // Untuk menyimpan referensi file yang diunggah
    private String selectedClassLevel = "Belum Dipilih"; // Untuk menyimpan kelas yang dipilih dari SplitMenuButton

    @FXML
    private void handleUnggahBukti() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Bukti Pengalaman");
        // Filter untuk jenis file yang diterima
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Document Files", "*.doc", "*.docx", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        Stage stage = (Stage) buktiUploadStatus.getScene().getWindow();
        uploadedFile = fileChooser.showOpenDialog(stage);

        if (uploadedFile != null) {
            buktiUploadStatus.setText("File diunggah: " + uploadedFile.getName());
            // Anda bisa menyimpan uploadedFile.getAbsolutePath() jika diperlukan
        } else {
            buktiUploadStatus.setText("Belum ada file diunggah");
        }
    }

    // Metode untuk MenuItem SplitMenuButton
    @FXML
    private void selectBeginnerClass() {
        selectedClassLevel = "Beginner";
        kelasDiambilButton.setText("Class: Beginner"); // Update teks tombol utama
    }

    @FXML
    private void selectIntermediateClass() {
        selectedClassLevel = "Intermediate";
        kelasDiambilButton.setText("class: Intermediate"); // Update teks tombol utama
    }

    @FXML
    private void selectExpertClass() {
        selectedClassLevel = "Expert";
        kelasDiambilButton.setText("Class: Expert"); // Update teks tombol utama
    }

    @FXML
    private void handleSubmitSurvei() {
        String nama = namaField.getText();
        String umur = umurField.getText();

        // Get selected gender
        String gender = null;
        if (genderToggleGroup.getSelectedToggle() != null) {
            gender = ((RadioButton) genderToggleGroup.getSelectedToggle()).getText();
        }

        String pengalaman = pengalamanField.getText();
        String spesialisasi = spesialisasiArea.getText();
        String buktiFile = (uploadedFile != null) ? uploadedFile.getAbsolutePath() : "No file yet";

        // Mendapatkan kelas yang dipilih dari SplitMenuButton
        // Variabel selectedClassLevel sudah diupdate oleh metode select...Class()
        String kelasYangDiambil = selectedClassLevel;

        // Lakukan sesuatu dengan data (misalnya, cetak ke konsol atau simpan ke database)
        System.out.println("--- Trainer Biodata ---");
        System.out.println("Name: " + nama);
        System.out.println("Age: " + umur);
        System.out.println("Gender: " + gender); // Print gender
        System.out.println("Experience: " + pengalaman + " Year ");
        System.out.println("File: " + buktiFile);
        System.out.println("Spesialist: " + spesialisasi);
        System.out.println("Class chosen: " + kelasYangDiambil);

        // Tampilkan konfirmasi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Survei Selesai");
        alert.setHeaderText(null);
        alert.setContentText("Thank you, " + nama + "! Your survey has been sent.\n" +
                "You choose the class: " + kelasYangDiambil);
        alert.showAndWait();

        // Opsional: Bersihkan formulir setelah pengiriman
        // namaField.clear();
        // umurField.clear();
        // if (genderToggleGroup.getSelectedToggle() != null) {
        //     genderToggleGroup.getSelectedToggle().setSelected(false);
        // }
        // pengalamanField.clear();
        // spesialisasiArea.clear();
        // buktiUploadStatus.setText("Belum ada file diunggah");
        // uploadedFile = null;
        // kelasDiambilButton.setText("Pilih Kelas");
        // selectedClassLevel = "Belum Dipilih";
    }
}