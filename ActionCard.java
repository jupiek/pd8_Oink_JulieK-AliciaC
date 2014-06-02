public class ActionCard extends Card {

    String description; 
    
    public ActionCard(String n) {

	if (n.equals ("Sly Deal")){
	
	    value = 3;
	    name = n;
	    type = "Action";
	    inField = "false";
	}
	else {
	    super();
	}
    }

    

    public String  getDescription() {
	if( name = "Sly Deal") {
	    return "Steal a property from the player of your choice (cannot be part of a full set)";
	}

    }
}