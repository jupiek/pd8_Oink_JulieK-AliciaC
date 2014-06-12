public class RentCard extends ActionCard {
    private String color;

    public RentCard(String c ){
	ActionCard("Rent");
	name = "Rent";
	color = c;
    }
    
    public String getColor(){
	return color;
    }
}

	
