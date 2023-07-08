/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.Random;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {

    //The group of cards, stored in an array
    private Card[] cards;
    private int cardsInDeck; //the size of the grouping

    public GroupOfCards() { // Uno Deck have 108  cards
         cards = new Card[108];
    }
    
    // assign value of Uno Deck
    public void reset(){
        Card.Color[] colors = Card.Color.values();
        cardsInDeck = 0;
        for (int i = 0; i < colors.length - 1; i++ ) {
            Card.Color color = colors[i];
            
            
            // assign the value of 0
            cards[cardsInDeck++] = new Card(color, Card.Value.getValue(0));
            
            // assign the value of 1 - 9
            for (int  j = 1; j < 10; j++) {
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));

            }
            
            // assign draw2, skip and reverse card.
            Card.Value[] values = new Card.Value[]{Card.Value.DrawTwo,Card.Value.Skip, Card.Value.Reverse};
            for (Card.Value value: values){
                cards[cardsInDeck++] = new Card(color, value);
                cards[cardsInDeck++] = new Card(color, value);

            }
            
            
            
        }
        // asign wild wild and wild four card.
        Card.Value[] values = new Card.Value[]{Card.Value.Wild, Card.Value.Wild_Four};
        for (Card.Value value: values) {
            cards[cardsInDeck++] = new Card(Card.Color.Wild, value);
            
            
            
        
        }
        
        

    }
    

//    /**
//     * A method that will get the group of cards as an ArrayList
//     *
//     * @return the group of cards.
//     */
//    public ArrayList<Card> getCards() {
//        return cards;
//    }

    public void shuffle(){
        int n = cards.length;
        Random random = new Random();
        
        for (int i = 0; i < cards.length; i++) {
            
            int randomValue = i + random.nextInt(n - i);
            Card randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }
    
    // to draw a number of card
    public Card[] drawCard(int n) { // not done
        Card[] ret = new Card[n];
        return ret;
    }
    
    
    
    //  to replace empty deck with the deck pile
    public void replaceDeckWith(){ // not done
    }

}//end class
