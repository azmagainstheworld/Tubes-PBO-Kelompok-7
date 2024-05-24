package com.gotpb.tubespbokelompok7;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    public static DatabaseConnection INSTANCE;

    public Connection connection;

    public DatabaseConnection() {
        this.connection = this.getConnection();
    }

    public static DatabaseConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseConnection();
        }

        return INSTANCE;
    }

    public Connection getConnection() {
        String databaseName = "new";
        String databaseUser = "root";
        String databasePassword = "";
        String databaseUrl = "jdbc:mariadb://localhost/" + databaseName;

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return conn;
    }

}
