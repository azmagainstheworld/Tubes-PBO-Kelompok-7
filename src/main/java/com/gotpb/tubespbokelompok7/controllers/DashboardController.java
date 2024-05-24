package com.gotpb.tubespbokelompok7.controllers;

import com.gotpb.tubespbokelompok7.HelloApplication;
import com.gotpb.tubespbokelompok7.model.Testimoni;
import com.gotpb.tubespbokelompok7.model.Video;
import com.gotpb.tubespbokelompok7.views.components.TestimoniComponent;
import com.gotpb.tubespbokelompok7.views.components.VideoItemComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DashboardController {
    private Boolean isTestimoniOpen;
    private ArrayList<Testimoni> listTestimoni;
    private ArrayList<Video> videoList;

    @FXML
    private TextField fieldTestimoni;
    @FXML
    private ScrollPane menuTestimoni;
    @FXML
    private VBox listViewTestimoni;
    @FXML
    private VBox videoListContainer;

    public DashboardController() {
        this.isTestimoniOpen = false;
    }

    public void initialize() {
        System.out.println("OKE");
        this.buildVideoList();
    }

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
        isTestimoniOpen = !isTestimoniOpen;

        if (!isTestimoniOpen) {
            menuTestimoni.setOpacity(0);
            listViewTestimoni.getChildren().clear();
            return;
        }

        this.listTestimoni = Testimoni.getAll();

        for (Testimoni testimoni: this.listTestimoni) {
            TestimoniComponent component = new TestimoniComponent(testimoni);
            listViewTestimoni.getChildren().add(component);
        }

        menuTestimoni.setOpacity(1);
    }

    @FXML
    private void openMediaView() {
        return;
    }

    public void buildVideoList() {
        this.videoListContainer.setSpacing(12);
        this.videoList = Video.getAll();

        for (Video video: this.videoList) {
            VideoItemComponent item = new VideoItemComponent(video);
            this.videoListContainer.getChildren().add(item);
        }
    }
}


