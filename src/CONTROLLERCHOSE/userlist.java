package CONTROLLERCHOSE;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class userlist {
    private static HashMap<String, String> users = new HashMap<>();
    private static final String FILE_NAME = "users.dat"; // Nama file untuk menyimpan data

    // Constructor pribadi untuk mencegah instansiasi
    private userlist() {
        // loadUsersFromFile(); // Panggil ini dari initializeUserList()
    }

    // Metode untuk memuat pengguna dari file
    private static void loadUsersFromFile() {
        users.clear(); // Bersihkan HashMap sebelum memuat ulang
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
            System.out.println("Users loaded from file: " + users.size() + " users.");
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found. Starting with empty user list.");
            // File doesn't exist yet, which is fine for first run
        } catch (IOException e) {
            System.err.println("Error reading user data from file: " + e.getMessage());
        }
    }

    // Metode untuk menyimpan pengguna ke file
    private static void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Users saved to file: " + users.size() + " users.");
        } catch (IOException e) {
            System.err.println("Error writing user data to file: " + e.getMessage());
        }
    }

    public static boolean validate(String uname, String pwd) {
        return users.containsKey(uname) && users.get(uname).equals(pwd);
    }

    public static void addUser(String uname, String pwd) {
        // Tidak perlu cek isUsernameExists di sini lagi, sudah dicek di controller
        users.put(uname, pwd);
        saveUsersToFile(); // Simpan setelah menambahkan pengguna baru
    }

    // Metode baru untuk mengecek apakah username sudah ada
    public static boolean isUsernameExists(String uname) {
        return users.containsKey(uname);
    }

    // Panggil ini sekali saat aplikasi dimulai (misalnya di Main.java)
    public static void initializeUserList() {
        loadUsersFromFile();
    }
}