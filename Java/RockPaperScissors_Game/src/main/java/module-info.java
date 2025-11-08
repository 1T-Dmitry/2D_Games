module com.example.rockpaperscissors_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.rockpaperscissors_game to javafx.fxml;
    exports com.example.rockpaperscissors_game;
}