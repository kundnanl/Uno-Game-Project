package com.example.unoonlinegame.model;

import com.example.unoonlinegame.GameController;

import java.util.*;

/**
 * The `Table` class represents the table where the game is played. It manages the card currently on the table.
 * The table displays the top card of the pile.
 * This class also interacts with the `GameController` to display the card on the GUI.
 *
 * @author Principal Authors:
 * - Laksh Kundnani
 * - Devendra Bisht
 * - Vuong Quoc Nguyen
 * - Neha
 */public class Table {
    private Card cardOnTable;
    private GameController controller;

    /**
     * Constructor for the `Table` class.
     *
     * @param controller The GameController instance responsible for managing the GUI.
     */
    public Table(GameController controller) {
        this.controller = controller;
    }

    /**
     * Lay a card on the table from the given list of cards and update the displayed card on the GUI.
     *
     * @param cards The list of cards from which the top card is laid on the table.
     * @return The list of cards after the top card has been removed.
     */
    public ArrayList<Card> layCard(ArrayList<Card> cards) {
        cardOnTable = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        controller.displayCard(cardOnTable);
        return cards;
    }

    /**
     * Lay a specific card on the table and update the displayed card on the GUI.
     *
     * @param card The card to be laid on the table.
     */
    public void layCard(Card card) {
        cardOnTable = card;
        controller.displayCard(cardOnTable);
    }

    /**
     * Get the card currently on the table.
     *
     * @return The card currently on the table.
     */
    public Card getCardOnTable() {
        return cardOnTable;
    }
}
