package com.gotpb.tubespbokelompok7.model;

import java.util.ArrayList;
import java.util.List;

public class Registrasi {
    private List<User> users;

    public Registrasi() {
        this.users = new ArrayList<>();
    }

    // Metode untuk register user baru
    public void registerUser(String fieldUsername, String fieldEmail, String fieldPassword) {
        // Memeriksa apakah user telah terdaftar atau belum
        for (User user : users) {
            if (user.getFieldUsername().equals(fieldUsername)) {
                System.out.println("Username atau email sudah ada. Berikan email yang berbeda.");
                return;
            }
        }

        User newUser = new User(fieldUsername, fieldEmail, fieldPassword);
        users.add(newUser);
        System.out.println("Pengguna berhasil terdaftar");
    }

    public void displayRegisteredUser() {
        System.out.println("Pengguna terdaftar:");
        for (User user : users) {
            System.out.println("Username: " + user.getFieldUsername() + ", Email: " + user.getFieldEmail());
        }
    }

    public static void main(String[] args) {
        Registrasi registrasi = new Registrasi();
        registrasi.registerUser("user1", "user1@example.com", "password1");
        registrasi.registerUser("user2", "user2@example.com", "password2");
        registrasi.registerUser("user3", "user3@example.com", "password3");


    }
}
