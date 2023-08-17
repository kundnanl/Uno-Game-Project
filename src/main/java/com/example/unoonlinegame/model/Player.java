package com.example.unoonlinegame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Player` class represents a player in the Uno game.
 * This class encapsulates player-related information such as name and cards in
 * hand.
 * Players can receive cards, retrieve cards, and manage their hand of cards.
 *
 * @author Principal Authors:
 *         - Laksh Kundnani
 *         - Devendra Bisht
 *         - Vuong Quoc Nguyen
 *         - Neha
 */
public class Player {
    private String name;
    private List<Card> cards = new ArrayList<>();

    /**
     * Constructs a new `Player` instance with the specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gives a card to the player's hand.
     * This method adds a card to the player's hand of cards.
     *
     * @param card The card to be given to the player.
     */
    public void giveCard(Card card) {
        cards.add(card);
    }

    /**
     * Retrieves the list of cards in the player's hand.
     *
     * @return The list of cards in the player's hand.
     */
    public List<Card> getCards() {
        return cards;
    }

     /**
     * Retrieves the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves and removes a card from the player's hand based on the index.
     *
     * @param index The index of the card to be retrieved and removed.
     * @return The retrieved card.
     */
    public Card getAndDeleteCard(int index) {
        Card card = cards.get(index);
        cards.remove(index);
        return card;
    }

    /**
     * Retrieves a card from the player's hand based on the index.
     *
     * @param index The index of the card to be retrieved.
     * @return The retrieved card.
     */
    public Card getCard(int index) {
        return cards.get(index);
    }
}