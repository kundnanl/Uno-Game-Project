/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Game {
    
    // fields
    private final String name = "Unogame" ;//the title of the game
    private String[] playerIds;// the players of the game
    
    private GroupOfCards deck; // create deck
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<Card> stockPile;
    private int currentPlayer;
    
    
    // fields to validate move
    private Card.Color validColor;
    private Card.Value validValue;
    
    
    boolean gameDirection;
    // false is default direction
    // true is reverse;

    
    // methods to initialize the game
    public Game(String[] pids) {
        
        // shuffled deck
        deck = new GroupOfCards();
        deck.shuffle();
        stockPile = new ArrayList<Card>();
        
        playerIds = pids;
        currentPlayer = 0;
        gameDirection = false;
        playerHand = new ArrayList<ArrayList<Card>>();
        
        // an initial hand of cards.
        for (int i = 0; i < pids.length; i++) {
            ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
            
        }
    }

   

    // method for gameplay
    public void play(Game game) {
    };
    
    // method to return if someone has Empty hand
    public boolean isGameOver() {
        return false;
    }
    
    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public abstract void declareWinner();
    
     /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}//end class
