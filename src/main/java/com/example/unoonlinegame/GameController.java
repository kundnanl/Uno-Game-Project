package com.example.unoonlinegame;

import com.example.unoonlinegame.model.Card;
import com.example.unoonlinegame.model.Game;
import com.example.unoonlinegame.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The `GameController` class manages the graphical user interface and
 * interactions of the Uno game.
 * It displays cards, handles button presses, and updates game information.
 *
 * @author Principal Authors:
 *         - Laksh Kundnani
 *         - Devendra Bisht
 *         - Vuong Quoc Nguyen
 *         - Neha
 */
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

    /**
     * Initializes the game controller with the specified URL and resource bundle.
     * This method is automatically called when the FXML file is loaded.
     *
     * @param url            The location used to resolve relative paths.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize any components or setup here
    }

    /**
     * Sets the players for the game and initializes game-related components.
     *
     * @param players The list of players participating in the game.
     */
    public void setPlayers(List<Player> players) {
        game = new Game(this, players);

        // Pile Image
        imageView_pile.setImage(new Image("file:src/main/resources/com/example/unoonlinegame/images/BackSide.png"));
        imageView_pile.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            game.buttonPressed();
            event.consume();
        });

        // Start game
        game.start();
    }

    /**
     * Displays the given card's image on the table.
     *
     * @param card The card to display.
     */
    public void displayCard(Card card) {
        imageView_cardOnTable
                .setImage(new Image("file:src/main/resources/com/example/unoonlinegame/images/" + card.getUrl()));
    }

    /**
     * Displays the list of cards in the player's hand.
     *
     * @param cards The list of cards to display.
     */
    public void displayCards(List<Card> cards) {
        lbl_yourCards.setText(String.format("Your Cards (%d)", cards.size()));
        yourCards.getChildren().clear();
        for (int i = 0; i < cards.size(); i++) {
            ImageView imageView = new ImageView();
            int finalI = i;
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                game.buttonPressed(finalI);
                event.consume();
            });
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            imageView.setImage(
                    new Image("file:src/main/resources/com/example/unoonlinegame/images/" + cards.get(i).getUrl()));

            yourCards.getChildren().add(imageView);
        }
    }

    /**
     * Sets the display name of the current player.
     *
     * @param text The text to set as the display name.
     */
    public void setLbl_displayName(String text) {
        lbl_displayName.setText("Player " + text);
    }

    /**
     * Sets the disabled state of the skip button.
     *
     * @param disabled `true` to disable the button, `false` to enable it.
     */
    public void setButtonDisabled(boolean disabled) {
        btn_skip.setDisable(disabled);
    }

    /**
     * Handles the "Skip" button press event.
     *
     * @param event The action event triggered by the button press.
     */
    @FXML
    public void btn_skip_press(ActionEvent event) {
        lbl_info.setText("Card drawn");
        game.nextPlayer();
        btn_skip.setDisable(true);
        game.setActive(true);
        game.setCardActive(true);
    }
}
