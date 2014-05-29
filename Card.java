public class Card {
    
    int value ; //1 will be $1M, 2 willbe $2M... money values can only be 1,2,3,4,5, and 10.
    String name;
    boolean inField;
    String type;
    
    public Card() { //will always be overridden
	value = 1;
	name = "";
	inField = false;
    }
    
    public int getValue() {
	return value;
    }
    
    public String getName() {
	return name;
    }
    
}
