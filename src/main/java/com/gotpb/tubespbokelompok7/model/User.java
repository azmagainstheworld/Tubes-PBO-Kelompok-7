package com.gotpb.tubespbokelompok7.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String fieldUsername;
    private String fieldEmail;
    private String fieldPassword;

    // Constructor
    public User(String fieldUsername, String fieldEmail, String fieldPassword) {
        this.fieldUsername = fieldUsername;
        this.fieldEmail = fieldEmail;
        this.fieldPassword = fieldPassword;
    }

    // Getters setters
    public String getFieldUsername() {
        return fieldUsername;
    }

    public void setFieldUsername(String fieldUsername) {
        this.fieldUsername = fieldUsername;
    }

    public String getFieldEmail() {
        return fieldEmail;
    }

    public void setFieldEmail(String fieldEmail) {
        this.fieldEmail = fieldEmail;
    }

    public String getFieldPassword() {
        return fieldPassword;
    }

    public void setFieldPassword(String fieldPassword) {
        this.fieldPassword = fieldPassword;
    }
}

