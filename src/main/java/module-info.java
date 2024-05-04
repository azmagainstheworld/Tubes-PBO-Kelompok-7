module com.gotpb.tubespbokelompok7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;

    opens com.gotpb.tubespbokelompok7 to javafx.fxml;
    exports com.gotpb.tubespbokelompok7;
}