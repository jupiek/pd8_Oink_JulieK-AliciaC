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
    
    public String getName(){
	return name;
    }

    public void turn(CLList players, ALStack deck){
	draw(deck);
	draw(deck);
	System.out.println("\nIt is now your turn, " + getName() + ". You have drawn 2 cards.");
	Scanner sc = new Scanner(System.in);
	int moves = 3;
	while (moves >0){
	    System.out.println("Hand:");
	    display(_hand);
	    System.out.println("\nYou have " + moves + " moves left to make. If you wish to end your turn, enter 'pass'. If not, then select the index of the card you wish to play.");
	    String choice = sc.nextLine();
	    if (choice.equals ("pass")){
		System.out.println("Next Player's Turn");
		players._head = players._head.getNext();
		moves = 0;
		DLLNode a =(DLLNode) players._head;
		Player temp = (Player)a.getCargo();
		temp.turn(players,deck);
	    }
	    else if (onlyNumbers(choice)){
		Card activated = _hand.remove(Integer.parseInt(choice)); //removes card
		if (activated.getType() .equals( "Money")){
		    _money.insert(activated.getValue());
		    moves = moves -1;
		}
		else if (activated.getType().equals( "Action")){
		    boolean  played = false;
		    while (played == false){
			System.out.println( "Which player would you like to use this action card on?"); 
			String p = sc.nextLine();
			if (onlyNumbers(p)){
			    useActionCard((ActionCard)activated,(Player) players.get(Integer.parseInt(p)));
			    played = true;
			    moves = moves -1;
			}
			else {
			    System.out.println("Please enter an integer indicating which player you would like to use this card on");
			}
		    }
			   
		}
		
		else{ //card is property
		    PropertyCard c = (PropertyCard) activated;
		    this.placeInProperties(c);
		    moves = moves -1;
		}
	    }
	 
		
		else {
		    System.out.println("Please enter an integer indicating which card you would like to play");
	    }
	}
    }

	    
	    
	

    public void display( ArrayList<Card> cards ) { //display method for not _properties ArrayLists
	for (int i = 0; i < cards.size(); i++) {
	    String retStr = "";
	    retStr+= "Card " + i + ": ";
	    Card c = cards.get(i);
	    retStr += "\n [TYPE: " +  c.getType()+ "\n NAME: " + c.getName() + "\n DESCRIPTION: " + c.getDescription() + "]";
	    System.out.println( retStr );
        }			
    }
    
    public void displayPropertyCards() {
    	for (int i = 0; i < _properties.size(); i++) {
	    String retStr = "Property Set " + i +":";
	    if (!(_properties.get(i).size()==0)){
		for (int j = 0; j<_properties.get(i).size(); j++){
		    LList l = (LList)_properties.get(i);
		    PropertyCard c = (PropertyCard)l.get(j);
		    retStr += "NAME:" + c.getName() +"\n" + c.getDescription();
		}
	    }
	    else{
		System.out.println("EMPTY");
	    }
	    
	    System.out.println(retStr);
	}
    }
    
    
    public void placeInProperties( PropertyCard c ) { // place new property card in correct place in _properties
	boolean alreadyHasColor = false;
	if (_properties.isEmpty()){
	    LList t = new LList<PropertyCard> ();
	    t.add(c);
	    _properties.add(t);
	}
	else {
	    for (int i =0; i< _properties.size()&&!alreadyHasColor; i++){
		LList prop = (LList) _properties.get(i);
		PropertyCard temp = (PropertyCard)prop.get(0);
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
	}	
    }
	    

    public void draw( ALStack deck ) {
	_hand.add((Card)deck.pop());
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


    public void pay(Player p, int debt){
	System.out.println ("This is your money pile: " + _money.preOrderTrav()); 

	Scanner sc = new Scanner(System.in);
	int sum = 0;
	if (debt >0){
	    sum = _money.sum();//sum of monies
	    if (sum >= debt){//you have enough money left to pay
		try {
		    System.out.println("Money owed: " + debt);
		    System.out.println ("Choose the value of a card you would like to use to pay " + p.getName());
		    String choice = sc.nextLine();	 
		    _money.remove(Integer.parseInt(choice));
		    debt = debt - Integer.parseInt(choice);
		    sum = _money.sum();
		}
		catch (ClassCastException ex){	    
		    System.out.println("Please enter an integer indicating the value of the  card you would like to remove");
		}
	    }
	    else {//you don't have enough money	
		System.out.println("You must pay with your properties or a combination of properties and money. These are your properties: " );
		displayPropertyCards();
		System.out.println("Choose the set of properties you would like to use a Property card from to pay with");
		System.out.println("Money Owed: " + debt);
		try { //removing a property card
		    String choice = sc.nextLine();
		    PropertyCard removed =(PropertyCard)_properties.get(Integer.parseInt(choice)).remove(0);
		    debt = debt - removed.getValue();
		    sum = _money.sum();
		    System.out.println("You now have this much money left to pay: " + debt);
		}
		catch (ClassCastException ex){	    
		    System.out.println("Please enter an integer indicating the index of the card you would like to remove");
		}
	    }
	    pay (p,debt);
	}
	else {
	    System.out.println ( "You no longer owe any more money");
	}
    }
    
    
    public void useActionCard( ActionCard x, Player p ) {
	if (x.getName().equals("Sly Deal"))
	    this.useSlyDeal(p);
	/*	  if (x.getName().equals("Forced Deal"))
		  this.useForcedDeal(p);
		  if (x.getName().equals("It's My Birthday!"))
		  this.useIBM(p);
	*/
	
	else if (x.getName().equals("Rent")) {
	    RentCard rentcard = (RentCard)x; 
	    String rentcolor = rentcard.getColor();
	    useRentCard(rentcolor,players);

	}
    }
	
	
    /* ALL ACTION CARD METHODS */
    public boolean useSlyDeal( Player p ) {
	boolean stolenYet = false;
	Scanner sc = new Scanner(System.in);
	PropertyCard stolen = (PropertyCard)p._properties.get(0).get(0);
	while (! stolenYet) { 
	    System.out.println("Which card would you like to steal from " + p.name + "?");
	    p.displayPropertyCards();
	    String choice = sc.nextLine();
	    if (onlyNumbers(choice)){
		LList temp =  p._properties.get(Integer.parseInt(choice));
		stolen = (PropertyCard)temp.removeLast();
		stolenYet = true;
	    }
	    else {
		System.out.println("Please enter an integer indicating which card you would like to steal");
		stolenYet = false;
	    }
	}
	placeInProperties(stolen);
	return stolenYet; //should always be true
    }

    public boolean useRentCard(Strint col,CLList players){
	if ( _properties.get(i).size() == 0){
	    System.out.println("You may not use this card. You do not have any properties with this color");
	    return false;}
	else {
	    LList props = _properties.get(i);
	    for (int i = 0;i < _properties.get(i).size(); i++){
		if ( col.equals(_properties.get(i).get(0).getColor()){
			props = _properties.get(i);
			int count = props.size();
			int rent = props.getRent(count + 1);
			//make all the players pay in CLLIst
			return true;
		    
		    }
		    }
	    }
	}
    }
    public boolean onlyNumbers(String str) {
	    if (str == null || str.length() == 0)
		return false;
			for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }   
        return true;
    }
}
    

