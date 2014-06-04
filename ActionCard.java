public class ActionCard extends Card {
    
    public ActionCard( String n ) {
	type = "Action";
	inField = false;
	name = n;
	if (n.equals ("Sly Deal")) 
      	    value = 3;
	else if (n.equals("Forced Deal")) 
	    value = 3;
	else if (n.equals("It's my Birthday!")) 	
	    value = 2;
	else if (n.equals("Rent")) { //remaining should be "Rent Blue" "Rent Yellow" cards etc.
	    value = 1;
	}
    }

    public String  getDescription() {
	if( name .equals("Sly Deal")) {
	    return "Steal a property from the player of your choice (cannot be part of a full set)";
	}

    }
}
