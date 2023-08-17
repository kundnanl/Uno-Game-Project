package com.example.unoonlinegame.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cards = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.name = name;
    }

    public void giveCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards(){
        return cards;
    }

    public Card getAndDeleteCard(int index){
        Card card = cards.get(index);
        cards.remove(index);
        return card;
    }

    public Card getCard(int index){
        return cards.get(index);
    }
}