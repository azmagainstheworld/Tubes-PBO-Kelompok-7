package com.gotpb.tubespbokelompok7;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    public static Database INSTANCE;
    public DatabaseConnection db;

    public Database() {
        this.db = DatabaseConnection.getInstance();
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }

        return INSTANCE;
    }

    public boolean isAkunTerdaftar(String username) {
        String query = "SELECT COUNT(*) FROM table_user WHERE username = ?";

        PreparedStatement statement = null;

        try {
            statement = this.db.connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Jika count > 0, berarti akun sudah terdaftar
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void buatAkun(String username, String email, String password) {
        if (this.isAkunTerdaftar(username)) {
            System.out.println("Akun dengan username atau email yang sama sudah terdaftar.");
            return;
        }

        String query = "INSERT INTO table_user (username, email, password) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = this.db.connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            System.out.println("Akun berhasil didaftarkan.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String username, String password) {
        if (!this.isAkunTerdaftar(username)) {
            return false;
        }

        String query = "SELECT COUNT(*) FROM table_user WHERE username = ? AND password = ?";

        PreparedStatement statement = null;
        try {
            statement = this.db.connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Jika count > 0, berarti akun sudah terdaftar
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
