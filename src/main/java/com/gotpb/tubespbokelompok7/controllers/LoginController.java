package com.gotpb.tubespbokelompok7.controllers;

import com.gotpb.tubespbokelompok7.Database;
import com.gotpb.tubespbokelompok7.DatabaseConnection;
import com.gotpb.tubespbokelompok7.HelloApplication;
import com.gotpb.tubespbokelompok7.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginController extends Controller {
    @FXML private TextField fieldUsername;
    @FXML private TextField fieldPassword;
    @FXML private Label LoginMessage;
    //@FXML private Button registerbutton;?
    @FXML private Button loginbutton;

    @FXML
    private void toRegister() throws IOException {
        this.router.open("register");
    }

    public void masukAkun() {
        Database db = Database.getInstance();
        Router router = Router.getInstance();

        if (!fieldUsername.getText().isBlank() && !fieldPassword.getText().isBlank()) {
            boolean result = db.login(fieldUsername.getText(), fieldPassword.getText());

            if (result) {
//                LoginMessage.setText("udh.");
                router.open("dashboard");
            } else {
//                LoginMessage.setText("Identitas tidak terdaftar.");
            }
        } else {
//            LoginMessage.setText("Tolong ae diisi dlu.");
        }
    }
}