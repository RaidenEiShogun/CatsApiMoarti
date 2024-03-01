module com.example.apifx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens com.example.apifx to javafx.fxml;
    exports com.example.apifx;
}