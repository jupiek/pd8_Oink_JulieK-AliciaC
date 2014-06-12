public class RentCard extends ActionCard {

    private String color;

    public RentCard(String c ){
	super("Rent");
	name = "Rent";
	color = c;
    }
    
    public String getColor(){
	return color;
    }


    public String  getDescription() {
	return "All players pay you rent for properties you own in one of thse colors.  Color of Rent Card: " + getColor();
    }
}


	
