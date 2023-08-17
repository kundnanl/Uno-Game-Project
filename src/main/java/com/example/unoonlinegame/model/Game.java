package com.example.unoonlinegame.model;

import com.example.unoonlinegame.GameController;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.*;

/**
 * The `Game` class represents the Uno game logic and control.
 * This class manages the game state, player actions, and card interactions.
 * It handles turns, card plays, and card drawing for players.
 * The class utilizes the `GameController` for user interface interaction.
 *
 * @author Principal Authors:
 *         - Laksh Kundnani
 *         - Devendra Bisht
 *         - Vuong Quoc Nguyen
 *         - Neha
 */
public class Game {
    private GameController controller;
    private List<Player> players = new ArrayList<>();
    private Table table;
    private ArrayList<Card> pile = new ArrayList<>();
    private final int numberOfCardsPerPlayer = 6;
    private int currentPlayer = 0;
    private boolean active = true;
    private boolean cardActive = true;
    // private boolean gameDirection;

    public Game(GameController controller, List<Player> players) {
        // this.gameDirection = false;
        this.players = players;
        this.controller = controller;
        table = new Table(controller);
    }

    /**
     * Starts the Uno game by creating and distributing cards to players.
     * This method initializes the game state and prepares for gameplay.
     */
    public void start() {
        createCards();
        giveCardsToPlayer();
        pile = table.layCard(pile);

        controller.displayCards(players.get(currentPlayer).getCards());
        controller.setLbl_displayName(players.get(currentPlayer).getName());
    }

    /**
     * Moves to the next player in the game.
     * This method advances the turn to the next player's move.
     */
    public void nextPlayer() {
        if (currentPlayer == players.size() - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
        controller.setLbl_displayName(players.get(currentPlayer).getName());
        controller.lbl_info.setText("Next player's turn");
        controller.displayCards(players.get(currentPlayer).getCards());
    }

    /**
     * Moves to the previous player in the game.
     * This method moves the turn to the previous player's move.
     */
    public void previousPlayer() {
        currentPlayer--;
        if (currentPlayer == -1) {
            currentPlayer = players.size() - 1;
        } else if (currentPlayer == players.size() - 1) {
            currentPlayer = 0;
        }
        System.out.println(currentPlayer);
        controller.setLbl_displayName(players.get(currentPlayer).getName());
        controller.displayCards(players.get(currentPlayer).getCards());
    }


    /**
     * Distributes cards to each player at the start of the game.
     * This method gives a fixed number of cards to each player from the deck.
     */    private void giveCardsToPlayer() {
        for (Player player : players) {
            for (int i = 0; i < numberOfCardsPerPlayer; i++) {
                int randomIndex = new Random().nextInt(pile.size());
                player.giveCard(pile.get(randomIndex));
                pile.remove(randomIndex);
            }
        }
    }

    /**
     * Creates a shuffled deck of Uno cards.
     * This method generates the initial set of Uno cards, including regular
     * number cards, Draw Two cards, Draw Four cards, Skip cards, and Reverse cards.
     */
    private void createCards() {
        List<Card> newCards = new ArrayList<>();
        // Add regular number cards for each color
        for (Color color : Color.values()) {
            if (color != Color.black) {
                for (int i = 1; i <= 9; i++) {
                    newCards.add(new Card(i, color, CardType.NUMBER));
                }
            }
        }
        // Add Draw Two cards for each color
        for (Color color : Color.values()) {
            if (color != Color.black) {
                newCards.add(new Card(10, color, CardType.DRAW_TWO));
            }
        }
        // Add Draw Four cards (black)
        for (int i = 0; i < 4; i++) {
            newCards.add(new Card(11, Color.black, CardType.DRAW_FOUR));
        }

        for (Color color : Color.values()) {
            if (color != Color.black) {
                // skip to the next player
                if (color != Color.black) {
                    newCards.add(new Card(12, color, CardType.SKIP));
                }
            }
        }

        for (Color color : Color.values()) {
            if (color != Color.black) {
                // reverse to the next player
                if (color != Color.black) {
                    newCards.add(new Card(13, color, CardType.REVERSE));
                }
            }
        }

        newCards.add(new Card(14, Color.black, CardType.WILD_CARD));
        System.out.println(newCards);
        // Shuffle Cards
        Collections.shuffle(newCards);
        pile.addAll(newCards);
    }

    /**
     * Handles the button press event when a card is played.
     * This method manages card play logic, such as drawing cards, skipping players,
     * reversing the order of play, and regular card plays.
     *
     * @param index The index of the selected card in the current player's hand.
     */
    public void buttonPressed(int index) {
        Card selectedCard = players.get(currentPlayer).getCard(index);
        Card topCard = table.getCardOnTable();

        if (selectedCard.getNumber() == 10) { // Draw Two card
            for (int i = 0; i < 2; i++) {
                int randomIndex = new Random().nextInt(pile.size());
                players.get((currentPlayer == players.size() - 1) ? 0 : currentPlayer + 1)
                        .giveCard(pile.get(randomIndex));
                pile.remove(randomIndex);
            }

            table.layCard(players.get(currentPlayer).getAndDeleteCard(index));

            setActiveAndDisplayCards(false);

        } else if (selectedCard.getNumber() == 11) { // Draw Four card
            for (int i = 0; i < 4; i++) {
                int randomIndex = new Random().nextInt(pile.size());
                players.get((currentPlayer == players.size() - 1) ? 0 : currentPlayer + 1)
                        .giveCard(pile.get(randomIndex));
                pile.remove(randomIndex);
            }
            table.layCard(players.get(currentPlayer).getAndDeleteCard(index));

            setActiveAndDisplayCards(false);

        } else if (selectedCard.getNumber() == 12) { // skip Card play
            pile.add(table.getCardOnTable());
            table.layCard(players.get(currentPlayer).getAndDeleteCard(index));
            setActiveAndDisplayCards(true);
            nextPlayer();
            nextPlayer();
            controller.setButtonDisabled(true);

        } else if (selectedCard.getNumber() == 13) { // reverse play

            pile.add(table.getCardOnTable());
            table.layCard(players.get(currentPlayer).getAndDeleteCard(index));
            setActiveAndDisplayCards(true);
            previousPlayer();

        } else if (selectedCard.getNumber() == 14) {
            // Regular card play logic for wild cards
            pile.add(table.getCardOnTable());
            table.layCard(players.get(currentPlayer).getAndDeleteCard(index));

            setActiveAndDisplayCards(false);
        }

        else if (selectedCard.getColor() == topCard.getColor() || selectedCard.getNumber() == topCard.getNumber()
                || topCard.getColor() == Color.black) {
            // Regular card play logic
            pile.add(table.getCardOnTable());
            table.layCard(players.get(currentPlayer).getAndDeleteCard(index));

            setActiveAndDisplayCards(false);
        } else {
            // controller.lbl_info.setText("You cannot play this card");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You cannot play this card");
            alert.showAndWait();
        }
        if (players.get(currentPlayer).getCards().size() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("CONGRATS");
            alert.setContentText("You won the game");
            alert.showAndWait();
            System.exit(0);
        }
    }

    private void setActiveAndDisplayCards(boolean active) {
        this.active = active;
        cardActive = false;
        controller.setButtonDisabled(false);
        controller.displayCards(players.get(currentPlayer).getCards());
    }

    /**
     * Handles the button press event when a player chooses to draw a card.
     * This method allows players to draw cards from the game pile.
     */
    public void buttonPressed() {
        if (active) {
            if (pile.size() > 0) {
                int randomIndex = new Random().nextInt(pile.size());
                players.get(currentPlayer).giveCard(pile.get(randomIndex));
                pile.remove(randomIndex);
                controller.displayCards(players.get(currentPlayer).getCards());
            } else {
                System.out.println("No more cards");
            }
            controller.setButtonDisabled(false);
        } else {
            // controller.lbl_info.setText("You cannot draw more cards");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You cannot draw more cards");
            alert.showAndWait();
        }
    }

    /**
     * Sets the game's active state.
     *
     * @param active The active state to set for the game.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Sets the card's active state.
     *
     * @param cardActive The active state to set for the card.
     */
    public void setCardActive(boolean cardActive) {
        this.cardActive = cardActive;
    }
}
