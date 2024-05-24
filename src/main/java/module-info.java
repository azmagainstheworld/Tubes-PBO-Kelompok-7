module com.gotpb.tubespbokelompok7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires java.desktop;

    opens com.gotpb.tubespbokelompok7 to javafx.fxml;
    exports com.gotpb.tubespbokelompok7;
    exports com.gotpb.tubespbokelompok7.controllers;
    exports com.gotpb.tubespbokelompok7.router;
    exports com.gotpb.tubespbokelompok7.views;
    opens com.gotpb.tubespbokelompok7.controllers to javafx.fxml;
}