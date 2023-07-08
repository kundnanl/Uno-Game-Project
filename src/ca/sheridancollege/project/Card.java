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
public abstract class Card {
    //default modifier for child classes

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    enum Color {
        Red, Blue, Green, Yellow, Wild ;
        private static final Color[] colors = Color.values();
        public static Color getColor(int i){
            return Color.colors[i];
        }
    }
    
    enum Value {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DrawTwo, Skip, Reverse, Wild, Wild_Four;
        private static final Value[] values = Value.values();
        public static Value getValue(int i){
            return Value.values[i];
        }
    }
    
    private final Color color;
    private final Value value;
    
    public Card(Color color, Value value) {
        this.color = color; 
        this.value = value;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public Value getValue() { 
        return this.value;
    }
    
    @Override
    public String toSting() {
        return this.color + "_" + this.value;
    }
}
