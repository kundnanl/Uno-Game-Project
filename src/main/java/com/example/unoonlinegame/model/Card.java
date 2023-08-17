
package com.example.unoonlinegame.model;

/**
 * The `Card` class represents a card in the Uno game.
 * Each card has a number, color, and type.
 * This class provides methods to retrieve the card's attributes and URL for its image.
 *
 * @author Principal Authors:
 * - Laksh Kundnani
 * - Devendra Bisht
 * - Vuong Quoc Nguyen
 * - Neha
 */
public class Card {
    private int number;
    private Color color;
    private CardType cardType;

    /**
     * Constructs a new `Card` instance with the specified number, color, and card type.
     *
     * @param number   The number of the card.
     * @param color    The color of the card.
     * @param cardType The type of the card.
     */
    public Card(int number, Color color, CardType cardType) {
        this.number = number;
        this.color = color;
        this.cardType = cardType;
    }

    /**
     * Retrieves the number of the card.
     *
     * @return The number of the card.
     */
    public int getNumber() {
        return number;
    }

/**
     * Retrieves the color of the card.
     *
     * @return The color of the card.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retrieves the URL for the image of the card.
     *
     * @return The URL for the card's image.
     */
    public String getUrl() {
        String colorName = color.toString().toLowerCase();
        String cardTypeName = "";

        if (color == Color.black && cardType == CardType.WILD_CARD) {
            cardTypeName = "_wild_card";
        }

        if (color == Color.black && cardType == CardType.DRAW_FOUR) {
            cardTypeName = "_draw_four";
        } else if (color != Color.black) {
            if (cardType == CardType.DRAW_TWO) {
                cardTypeName = "_drawtwo";
            } else if (cardType == CardType.REVERSE) {
                cardTypeName = "_reverse";
            } else if (cardType == CardType.SKIP) {
                cardTypeName = "_skip";
            }
        }

        return colorName + (cardType == CardType.NUMBER ? number : cardTypeName) + ".png";
    }

    /**
     * Retrieves the type of the card.
     *
     * @return The type of the card.
     */
    public CardType getCardType() {
        return cardType;
    }

}