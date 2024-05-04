package com.gotpb.tubespbokelompok7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;



public class DashboardController {
    @FXML
    private TextField fieldTestimoni;
    @FXML
    private ScrollPane menuTestimoni;

    @FXML
    private void kirimTestimoni(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("tampilan dashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void profile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void toVideoPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan video.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void openMenuTestimoni() {
        menuTestimoni.setOpacity(1);
    }

    public class Testimoni {
        private DashboardController connection;

        public Testimoni(DashboardController connection) {
            this.connection = connection;
        }

        public boolean kirimTestimoni(String username, String isiTestimoni) {
            try {
                String query = "INSERT INTO testimonis (username, isi_testimoni) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, isiTestimoni);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Testimoni berhasil dikirim oleh " + username + ".");
                    return true;
                } else {
                    System.out.println("Gagal mengirim testimoni.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Gagal mengirim testimoni: " + e.getMessage());
                return false;
            }
        }

        public void tampilkanTestimoni() {
            try {
                String query = "SELECT * FROM testimonis";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String isiTestimoni = resultSet.getString("isi_testimoni");
                    System.out.println("Username: " + username + ", Testimoni: " + isiTestimoni);
                }
            } catch (SQLException e) {
                System.out.println("Gagal menampilkan testimoni: " + e.getMessage());
            }
        }

        public void main(String[] args) {
            try {
                // Konfigurasi koneksi ke database MySQL
                String url = "jdbc:mysql://localhost:3306/nama_database";
                String username = "username_database";
                String password = "password_database";
                Connection connection = DriverManager.getConnection(url, username, password);

                Testimoni testimoni = new Testimoni(connection);

                // Contoh penggunaan
                testimoni.kirimTestimoni("user1", "Testimoni pengguna 1");
                testimoni.kirimTestimoni("user2", "Testimoni pengguna 2");

                // Menampilkan testimoni yang berhasil dikirim
                testimoni.tampilkanTestimoni();

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}


