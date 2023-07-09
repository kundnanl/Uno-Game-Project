/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
/**
 * A class to represent a card in the Uno Game.
 */
public class Card {
    public enum Color {
        Red, Blue, Green, Yellow, Wild
    }

    public enum Value {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Skip, Reverse, Wild, Wild_Four, DRAW_TWO
    }

    private final Color color;
    private final Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + "_" + value;
    }
}