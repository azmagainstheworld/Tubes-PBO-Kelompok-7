package com.gotpb.tubespbokelompok7;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class RegisterController {
    @FXML
    private TextField fieldUsername;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldPassword;
    @FXML
    private void buatAkun() {
    }

    public class Registrasi {
        private Connection connection;

        public Registrasi(Connection connection) {
            this.connection = connection;
        }

        public boolean buatAkun(String username, String email, String password) {
            try {
                // Periksa apakah username atau email sudah terdaftar
                if (isUserExists(username) || isEmailExists(email)) {
                    System.out.println("Username atau email sudah terdaftar.");
                    return false;
                }

                // Jika tidak terdaftar, lakukan proses pendaftaran
                String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();
                System.out.println("Registrasi berhasil.");
                return true;
            } catch (SQLException e) {
                System.out.println("Registrasi gagal: " + e.getMessage());
                return false;
            }
        }

        private boolean isUserExists(String username) throws SQLException {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }

        private boolean isEmailExists(String email) throws SQLException {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }

        public void main(String[] args) {
            try {
                // Konfigurasi koneksi ke database MySQL
                String url = "jdbc:mysql://localhost:3306/nama_database";
                String username = "username_database";
                String password = "password_database";
                Connection connection = DriverManager.getConnection(url, username, password);

                Registrasi registrasi = new Registrasi(connection);

                // Contoh penggunaan
                registrasi.buatAkun("user1", "user1@example.com", "password1");
                registrasi.buatAkun("user2", "user2@example.com", "password2");
                registrasi.buatAkun("user1", "user2@example.com", "password3"); // Akan gagal karena username sudah terdaftar
                registrasi.buatAkun("user3", "user1@example.com", "password4"); // Akan gagal karena email sudah terdaftar

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

