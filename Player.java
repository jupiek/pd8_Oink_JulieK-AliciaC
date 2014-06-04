import java.util.*;
import java.io.*;

public class Player{
    ArrayList<Card> _hand;
    ArrayList<LList> _properties;
    BST _money;
    String name;

    public Player(){
	_hand = new ArrayList<Card>();
	_properties = new ArrayList<LList>(5);
	for (int i = 0; i <= 5; i ++) {	
		_properties.add( new LList<PropertyCard> );		
	}
	_money = new BST();
    }	

    public Player( String n ) {
	_hand = new ArrayList<Card>();
	_properties = new ArrayList<LList>();
	_money = new BST();
	name = n;
    }	
    
    public void display( ArrayList<Card> cards ) {
	for (int i = 0; i < cards.size(); i++) {
	    String retStr = "";
	    retStr+= "Card " + i + ": ";
	    Card c = cards.get(i);
	    retStr += "[TYPE: " +  c.getType()+ "NAME: " + c.getName() + "DESCRIPTION: " + c.getDescription() + "]";
	    System.out.println( retStr );
        }			
    }
    
    public void display( LList<PropertyCard> cards ) {
    	for (int i = 0; i < LList.size()) {
    		String retStr = "";
    		//FINISH AT HOME!!!! DISPLAY ALL OF THE PROPERTY CARDS OF ONE COLOR TOGETHER
    	}
    }
    
    
    public void placeInProperties( PropertyCard c ) { // place new property card in correct place in _properties
        String color = c.getColor();
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
	System.out.println( c.getName() + " was successfully added onto the board." );
    }
	    

    public void draw2( RQueue deck ) {
	_hand.add(deck.pop());
	_hand.add(deck.pop());
    }


    public void checkHand() {
	int total = _hand.size();
	while (total> 7) {
	    System.out.println("Too many cards in hand. Please select a card to discard.");
	    display(_hand);
	    Scanner sc = new Scanner(System.in);
	    try {
		String choice = sc.nextLine();	 
		_hand.remove((int)choice);
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
	if (x.getName().equals("Forced Deal"))
	    this.useForcedDeal(p);
	if (x.getName().equals("It's My Birthday!"))
            this.useIBM(p);
        if (x.getName().equals("Rent")) {
        	x.getColor();
        	//etc.
        }
    }

    /* ALL ACTION CARD METHODS */
    public boolean useSlyDeal( Player p ) {
	System.out.println("Which card would you like to steal from, " + p.getName()); + "?"'
	boolean stolenYet = false;
	while (! stolenYet) { 
	    display(p._properties);
	    Scanner sc = new Scanner(System.in);
	    try {
		String choice = sc.nextLine();	 
	        Card stolen =  p._properties.remove((int)choice);

	    }
	    catch (ClassCastException ex){
		System.out.println("Please enter an integer indicating which card you would like to steal");
	    }
	}
	    placeInProperties(stolen);
	}

    }
