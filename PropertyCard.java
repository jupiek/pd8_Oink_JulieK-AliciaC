public class PropertyCard extends Card {

    int rent1;
    int rent2;
    int rent3;

    public Property() {
    	super();
    }

    public String getDescription() {
	return "Rent for 1: " + rent1 + "\n" + "Rent for 2: " + rent2 + "\n" + "Rent for 3: " + rent3;
    }
    
}
