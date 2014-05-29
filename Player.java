public class Player{
    ArrayList<Card> _hand;
    ArrayList <LList> _properties;
    BST _money;

    public Player(){
	_hand = new ArrayList<Card>();
	_properties = new ArrayList<LList>();
	_money = new BST;
    }	

    public void display(ArrayList<Card> cards){
	String retStr;
	retStr = "";
	for (int i = 0; i <cards.size() -1, i++){
	    retStr+= "Card" +i;
	    if (c.type = "Action"){
		Card c = ArrayList.get(i);
		retStr += "["+  c.getType()+ ":" + c.getName() + "\n" + "]" + "\n";
	    }
	    else{
		Card c = ArrayList.get(i);
		retStr += "["+  c.getType()+ ":"  + c.getName() +"\n" +c.getDescription() + "\n" + "]" + "\n"+"\n";
	    }
	}
	return retStr;
    }
    


    public void draw2(){
	_hand.add(deck.pop());
	_hand.add(deck.pop());
    }

    public void checkHand(){
	total = _hand.size();
	while (total> 7) {
	    System.out.println("Too many cards in hand. Please select a card to discard.");
	    display(_hand);
	    Scanner sc = new Scanner(System.in);
	    String choice = sc.nextLine();
	    while  //choice is not an int use throw catch
		{
		    System.out.println("Please enter an integer indicating which card you would like to remove");
		    Scanner sc = new Scanner(System.in);
		    String choice = sc.nextLine();
		}
	    _hand.remove((int)choice);
	    total = _hand.size();
	}
    }
    
}
