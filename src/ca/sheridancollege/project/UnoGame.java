package ca.sheridancollege.project;

import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.Card.Value;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * A class that represents the Uno Game.
 */
public class UnoGame {
    private List<Player> players; // the players in the game
    private Deck deck; // the deck of cards
    private List<Card> stockPile; // the pile of cards in the stock
    private int currentPlayerIndex; // the index of the current player
    private boolean gameDirection; // the direction of the game (false: default, true: reverse)
    private Scanner scanner; // Scanner object for user input

    public UnoGame(String[] playerNames) {
        this.players = new ArrayList<>();
        initializePlayers(playerNames);
        this.deck = new Deck();
        this.stockPile = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.gameDirection = false;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initialize the players in the game.
     *
     * @param playerNames the names of the players
     */
    private void initializePlayers(String[] playerNames) {
        for (String name : playerNames) {
            players.add(new Player(name));
        }
    }

    /**
     * Start the Uno game.
     */
    public void startGame() {
        deck.shuffle(); // Shuffle the deck before starting the game
        dealInitialCards();
        Card initialCard = deck.dealCard();
        stockPile.add(initialCard);
        Player.setStockPile(stockPile);
        playGame();
    }

    /**
     * Deal initial cards to all players.
     */
    private void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card card = deck.dealCard();
                player.addCard(card);
            }
        }
    }
    /**
     * Get the next player based on the game direction.
     *
     * @param currentPlayer the current player
     * @return the next player
     */
    public Player getNextPlayer(Player currentPlayer) {
        int nextPlayerIndex;
        if (gameDirection) {
            nextPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        return players.get(nextPlayerIndex);
    }
/**
 * Play the Uno game until a player wins.
 */
private void playGame() {
    boolean gameWon = false;
    while (!gameWon) {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("\nIt's " + currentPlayer.getName() + "'s turn.");
        System.out.println("Top card on the stock pile: " + stockPile.get(stockPile.size() - 1));

        boolean validMove = false;
        while (!validMove) {
            if (currentPlayer.getHand().size() > 0) {
                System.out.println("Your hand: " + currentPlayer.getHand());
                System.out.println("Enter the index of the card you want to play (or -1 to draw):");

                int cardIndex = scanner.nextInt();
                if (cardIndex >= 0 && cardIndex < currentPlayer.getHand().size()) {
                    Card selectedCard = currentPlayer.getHand().get(cardIndex);
                    if (isValidMove(selectedCard)) {
                        currentPlayer.play(this);
                        stockPile.add(selectedCard);
                        System.out.println(currentPlayer.getName() + " played " + selectedCard);
                        validMove = true;
                        checkGameWon(currentPlayer);
                        if (selectedCard.getValue() == Value.DRAW_TWO) {
                            Player nextPlayer = players.get(getNextPlayerIndex());
                            for (int i = 0; i < 2; i++) {
                                Card drawnCard = deck.dealCard();
                                nextPlayer.addCard(drawnCard);
                            }
                            System.out.println(nextPlayer.getName() + " drew 2 cards.");
                        }
                    } else {
                        System.out.println("Invalid move! Try again.");
                    }
                } else if (cardIndex == -1) {
                    Card drawnCard = deck.dealCard();
                    currentPlayer.addCard(drawnCard);
                    System.out.println(currentPlayer.getName() + " drew a card.");
                    validMove = true;
                } else {
                    System.out.println("Invalid input! Try again.");
                }
            } else {
                System.out.println("Your hand is empty. You cannot play a card. Drawing a card...");
                Card drawnCard = deck.dealCard();
                currentPlayer.addCard(drawnCard);
                System.out.println(currentPlayer.getName() + " drew a card.");
                validMove = true;
            }
        }

        currentPlayerIndex = getNextPlayerIndex();
        gameWon = isGameOver();
    }
}



    /**
     * Check if the game is over (i.e., a player has an empty hand).
     *
     * @return true if the game is over, false otherwise
     */
    private boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the index of the next player based on the game direction.
     *
     * @return the index of the next player
     */
    private int getNextPlayerIndex() {
        if (gameDirection) {
            return (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            return (currentPlayerIndex + 1) % players.size();
        }
    }

    /**
     * Declare and display the winning player.
     */
    private void declareWinner() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                System.out.println("\nPlayer " + player.getName() + " wins!");
            }
        }
    }
    
    /**
     * Check if the game is won by a player.
     *
     * @param player the player to check for winning
     */
    private void checkGameWon(Player player) {
        if (player.getHand().isEmpty()) {
            System.out.println("\nPlayer " + player.getName() + " wins!");
            System.exit(0);
        }
    }
    /**
     * Get the deck of cards.
     *
     * @return the deck of cards
     */
    public Deck getDeck() {
        return deck;
    }
    private boolean isValidMove(Card card) {
    Card topCard = stockPile.get(stockPile.size() - 1);
return card.getColor().equals(topCard.getColor()) || card.getValue().equals(topCard.getValue()) || card.getValue() == Value.DRAW_TWO;
}


    public static void main(String[] args) {
        String[] playerNames = {"Player 1", "Player 2", "Player 3", "Player 4"};
        UnoGame game = new UnoGame(playerNames);
        game.startGame();
    }
}
