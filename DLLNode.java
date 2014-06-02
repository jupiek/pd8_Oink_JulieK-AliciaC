/*****************************************************
 * class DLLNode
 * Implements a doubly-linked node, for use in linear container classes.
 *****************************************************/

public class DLLNode<T> {

    private T _cargo;    //cargo may only be of type T
    private DLLNode<T> _nextNode, _prevNode; //pointers to next, prev DLLNodes


    // constructor -- initializes instance vars
    public DLLNode( T value, DLLNode<T> prev, DLLNode<T> next ) {
	_cargo = value;
	_nextNode = next;
	_prevNode = prev;
    }


    //--------------v  ACCESSORS  v--------------
    public T getValue() { return _cargo; }

    public DLLNode<T> getNext() { return _nextNode; }

    public DLLNode<T> getPrev() { return _prevNode; }
    //--------------^  ACCESSORS  ^--------------


    //--------------v  MUTATORS  v--------------
    public T setValue( T newVal ) {
	T foo = getValue();
	_cargo = newVal;
	return foo;
    }

    public DLLNode<T> setNext( DLLNode<T> newNext ) {
	DLLNode<T> foo = getNext();
	_nextNode = newNext;
	return foo;
    }

    public DLLNode<T> setPrev( DLLNode<T> newPrev ) {
	DLLNode<T> foo = getPrev();
	_prevNode = newPrev;
	return foo;
    }
    //--------------^  MUTATORS  ^--------------



    // override inherited toString
    public String toString() { return _cargo.toString(); }


}//end class DLLNode
