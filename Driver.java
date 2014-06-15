import java.util.*;
import java.io.*;

public class Driver {

    public static void main( String[] args ) {

	ArrayList<Card> cards = new ArrayList<Card>();
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
	cards.add(new RentCard("Green"));
	
	ALStack<Card> deck = new ALStack<Card>();
	for (int i = 0; i < cards.size(); i++) {
	    int rand = (int) (cards.size() * Math.random());
	    deck.push( cards.remove(rand) );
	}
	
	CLList<Player> players = new CLList<Player>();
	Player P1 = new Player("Me");
	Player P2 = new Player("Julie");
        Player P3 = new Player("Alicia");
        Player P4 = new Player("Potato");
	players.add(P1);
	players.add(P2);
	players.add(P3);
	players.add(P4);
	P1.turn(players, deck);
	
    }
    
}
