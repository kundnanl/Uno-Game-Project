package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that represents a deck of cards.
 */
class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }
    public void resetDeck() {
        cards.clear();
        initializeDeck();
    }
    private void initializeDeck() {
        for (Card.Color color : Card.Color.values()) {
            if (color != Card.Color.Wild) {
                for (int value = 0; value <= 9; value++) {
                    for (int count = 0; count < 2; count++) {
                        cards.add(new Card(color, Card.Value.values()[value]));
                    }
                }

                // Add special cards: Skip, Reverse, Draw Two
                for (int count = 0; count < 2; count++) {
                    cards.add(new Card(color, Card.Value.Skip));
                    cards.add(new Card(color, Card.Value.Reverse));
                    cards.add(new Card(color, Card.Value.DRAW_TWO));
                }
            }
        }

        // Add Wild and Wild Draw Four cards
        for (int count = 0; count < 4; count++) {
            cards.add(new Card(Card.Color.Wild, Card.Value.Wild));
            cards.add(new Card(Card.Color.Wild, Card.Value.Wild_Four));
        }

        // Add Blank cards
        for (int count = 0; count < 4; count++) {
            cards.add(new Card(null, null));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty. Cannot deal a card.");
        }
        return cards.remove(cards.size() - 1);
    }
}
