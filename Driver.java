import java.util.*;
import java.io.*;

public class Driver {

    public static void main( String[] args ) {
	ArrayList<Card> cards = new ArrayList<Card>();
	for (int count = 0; count <2; count++) {

	cards.add( new PropertyCard("Pennsylvania Avenue", "Green"));
	cards.add( new PropertyCard("North Carolina Avenue", "Green"));
	cards.add( new PropertyCard("Pacific Avenue","Green"));
	cards.add( new PropertyCard("Atlantic Avenue" ,"Yellow"));
	cards.add( new PropertyCard("Marvin Gardens" ,"Yellow"));
	cards.add( new PropertyCard("Ventnor Avenue" ,"Yellow"));
	cards.add( new PropertyCard("Illinois Avenue","Red"));
	cards.add( new PropertyCard("Indiana Avenue","Red"));
	cards.add( new PropertyCard("Kentucky Avenue","Red"));
	cards.add( new PropertyCard("St. James Place","Orange"));
	cards.add( new PropertyCard("Tennessee Avenue","Orange"));
	cards.add( new PropertyCard("New York Avenue","Orange"));
	cards.add( new PropertyCard("St. Charles Place","Pink"));
	cards.add( new PropertyCard("Virginia Avenue","Pink"));
	cards.add( new PropertyCard("States Avenue","Pink"));
	cards.add( new ActionCard("Sly Deal"));
	cards.add( new ActionCard("Forced Deal"));
	cards.add( new ActionCard("It's my Birthday!"));
	cards.add(new RentCard("Yellow"));
	cards.add(new RentCard("Pink"));
	cards.add(new RentCard("Orange"));
	cards.add(new RentCard("Red"));
	cards.add(new RentCard("Green"));}
	
	ALStack<Card> deck = new ALStack<Card>();
	for (int i = 0; i < cards.size(); i++) {
	    int rand = (int) (cards.size() * Math.random());
	    deck.push( cards.remove(rand) );
	}
	
	CLList<Player> players = new CLList<Player>();
	System.out.println("Hello! Welcome to the land of Monopoly Deal! Here are some rules to help you get started The objective of Monopoly Deal is to be the first player to complete 3 full property sets on the playing field. A set is 3 properties that have the same color. There are 3 places where cards can be played during a turn: \n1. A player can place money cards or action cards in their bank.\n2. A player can play property cards in their property section.\n3. A player can play action cards to use them.\n\nLet's get started. What is your name?");
	Scanner sc = new Scanner(System.in);
	String n = sc.nextLine();
	Player P1 = new Player(n);
	Player P2 = new Player("Julie");
	Player P3 = new Player("Alicia");
	Player P4 = new Player("Potato");
	players.add(P1);
	players.add(P2);
	players.add(P3);
	players.add(P4);
	System.out.println("GAME STARTS NOW. EVERYONE DRAWS 5 CARDS.");

	for (int i = 0; i <5; i++){
	    P1.draw(deck);
	    P2.draw(deck);
	    P3.draw(deck);
	    P4.draw(deck);
	}

	P1.turn(players, deck);
	
    }
    
}
