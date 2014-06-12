import java.util.*;
import java.io.*;

public class Driver {

    public static void main( String[] args ) {

	RQueue<Card> deck = new RQueue<Card>();
	PropertyCard a = new PropertyCard("Pennsylvania Avenue", "Green");
	deck.enqueue(a);
	deck.enqueue( new PropertyCard("North Carolina Avenue", "Green"));
	deck.enqueue( new PropertyCard("Pacific Avenue","Green"));
	deck.enqueue( new PropertyCard("Atlantic Avenue" ,"Yellow"));
	deck.enqueue( new PropertyCard("Marvin Gardens" ,"Yellow"));
	deck.enqueue( new PropertyCard("Ventnor Avenue" ,"Yellow"));
	deck.enqueue( new PropertyCard("Illinois Avenue","Red"));
	deck.enqueue( new PropertyCard("Indiana Avenue","Red"));
	deck.enqueue( new PropertyCard("Kentucky Avenue","Red"));
	deck.enqueue( new PropertyCard("St. James Place","Orange"));
	deck.enqueue( new PropertyCard("Tennessee Avenue","Orange"));
	deck.enqueue( new PropertyCard("New York Avenue","Orange"));
	deck.enqueue( new PropertyCard("St. Charles Place","Pink"));
	deck.enqueue( new PropertyCard("Virginia Avenue","Pink"));
	deck.enqueue( new PropertyCard("States Avenue","Pink"));


	deck.enqueue(new ActionCard("Sly Deal"));
	deck.enqueue(new ActionCard("Forced Deal"));
	deck.enqueue(new ActionCard("It's my Birthday!"));
	//  deck.enqueue(ActionCard("Rent"));

	deck.sample();

	CLList<Player> players = new CLList<Player>();
	new Player P1 = new Player();
	new Player P2 = new Player();
	new Player P3 = new Player();
	new Player P4 = new Player();
	players.add(P1);
	players.add(P2);
	players.add(P3);
	players.add(P4);
	P1.turn(players, deck);

    }
    
}
