import java.util.*;
import java.io.*;

public class Player{
    ArrayList<Card> _hand;
    ArrayList<LList> _properties;
    BST _money;
    String name;

    public Player(){
	_hand = new ArrayList<Card>();
	LList<PropertyCard> temp = new LList<PropertyCard>();
	_properties = new ArrayList<LList>();
	_properties.add(temp);
	_money = new BST();
    }	

    public Player( String n ) {
	_hand = new ArrayList<Card>();
	LList<PropertyCard> temp = new LList<PropertyCard>();
	_properties = new ArrayList<LList>();
	_properties.add(temp);
	name = n;
    }	
    
    public void display( ArrayList<Card> cards ) { //display method for not _properties ArrayLists
	for (int i = 0; i < cards.size(); i++) {
	    String retStr = "";
	    retStr+= "Card " + i + ": ";
	    Card c = cards.get(i);
	    retStr += "[TYPE: " +  c.getType()+ "NAME: " + c.getName() + "DESCRIPTION: " + c.getDescription() + "]";
	    System.out.println( retStr );
        }			
    }
    
    public void displayPropertyCards() {
    	for (int i = 0; i < _properties.size(); i++) {
	    String retStr = "Property Set " + i +":";
	    for (int j = 0; j<_properties.get(i).size(); j++){
		Card c = (Card)_properties.get(i).get(j);
		retStr += "NAME:" + c.getName() +"\n" + c.getDescription();
	    }
	    System.out.println(retStr);
	}
    }
    
    
    public void placeInProperties( PropertyCard c ) { // place new property card in correct place in _properties
	boolean alreadyHasColor = false;
	for (int i =0; i< _properties.size()&&!alreadyHasColor; i++){
	    PropertyCard temp = (PropertyCard)_properties.get(i).get(0);
	    String color = temp.getColor();
	    if (c.getColor().equals(color)){  //if color or property already exists in the AL
		_properties.get(i).add(c);
		alreadyHasColor = true;
	    }
	}
	if (!alreadyHasColor){
	    LList addition = new LList<PropertyCard>();
	    addition.add(c);
	    _properties.add(addition);
	}	
	    /*    String color = c.getColor();
        if (color == "Blue")
        	_properties.get(0).add(c);
        if (color == "Yellow")
        	_properties.get(1).add(c);        	
        if (color == "Orange")
        	_properties.get(2).add(c);
        if (color == "Green")
        	_properties.get(3).add(c);
        if (color == "Pink")
        	_properties.get(4).add(c);
		System.out.println( c.getName() + " was successfully added onto the board." );*/
    }
	    

    public void draw2( RQueue deck ) {
	_hand.add((Card)deck.dequeue());
	_hand.add((Card)deck.dequeue());
    }


    public void checkHand() {
	int total = _hand.size();
	while (total> 7) {
	    System.out.println("Too many cards in hand. Please select a card to discard.");
	    display(_hand);
	    Scanner sc = new Scanner(System.in);
	    try {
		String choice = sc.nextLine();	 
		_hand.remove(Integer.parseInt(choice));
		total = _hand.size();
	    }
	    catch (ClassCastException ex){

		System.out.println("Please enter an integer indicating which card you would like to remove");
	    }
	}
    }
    
    public void useActionCard( ActionCard x, Player p ) {
	if (x.getName().equals("Sly Deal"))
	    this.useSlyDeal(p);
	/*
	  if (x.getName().equals("Forced Deal"))
	  this.useForcedDeal(p);
	  if (x.getName().equals("It's My Birthday!"))
	  this.useIBM(p);
	  if (x.getName().equals("Rent")) {
	  x.getColor();
	  //etc. */
    }
    

    /* ALL ACTION CARD METHODS */
    public boolean useSlyDeal( Player p ) {
	System.out.println("Which card would you like to steal from " + p.name + "?");
	boolean stolenYet = false;
	Scanner sc = new Scanner(System.in);
	while (! stolenYet) { 
	    p.displayPropertyCards();
	    try {
		String choice = sc.nextLine();	 
		LList temp =  p._properties.get(Integer.parseInt(choice));
		PropertyCard stolen = (PropertyCard)temp.removeLast();
		stolenYet = true;

	    }
	    catch (ClassCastException ex){
		System.out.println("Please enter an integer indicating which card you would like to steal");
	    }

	}
    
	placeInProperties(stolen);
    }

}
