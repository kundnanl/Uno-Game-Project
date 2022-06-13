package com.example.unoonlinegame.model;

import com.example.unoonlinegame.GameController;

import java.util.*;

public class Game {
    private GameController controller;
    private List<Player> players = new ArrayList<>();
    private Tisch tisch;
    private ArrayList<Card> pale = new ArrayList<>();
    private final int numberOfCardsPerPlayer = 6;
    private int currentPlayer = 0;
    private boolean active = true;
    private boolean cardActive = true;


    public Game(GameController controller, List<Player> players) {
        this.players = players;
        this.controller = controller;
        tisch = new Tisch(controller);

    }

    public void start(){
        createCards();
        giveCardsToPlayer();
        pale = tisch.layCard(pale);

        controller.displayCards(players.get(currentPlayer).getCards());
        controller.setLbl_displayName(players.get(currentPlayer).getName());
    }

    public void nextPlayer(){
        if(currentPlayer == players.size() -1){
            currentPlayer = 0;
        }else{
            currentPlayer++;
        }
        controller.setLbl_displayName(players.get(currentPlayer).getName());
        controller.lbl_info.setText("nächster Spieler am Zug");
        controller.displayCards(players.get(currentPlayer).getCards());
    }

    private void giveCardsToPlayer(){
        for (Player player: players
             ) {
            for (int i = 0; i < numberOfCardsPerPlayer; i++) {
                player.giveCard(pale.get(pale.size()-1));
                pale.remove(pale.size()-1);
            }
        }
    }

    //create Cards
    private void createCards(){
        List<Card> newCards = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            newCards.add(new Card(i+1, Color.blue));
            newCards.add(new Card(i+1, Color.blue));
        }
        for (int i = 0; i < 12; i++) {
            newCards.add(new Card(i+1, Color.red));
            newCards.add(new Card(i+1, Color.red));
        }
        for (int i = 0; i < 12; i++) {
            newCards.add(new Card(i+1, Color.green));
            newCards.add(new Card(i+1, Color.green));
        }
        for (int i = 0; i < 12; i++) {
            newCards.add(new Card(i+1, Color.yellow));
            newCards.add(new Card(i+1, Color.yellow));
        }
        for (int i = 0; i < 4; i++) {
            newCards.add(new Card(1, Color.black));
            newCards.add(new Card(2, Color.black));
        }
        //shuffle Cards
        Collections.shuffle(newCards);

        pale.addAll(newCards);
    }


    public void buttonPressed(int index){
        System.out.println(index);
        //remove card and add to pile
        if ((players.get(currentPlayer).getCard(index).getColor() == tisch.getCardOnTable().getColor() || players.get(currentPlayer).getCard(index).getNumber() == tisch.getCardOnTable().getNumber() || players.get(currentPlayer).getCard(index).getColor() == Color.black || tisch.getCardOnTable().getColor() == Color.black)&& cardActive){
            pale.add(tisch.getCardOnTable());
            tisch.layCard(players.get(currentPlayer).getAndDeleteCard(index));

            active = false;
            cardActive = false;
            controller.setButtonDisabled(false);
            controller.displayCards(players.get(currentPlayer).getCards());
        }else{
            controller.lbl_info.setText("Sie könne dies nicht tun");
        }
    }

    public void buttonPressed(){
        if (active){
            players.get(currentPlayer).giveCard(pale.get(pale.size()-1));
            pale.remove(pale.size()-1);
            controller.displayCards(players.get(currentPlayer).getCards());
            if(pale.size() == 0){
                System.out.println("keine karten mehr");
            }
            controller.setButtonDisabled(false);
            active=false;
        }else{
            controller.lbl_info.setText("Sie können keine Karte mehr ziehen");
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCardActive(boolean cardActive) {
        this.cardActive = cardActive;
    }
}
