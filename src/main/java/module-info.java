module com.example.batallabarcos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.batallabarcos to javafx.fxml;
    exports com.example.batallabarcos;
}