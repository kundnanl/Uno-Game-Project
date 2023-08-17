package com.example.unoonlinegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class that launches the Uno Game application.
 * This class sets up the JavaFX application and initializes the GUI.
 * It loads the FXML file "hello-view.fxml" and creates the primary stage for the game.
 * The minimum width and height of the stage are also set.
 * The main method is used to launch the application.
 *
 * @author Principal Authors:
 * - Laksh Kundnani
 * - Devendra Bisht
 * - Vuong Quoc Nguyen
 * - Neha
 */
public class HelloApplication extends Application {
    /**
     * Initializes the JavaFX stage and scene, loading the FXML layout.
     * This method creates the primary stage for the Uno Game and sets up the GUI.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML layout.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 400);
        stage.setTitle("Uno Game!");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setScene(scene);

        stage.show();
    }

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}