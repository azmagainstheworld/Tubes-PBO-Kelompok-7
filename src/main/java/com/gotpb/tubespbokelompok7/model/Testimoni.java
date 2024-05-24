package com.gotpb.tubespbokelompok7.model;

import com.gotpb.tubespbokelompok7.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Testimoni {
    public String id;
    public String idUser;
    public String text;

    public Testimoni(String id, String idUser, String text) {
        this.id = id;
        this.idUser = idUser;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ArrayList<Testimoni> getAll() {
        ArrayList<Testimoni> listTestimoni = new ArrayList<>();

        String query = "SELECT * FROM table_testimoni";
        DatabaseConnection db = DatabaseConnection.getInstance();

        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Testimoni testimoni = new Testimoni(
                        resultSet.getString("id"),
                        resultSet.getString("id_user"),
                        resultSet.getString("text")
                );

                listTestimoni.add(testimoni);
            }

            return listTestimoni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
