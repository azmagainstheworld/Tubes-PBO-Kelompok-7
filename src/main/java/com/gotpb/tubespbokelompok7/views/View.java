package com.gotpb.tubespbokelompok7.views;

import com.gotpb.tubespbokelompok7.HelloApplication;
import com.gotpb.tubespbokelompok7.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class View {
    public String name;
    public String fileName;
    protected FXMLLoader root;
    private Scene scene;

    public View(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public boolean open(Stage stage) {
        URL fileLocation = HelloApplication.class.getResource(this.fileName);
        this.root = new FXMLLoader(fileLocation);

        try {
            this.scene = new Scene(this.root.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Controller getController() {
        return this.root.getController();
    }
}
