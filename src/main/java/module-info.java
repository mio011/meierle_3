module com.example.meierle_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.meierle_3 to javafx.fxml;
    exports com.example.meierle_3;
}