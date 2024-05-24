package com.gotpb.tubespbokelompok7.controllers;

import com.gotpb.tubespbokelompok7.Database;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController extends Controller {
    @FXML
    private TextField fieldUsername;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldPassword;

    @FXML
    public void kembali() {
        this.router.open("login");
    }

    @FXML
    public void onRegister() {
        Database db = Database.getInstance();

        String username = fieldUsername.getText();
        String email = fieldEmail.getText();
        String password = fieldPassword.getText();

        db.buatAkun(username, email, password);
    }
}





