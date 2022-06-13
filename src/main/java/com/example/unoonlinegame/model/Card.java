package com.example.unoonlinegame.model;

public class Card {
    private int number;
    private Color color;

    public Card(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    public String getUrl() {
        return color + Integer.toString(number) + ".png";
    }
}
