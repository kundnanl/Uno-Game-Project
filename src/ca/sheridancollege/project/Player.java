package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that models each Player in the game. Players have a unique identifier.
 */
public class Player {
    private Deck deck;
    private String name; // the unique name for this player
    private List<Card> hand; // the player's hand of cards
    private static List<Card> stockPile; // reference to the stock pile in UnoGame
    private boolean gameDirection; // the direction of the game (false: default, true: reverse)

    /**
     * A constructor that allows you to set the player's unique name.
     *
     * @param name the unique name to assign to this player.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.gameDirection = false;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Ensure that the player's name is unique.
     *
     * @param name the player's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add a card to the player's hand.
     *
     * @param card the card to add
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Remove a card from the player's hand.
     *
     * @param card the card to remove
     * @return true if the card was successfully removed, false otherwise
     */
    public boolean removeCard(Card card) {
        return hand.remove(card);
    }

    /**
     * Get the player's hand of cards.
     *
     * @return the player's hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Set the reference to the stock pile.
     *
     * @param stockPile the stock pile of cards
     */
    public static void setStockPile(List<Card> stockPile) {
        Player.stockPile = stockPile;
    }

    /**
     * Set the game direction.
     *
     * @param gameDirection the game direction (false: default, true: reverse)
     */
    public void setGameDirection(boolean gameDirection) {
        this.gameDirection = gameDirection;
    }
/**
 * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
 * with logic to play your game.
 *
 * @param game the UnoGame object
 */
public void play(UnoGame game) {
    System.out.println("It's " + getName() + "'s turn.");
    System.out.println("Top card on the stock pile: " + stockPile.get(stockPile.size() - 1));

    // Check if the player has any playable cards
    List<Card> playableCards = getPlayableCards();
    if (!playableCards.isEmpty()) {
        // Player has playable cards
        Card cardToPlay = chooseCardToPlay(playableCards);
        removeCard(cardToPlay);
        if (cardToPlay.getValue() == Card.Value.DRAW_TWO) {
            int drawCount = 2;
            Player nextPlayer = game.getNextPlayer(this);
            for (int i = 0; i < drawCount; i++) {
                Card drawnCard = game.getDeck().dealCard();
                nextPlayer.addCard(drawnCard);
            }
            System.out.println(getName() + " played " + cardToPlay + ". " + nextPlayer.getName() + " drew " + drawCount + " cards.");
        } else {
            stockPile.add(cardToPlay);
            System.out.println(getName() + " played " + cardToPlay);
        }
    } else {
        // Player doesn't have playable cards, draw a card from the deck
        Card drawnCard = game.getDeck().dealCard();
        addCard(drawnCard);
        System.out.println(getName() + " drew a card.");
    }

    // Check if the player has an empty hand (i.e., wins the game)
    if (getHand().isEmpty()) {
        System.out.println(getName() + " wins!");
    }
}


    /**
     * Get the playable cards from the player's hand based on the top card on the stock pile.
     *
     * @return the list of playable cards
     */
    private List<Card> getPlayableCards() {
        Card topCard = stockPile.get(stockPile.size() - 1);
        List<Card> playableCards = new ArrayList<>();

        for (Card card : hand) {
            if (card.getColor() == topCard.getColor() || card.getValue() == topCard.getValue()
                    || card.getColor() == Card.Color.Wild) {
                playableCards.add(card);
            }
        }

        return playableCards;
    }

    /**
     * Choose a card to play from the list of playable cards.
     *
     * @param playableCards the list of playable cards
     * @return the card to play
     */
    private Card chooseCardToPlay(List<Card> playableCards) {
        // Choose a card randomly (you can implement your own strategy here)
        int randomIndex = (int) (Math.random() * playableCards.size());
        return playableCards.get(randomIndex);
    }
    
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
