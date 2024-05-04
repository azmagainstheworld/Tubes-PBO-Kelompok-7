package com.gotpb.tubespbokelompok7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML private TextField fieldUsername;
    @FXML private TextField fieldEmail;
    @FXML private TextField fieldPassword;
    @FXML
    private void toRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void masukAkun(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public class Login {
        private LoginController connection;

        public Login(LoginController connection) {
            this.connection = login();
        }

        public boolean login(String usernameOrEmail, String password) {
            try {
                String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, usernameOrEmail);
                preparedStatement.setString(2, usernameOrEmail);
                preparedStatement.setString(3, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Login berhasil. Selamat datang, " + resultSet.getString("username") + "!");
                    return true;
                } else {
                    System.out.println("Login gagal. Periksa kembali username/email dan password Anda.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Login gagal: " + e.getMessage());
                return false;
            }
        }

        public void main(String[] args) {
            try {
                // Konfigurasi koneksi ke database MySQL
                String url = "jdbc:mysql://localhost:3306/nama_database";
                String username = "username_database";
                String password = "password_database";
                Connection connection = DriverManager.getConnection(url, username, password);

                Login login = new Login((LoginController) connection);

                // Contoh penggunaan
                login.login("user1", "password1"); // Akan berhasil
                login.login("user1@example.com", "password1"); // Akan berhasil
                login.login("user1", "password2"); // Akan gagal karena password salah
                login.login("user2", "password1"); // Akan gagal karena kombinasi username/email tidak terdaftar

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}