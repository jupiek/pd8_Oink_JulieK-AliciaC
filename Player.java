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

    public Player(String n){
	_hand = new ArrayList<Card>();
	_properties = new ArrayList<LList>();
	_money = new BST();
	name = n;
    }	
    
    public void display(ArrayList<Card> cards){
	String retStr;
	retStr = "";
	for (int i = 0; i <cards.size() -1; i++){
	    retStr+= "Card" +i;
	    Card c = cards.get(i);
	    retStr += "["+  c.getType()+ ":"  + c.getName() +"\n" +c.getDescription() + "\n" + "]" + "\n"+"\n";
	}
    
	return retStr;
    }
    
    public int  searchColor(String color){
	//returns where in _properties this color property is if not in then will return -1
	for (int i; i< _properties.size(); i++){
	    if (_properties.get(i).getColor().equals(color)){
		return i;
	    }
	}
	return -1;
    }

    public void placeInProperties(PropertyCard c){ // place new property card in correct place in _properties
        String color = c.getColor();
	if (searchColor(color) ==-1){ //if card's color is not already in _properties then add to the end
	    _properties.add(c);
	}
	else{ //if card's color already has a linked list in _properties
	    int index = searchColor(color);
	    _properties.get(index).add(c);
	}
	System.out.println(c.getName() + " was successfully added onto the board.");
    }
	    

    public void draw2(RQueue deck){
	_hand.add(deck.pop());
	_hand.add(deck.pop());
    }

    public void checkHand(){
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
    
    public void useActionCard(ActionCard x, Player p){
	if (x.getName().equals("Sly Deal")){
	    this.useSlyDeal( p);
	}
	//else if all the other action cards
    }

    /* ALL ACTION CARD METHODS */
    public boolean useSlyDeal(Player p){
	System.out.println("Which card would you like to steal from " + p.getName());
	boolean stolenYet = false;
	while (!stolenYet){ 
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
