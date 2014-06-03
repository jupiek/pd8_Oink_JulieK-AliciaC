public class PropertyCard extends Card {

    int rent1;
    int rent2;
    int rent3;
    String color;
    
    public Property( String s, String c ) {
    	type = "Property"
   	name = s;
   	color = c;
   	if (color == "Blue") {
	    rent1 = 3;
	    rent2 = 5;
	    rent3 = 8;
	    value = 4;
   	}
   	else if (color == "Yellow") {
	    rent1 = 2;
	    rent2 = 4;
	    rent3 = 6;
	    value = 3;
   	}
   	else if (color == "Orange") {
	    rent1 = 1;
	    rent2 = 3;
	    rent3 = 5;
	    value = 2;
   	}
   	else if (color == "Green") {
	    rent1 = 2;
	    rent2 = 4;
	    rent3 = 7;
	    value = 4;
   	}
   	else if (color == "Pink") {
	    rent1 = 2;
	    rent2 = 3;
	    rent3 = 6;
	    value = 3;
   	}
    }
	
    public int getRent( int n ) {
	if (n == 1)
	    return rent1;
	if (n == 2) 
	    return rent2;
	else
	    return rent3;
    }

    public String getDescription() {
	return "Description for Property " + name + ": " + "Rent for 1: " + rent1 + "\n" + "Rent for 2: " + rent2 + "\n" + "Rent for 3: " + rent3;
    }

    public String getColor() {
	return color;
    }
    
}
