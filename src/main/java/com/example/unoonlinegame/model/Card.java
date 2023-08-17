
package com.example.unoonlinegame.model;

public class Card {
    private int number;
    private Color color;
    private CardType cardType;

    public Card(int number, Color color, CardType cardType) {
        this.number = number;
        this.color = color;
        this.cardType = cardType;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

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

    public CardType getCardType() {
        return cardType;
    }

}