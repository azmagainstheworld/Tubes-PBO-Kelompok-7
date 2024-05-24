package com.gotpb.tubespbokelompok7.model;

import com.gotpb.tubespbokelompok7.DatabaseConnection;
import com.gotpb.tubespbokelompok7.HelloApplication;

import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Video {
    public String id;
    public String namaFile;
    public String namaGambar;
    public String judul;
    public String deskripsi;

    public Video(String id, String namaFile, String namaGambar, String judul, String deskripsi) {
        this.id = id;
        this.namaFile = namaFile;
        this.namaGambar = namaGambar;
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public URL getFile() {
        return HelloApplication.class.getResource("videos/" + this.getNamaFile());
    }

    public InputStream getFileAsStream() {
        return HelloApplication.class.getResourceAsStream("videos/" + this.getNamaFile());
    }

    public String getNamaGambar() {
        return namaGambar;
    }

    public URL getThumbnailImage() {
        return HelloApplication.class.getResource("video_thumbnails/" + this.getNamaGambar());
    }

    public InputStream getThumbnailImageAsStream() {
        return HelloApplication.class.getResourceAsStream("video_thumbnails/" + this.getNamaGambar());
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public static ArrayList<Video> getAll() {
        ArrayList<Video> listVideo = new ArrayList<>();

        String query = "SELECT * FROM table_video";
        DatabaseConnection db = DatabaseConnection.getInstance();

        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Video video = new Video(
                        resultSet.getString("id"),
                        resultSet.getString("nama_file"),
                        resultSet.getString("nama_gambar"),
                        resultSet.getString("judul"),
                        resultSet.getString("deskripsi")
                );

                listVideo.add(video);
            }

            return listVideo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
