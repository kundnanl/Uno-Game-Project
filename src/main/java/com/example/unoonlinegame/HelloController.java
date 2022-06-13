package com.example.unoonlinegame;

import com.example.unoonlinegame.model.Card;
import com.example.unoonlinegame.model.Color;
import com.example.unoonlinegame.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button btn_AddPlayer;
    @FXML
    private Button btn_StartGame;
    @FXML
    private ListView lv_Players;
    @FXML
    private TextField txt_PlayerName;

    private List<Player> players = new ArrayList<>();

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

    @FXML
    public void changeText(){
        if(txt_PlayerName.getText() != ""){
            btn_AddPlayer.setDisable(false);
        }else{
            btn_AddPlayer.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

//sliderLabel.textProperty().bindBidirectional(number.valueProperty(), NumberFormat.getCompactNumberInstance());