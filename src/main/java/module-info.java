module com.example.unoonlinegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.unoonlinegame to javafx.fxml;
    exports com.example.unoonlinegame;
    exports com.example.unoonlinegame.model;
    opens com.example.unoonlinegame.model to javafx.fxml;
}