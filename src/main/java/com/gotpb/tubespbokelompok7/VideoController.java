package com.gotpb.tubespbokelompok7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.MediaView;
import javafx.util.Duration;



public class VideoController {
    @FXML private TextField fieldKomentar;
    @FXML private MediaView mediaView;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private void initialize(){
        file = new File("src/main/java/com/gotpb/tubespbokelompok7/video 1.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    public void play() {
        mediaPlayer.play();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void reset() {
        mediaPlayer.seek(Duration.seconds(0.0));
    }

    @FXML
    private void kirimKomentar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan video.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void toRating(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan video.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void takeQuiz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan video.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    class Komentar {
        private String username;
        private String komentar;

        public Komentar(String username, String comment) {
            this.username = username;
            this.komentar = comment;
        }

        public String getUsername() {
            return username;
        }

        public String getComment() {
            return komentar;
        }
    }

    class Post {
        private String title;
        private String content;
        private List<Komentar> comments;

        public Post(String title, String content) {
            this.title = title;
            this.content = content;
            this.comments = new ArrayList<>();
        }

        public void addComment(String username, String comment) {
            Komentar newKomentar = new Komentar(username, comment);
            Komentar.add(newKomentar);
        }

        public void displayPostWithComments() {
            System.out.println("Title: " + title);
            System.out.println("Content: " + content);
            System.out.println("Comments:");
            for (Komentar comment : Komentar) {
                System.out.println("Username: " + comment.getUsername());
                System.out.println("Comment: " + comment.getComment());
                System.out.println("---------");
            }
        }

        // Method to save post and comments to the database
        public void saveToDatabase() {
            try {
                // Establishing database connection
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

                // Inserting post into database
                String insertPostQuery = "INSERT INTO posts (title, content) VALUES (?, ?)";
                PreparedStatement postStatement = conn.prepareStatement(insertPostQuery);
                postStatement.setString(1, title);
                postStatement.setString(2, content);
                postStatement.executeUpdate();

                // Getting the post ID
                int postId = 0;
                String getPostIdQuery = "SELECT LAST_INSERT_ID()";
                PreparedStatement getPostIdStatement = conn.prepareStatement(getPostIdQuery);
                getPostIdStatement.execute();
                // Retrieving the generated post ID
                try (var resultSet = getPostIdStatement.getResultSet()) {
                    if (resultSet.next()) {
                        postId = resultSet.getInt(1);
                    }
                }

                // Inserting comments into database
                String insertCommentQuery = "INSERT INTO comments (post_id, username, comment) VALUES (?, ?, ?)";
                PreparedStatement commentStatement = conn.prepareStatement(insertCommentQuery);
                for (Comment comment : comments) {
                    commentStatement.setInt(1, postId);
                    commentStatement.setString(2, comment.getUsername());
                    commentStatement.setString(3, comment.getComment());
                    commentStatement.executeUpdate();
                }

                // Closing resources
                commentStatement.close();
                postStatement.close();
                conn.close();

                System.out.println("Post and comments saved to the database successfully!");
            } catch (SQLException e) {
                System.out.println("Error occurred while saving to the database: " + e.getMessage());
            }
        }
    }

    public class Komentar {
        public static void main(String[] args) {
            // Creating a new post
            Post myPost = new Post("Sample Title", "Sample Content");

            // Adding comments to the post
            myPost.addComment("User1", "Sample Comment 1");
            myPost.addComment("User2", "Sample Comment 2");

            // Displaying the post with comments
            myPost.displayPostWithComments();

            // Saving post and comments to the database
            myPost.saveToDatabase();
        }
    }

    public class toRating {
        private Connection connection;

        public toRating(Connection connection) {
            this.connection = connection;
        }

        public boolean beriRating(String username, int videoId) {
            try {
                // Periksa apakah pengguna sudah memberikan like sebelumnya
                if (cekLike(username, videoId)) {
                    System.out.println("Anda sudah memberikan like pada video ini.");
                    return false;
                }

                // Tambahkan like ke dalam database
                String query = "INSERT INTO likes (username, video_id) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, videoId);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Anda memberikan like pada video dengan ID " + videoId + ".");
                    return true;
                } else {
                    System.out.println("Gagal memberikan like pada video.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Gagal memberikan like pada video: " + e.getMessage());
                return false;
            }
        }

        public boolean batalkanRating(String username, int videoId) {
            try {
                // Hapus like dari database
                String query = "DELETE FROM likes WHERE username = ? AND video_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, videoId);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Anda telah membatalkan like pada video dengan ID " + videoId + ".");
                    return true;
                } else {
                    System.out.println("Gagal membatalkan like pada video.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Gagal membatalkan like pada video: " + e.getMessage());
                return false;
            }
        }

        private boolean cekRating(String username, int videoId) throws SQLException {
            String query = "SELECT * FROM likes WHERE username = ? AND video_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, videoId);
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

                toRating toRating = new toRating(connection);

                // Contoh penggunaan
                toRating.beriRating("user1", 1); // Memberikan like pada video dengan ID 1
                toRating.beriRating("user1", 1); // Gagal karena pengguna sudah memberikan like sebelumnya
                toRating.batalkanRating("user1", 1); // Membatalkan like pada video dengan ID 1

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
