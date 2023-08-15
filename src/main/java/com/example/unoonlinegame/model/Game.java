package com.example.unoonlinegame.model;

import com.example.unoonlinegame.GameController;

import java.util.*;

public class Game {
    private GameController controller;
    private List<Player> players = new ArrayList<>();
    private Table table;
    private ArrayList<Card> pile = new ArrayList<>();
    private final int numberOfCardsPerPlayer = 6;
    private int currentPlayer = 0;
    private boolean active = true;
    private boolean cardActive = true;

    public Game(GameController controller, List<Player> players) {
        this.players = players;
        this.controller = controller;
        table = new Table(controller);
    }

    public void start() {
        createCards();
        giveCardsToPlayer();
        pile = table.layCard(pile);

        controller.displayCards(players.get(currentPlayer).getCards());
        controller.setLbl_displayName(players.get(currentPlayer).getName());
    }

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

    private void giveCardsToPlayer() {
        for (Player player : players) {
            for (int i = 0; i < numberOfCardsPerPlayer; i++) {
                player.giveCard(pile.get(pile.size() - 1));
                pile.remove(pile.size() - 1);
            }
        }
    }

    // Create Cards
    private void createCards() {
        List<Card> newCards = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            newCards.add(new Card(i + 1, Color.blue));
            newCards.add(new Card(i + 1, Color.blue));
        }
        for (int i = 0; i < 12; i++) {
            newCards.add(new Card(i + 1, Color.red));
            newCards.add(new Card(i + 1, Color.red));
        }
        for (int i = 0; i < 12; i++) {
            newCards.add(new Card(i + 1, Color.green));
            newCards.add(new Card(i + 1, Color.green));
        }
        for (int i = 0; i < 12; i++) {
            newCards.add(new Card(i + 1, Color.yellow));
            newCards.add(new Card(i + 1, Color.yellow));
        }
        for (int i = 0; i < 4; i++) {
            newCards.add(new Card(1, Color.black));
            newCards.add(new Card(2, Color.black));
        }
        // Shuffle Cards
        Collections.shuffle(newCards);

        pile.addAll(newCards);
    }

    public void buttonPressed(int index) {
        System.out.println(index);
        // Remove card and add to pile
        if ((players.get(currentPlayer).getCard(index).getColor() == table.getCardOnTable().getColor() ||
             players.get(currentPlayer).getCard(index).getNumber() == table.getCardOnTable().getNumber() ||
             players.get(currentPlayer).getCard(index).getColor() == Color.black ||
             table.getCardOnTable().getColor() == Color.black) && cardActive) {
            pile.add(table.getCardOnTable());
            table.layCard(players.get(currentPlayer).getAndDeleteCard(index));

            active = false;
            cardActive = false;
            controller.setButtonDisabled(false);
            controller.displayCards(players.get(currentPlayer).getCards());
        } else {
            controller.lbl_info.setText("You cannot do this");
        }
    }

    public void buttonPressed() {
        if (active) {
            players.get(currentPlayer).giveCard(pile.get(pile.size() - 1));
            pile.remove(pile.size() - 1);
            controller.displayCards(players.get(currentPlayer).getCards());
            if (pile.size() == 0) {
                System.out.println("No more cards");
            }
            controller.setButtonDisabled(false);
            active = false;
        } else {
            controller.lbl_info.setText("You cannot draw more cards");
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCardActive(boolean cardActive) {
        this.cardActive = cardActive;
    }
}
