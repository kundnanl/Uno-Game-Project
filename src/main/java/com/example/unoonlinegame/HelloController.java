package com.example.unoonlinegame;

import com.example.unoonlinegame.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The `HelloController` class is the controller for the initial screen of the Uno game.
 * It handles adding players and starting the game.
 *
 * @author Principal Authors:
 * - Laksh Kundnani
 * - Devendra Bisht
 * - Vuong Quoc Nguyen
 * - Neha
 */
public class HelloController implements Initializable {
    @FXML
    private Button btn_AddPlayer;
    @FXML
    private Button btn_StartGame;
    @FXML
    private ListView lv_Players;
    @FXML
    private TextField txt_PlayerName;

    private final List<Player> players = new ArrayList<>();

    /**
     * Event handler for the "Add Player" button. Adds a player to the list of players.
     *
     * @param event The action event triggered by clicking the button.
     */
    @FXML
    public void addPlayer(ActionEvent event){
        lv_Players.getItems().add(txt_PlayerName.getText());
        players.add(new Player(txt_PlayerName.getText()));
        txt_PlayerName.setText("");
        btn_AddPlayer.setDisable(true);
        if(players.size() > 1){
            btn_StartGame.setDisable(false);
        }
    }

    /**
     * Event handler for the "Start Game" button. Loads the game screen and passes player data.
     *
     * @param event The action event triggered by clicking the button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void startGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();

        //get new Controller
        GameController gameController = loader.getController();
        gameController.setPlayers(players);


        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, btn_StartGame.getScene().getWindow().getWidth(), btn_StartGame.getScene().getWindow().getHeight());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Event handler for updating the "Add Player" button state based on input.
     */
    @FXML
    public void changeText(){
        if(!"".equals(txt_PlayerName.getText())){
            btn_AddPlayer.setDisable(false);
        }else{
            btn_AddPlayer.setDisable(true);
        }
    }

    /**
     * Initialize the controller.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
