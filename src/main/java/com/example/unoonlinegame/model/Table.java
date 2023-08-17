package com.example.unoonlinegame.model;

import com.example.unoonlinegame.GameController;

import java.util.*;

public class Table {
    private Card cardOnTable;
    private GameController controller;

    public Table(GameController controller) {
        this.controller = controller;
    }

    public ArrayList<Card> layCard(ArrayList<Card> cards) {
        cardOnTable = cards.get(cards.size()-1);
        cards.remove(cards.size()-1);
        controller.displayCard(cardOnTable);
        return cards;
    }

    public void layCard(Card card){
        cardOnTable = card;
        controller.displayCard(cardOnTable);
    }

    public Card getCardOnTable() {
        return cardOnTable;
    }
}
