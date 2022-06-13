package com.example.unoonlinegame;

import com.example.unoonlinegame.model.Card;
import com.example.unoonlinegame.model.Game;
import com.example.unoonlinegame.model.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private HBox yourCards;
    @FXML
    private ImageView imageView_pile;
    @FXML
    private ImageView imageView_cardOnTable;
    @FXML
    private Text lbl_yourCards;
    @FXML
    private Text lbl_displayName;
    @FXML
    private Button btn_skip;
    @FXML
    public Text lbl_info;

    private Game game;
    private List<Player> players;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        game = new Game(this, players);

        //Pile Image
        imageView_pile.setImage(new Image("file:src//main//resources/com/example/unoonlinegame/images/BackSide.png"));
        imageView_pile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            //draw Card
            @Override
            public void handle(MouseEvent event) {
                game.buttonPressed();
                event.consume();
            }
        });
        //start game
        game.start();
    }

    //display Card
    public void displayCard(Card card){
        imageView_cardOnTable.setImage(new Image("file:src//main//resources/com/example/unoonlinegame/images/" + card.getUrl()));
    }

    //display user Cards
    public void displayCards(List<Card> cards){
        lbl_yourCards.setText(String.format("Deine Karten (%d)", cards.size()));
        yourCards.getChildren().clear();
        for (int i = 0; i < cards.size(); i++) {
            ImageView imageView = new ImageView();
            int finalI = i;
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                //draw Card
                @Override
                public void handle(MouseEvent event) {
                    game.buttonPressed(finalI);
                    event.consume();
                }
            });
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            imageView.setImage(new Image("file:src//main//resources/com/example/unoonlinegame/images/" + cards.get(i).getUrl()));

            yourCards.getChildren().add(imageView);
        }
    }

    public void setLbl_displayName(String text){
        lbl_displayName.setText("Player " + text);
    }

    public void setButtonDisabled(boolean disabled){
        btn_skip.setDisable(disabled);
    }

    @FXML
    public void btn_skip_press(ActionEvent event){
        lbl_info.setText("Karte gezogen");
        game.nextPlayer();
        btn_skip.setDisable(true);
        game.setActive(true);
        game.setCardActive(true);
    }
}
