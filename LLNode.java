/*****************************************************
 * class LLNode
 * Implements a node, for use in lists and other container classes.
 *****************************************************/

public class LLNode<Card> {

    private Card _cargo;    //cargo may only be of type Card
    private LLNode<Card> _nextNode; //pointer to next LLNode


    // constructor -- initializes instance vars
    public LLNode( Card value, LLNode<Card> next ) {
	_cargo = value;
	_nextNode = next;
    }


    //--------------v  ACCESSORS  v--------------
    public Card getValue() { return _cargo; }

    public LLNode<Card> getNext() { return _nextNode; }
    //--------------^  ACCESSORS  ^--------------


    //--------------v  MUCardACardORS  v--------------
    public Card setValue( Card newCargo ) {
	Card foo = getValue();
	_cargo = newCargo;
	return foo;
    }

    public LLNode<Card> setNext( LLNode<Card> newNext ) {
	LLNode<Card> foo = getNext();
	_nextNode = newNext;
	return foo;
    }
    //--------------^  MUTATORS  ^--------------


    // override inherited toString
    public String toString() { return _cargo.toString(); }


    //main method for testing
    public static void main( String[] args ) {

	//Below is an exercise in creating a linked list...

	//Create a node
	LLNode<String> first = new LLNode<String>( "cat", null );

	//Create a new node after the first
	first.setNext( new LLNode<String>( "dog", null ) );

	//Create a third node after the second
	first.getNext().setNext( new LLNode<String>( "cow", null ) );

	LLNode temp = first; 
	while( temp != null ) {
	    System.out.println( temp );
	    temp = temp.getNext();
	}

    }//end main

}//end class LLNode
