/*****************************************************
 * class BST
 * Implementation of a BINARY SEARCH TREE
 * A BST maintains the invariant that, for any node N with value V, 
 * L<V && V<R, where L and R are node values in N's left and right
 * subtrees, respectively.
 * This BST only holds ints (its nodes have int cargo)
 *****************************************************/

public class BST {

    //instance variables
    TreeNode _root;


    /*****************************************************
     * default constructor
     * Initializes an empty tree.
     *****************************************************/
    BST( ) 
    {
	_root = null;
    }



    /*****************************************************
     * void insert( int ) 
     * Adds a new data element to the tree at appropriate location.
     *****************************************************/
    public void insert( int newVal ) 
    {
	TreeNode newNode = new TreeNode( newVal );

	if ( _root == null ) {
	    _root = newNode;
	    return;
	}
        insert( _root, newNode );
    }
    //recursive helper for insert(int)
    public void insert( TreeNode stRoot, TreeNode newNode ) 
    {
	if ( newNode.getValue() < stRoot.getValue() ) {
	    //if no left child, make newNode the left child
	    if ( stRoot.getLeft() == null )
		stRoot.setLeft( newNode );
	    else //recurse down left subtree
		insert( stRoot.getLeft(), newNode );
	    return;
	}
	else { // new val >= curr, so look down right subtree
	    //if no right child, make newNode the right child
	    if ( stRoot.getRight() == null )
		stRoot.setRight( newNode );
	    else //recurse down right subtree
		insert( stRoot.getRight(), newNode );
	    return;
	}
    }



    /*****************************************************
     * TreeNode search(int)
     * returns pointer to node containing target,
     * or null if target not found
     *****************************************************/
    public TreeNode search( int target )
    {
	return search( target, _root );
    }
    public TreeNode search( int target, TreeNode currNode )
    {
	if ( currNode==null || currNode.getValue()==target )
	    return currNode;
	else if ( target < currNode.getValue() )
	    return search( target, currNode.getLeft() );
	else if ( target > currNode.getValue() )
	    return search( target, currNode.getRight() );
	else
	    return null; //to get past compiler
    }



    /*****************************************************
     * TreeNode remove( int )
     * if remVal is present, removes it from tree
     * Assumes no duplicates in tree.
     *****************************************************/
    public TreeNode remove( int remVal )
    {
	TreeNode leader = _root;  
	TreeNode follower = null; //piggybacker

	//first, walk leader ptr down to target node w/ trailing follower ptr
	while( leader != null && leader.getValue() != remVal ) {
	    if ( remVal < leader.getValue() ) {
		follower = leader;
		leader = leader.getLeft();
	    }
	    else { // remVal > leader.getValue()
		follower = leader;
		leader = leader.getRight();
	    }
	}

	if ( leader == null ) //remVal was not in tree
	    return leader;
	
	//CASE 1: removal node is a leaf
	//action: snip it
	if ( isLeaf(leader) ) {
	    //subcase: 1-node tree
	    if ( leader==_root ) {
		_root = null;
		return leader;
	    }

	    //subcase: removal node is a left child
	    if ( follower.getLeft()==leader ) { 
		follower.setLeft( null );
		return leader;
	    } 
	    //subcase: removal node is a right child
	    else { 
		follower.setRight( null );
		return leader;
	    }
	}

	//CASE 2: removal node has 1 subtree
	//action: replace node with only child
	else if ( leader.getRight()==null ) { //rem node's child is on left

	    //subcase: removal node is root
	    if ( leader==_root ) {
		_root = leader.getLeft();
		return leader;
	    }

	    //subcase: removal node is a left child
	    if ( follower!=null && follower.getLeft()==leader ) { 
		follower.setLeft( leader.getLeft() );
		return leader;
	    } 
	    //subcase: removal node is a right child
	    else if ( follower!=null ) { 
		follower.setRight( leader.getLeft() );
		return leader;
	    }
	}
	else if ( leader.getLeft()==null ) { //rem node's child is on right

	    //subcase: removal node is root
	    if ( leader==_root ) {
		_root = leader.getRight();
		return leader;
	    }

	    //subcase: removal node is a left child
	    if ( follower!=null && follower.getLeft()==leader ) { 
		follower.setLeft( leader.getRight() );
		return leader;
	    } 
	    //subcase: removal node is a right child
	    else { 
		follower.setRight( leader.getRight() );
		return leader;
	    }
	}

	//CASE 3: removal node has 2 subtrees
	//action: overwrite removal node value with max value in left subtree
	//        (deepest node with no right child), then remove that node, 
	//        promoting its left child if exists
	else {
            TreeNode maxLST = leader.getLeft();
            while( maxLST.getRight() != null ) {
		maxLST = maxLST.getRight();
            }

	    //create replacement node for removal node
            TreeNode tmp = new TreeNode( maxLST.getValue() );
            tmp.setLeft( leader.getLeft() );
            tmp.setRight( leader.getRight() );

            remove( maxLST.getValue() );

	    //subcase: removal node is root
            if ( follower==null ) { 
                _root = tmp;
                return leader;
            }

	    //subcase: removal node is a left child
            if ( follower.getLeft() == leader) {
                follower.setLeft( tmp );
            }
	    //subcase: removal node is a right child
            else {
                follower.setRight( tmp );
            }
	}
	return leader;
    }//end remove()



    /*****************************************************
     * int height()
     * returns height of this tree (length of longest leaf-to-root path)
     * eg: a 1-node tree has height 1
     *****************************************************/
    public int height()
    {
	return height( _root );
    }
    //recursive helper for height()
    public int height( TreeNode currNode )
    {
	if ( currNode==null ) //Q: Why cannot use .equals() ?
	    return 0;
	if ( isLeaf(currNode) )
	    return 1;
	else //height is 1 for this node + height of deepest subtree
	    return 1 + Math.max( height(currNode.getLeft()),
				 height(currNode.getRight()) );
    }



    /*****************************************************
     * int numLeaves()
     * returns number of leaves in tree
     *****************************************************/
    public int numLeaves()
    {
	return numLeaves( _root );
    }
    public int numLeaves( TreeNode currNode ) { 
	int foo = 0;
	if ( currNode == null )
	    return 0;
	foo += numLeaves( currNode.getLeft() );
	if ( isLeaf(currNode) )
	    foo++;
	foo += numLeaves( currNode.getRight() );
	return foo;
    }



    //~~~~~~~~~~~~~v~~MISC.HELPERS~~v~~~~~~~~~~~~~~~~~~~
    public boolean isLeaf( TreeNode node ) { 
	return ( node.getLeft() == null && node.getRight() == null );
    }
    //~~~~~~~~~~~~~^~~MISC.HELPERS~~^~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~~v~~TRAVERSALS~~v~~~~~~~~~~~~~~~~~~~~~
    //process root, recurse left, recurse right
    public void preOrderTrav() {
	preOrderTrav( _root );
    }
    public void preOrderTrav( TreeNode currNode ) {
	if ( currNode == null )
	    return;
	System.out.print( currNode.getValue() + " " );
	preOrderTrav( currNode.getLeft() );
	preOrderTrav( currNode.getRight() );
    }

    //recurse left, process root, recurse right
    public void inOrderTrav() {
	inOrderTrav( _root );
    }
    public void inOrderTrav( TreeNode currNode ) {
	if ( currNode == null )
	    return;
	inOrderTrav( currNode.getLeft() );
	System.out.print( currNode.getValue() + " " );
	inOrderTrav( currNode.getRight() );
    }

    //recurse left, recurse right, process root
    public void postOrderTrav() {
	postOrderTrav( _root );
    }
    public void postOrderTrav( TreeNode currNode ) {
	if ( currNode == null )
	    return;
	postOrderTrav( currNode.getLeft() );
	postOrderTrav( currNode.getRight() );
	System.out.print( currNode.getValue() + " "  );
    }

    public String inOrderTravStr( TreeNode currNode ) {
	String retStr = "";
	if ( currNode == null )
	    return retStr;
	retStr += inOrderTravStr( currNode.getLeft() );
	retStr += " " + currNode.getValue();
	retStr += inOrderTravStr( currNode.getRight() );
	return retStr;
    }
    public String preOrderTravStr( TreeNode currNode ) {
	String retStr = "";
	if ( currNode == null )
	    return retStr;
	retStr += " " + currNode.getValue();
	retStr += preOrderTravStr( currNode.getLeft() );
	retStr += preOrderTravStr( currNode.getRight() );
	return retStr;
    }
    public String postOrderTravStr( TreeNode currNode ) {
	String retStr = "";
	if ( currNode == null )
	    return retStr;
	retStr += postOrderTravStr( currNode.getLeft() );
	retStr += postOrderTravStr( currNode.getRight() );
	retStr += " " + currNode.getValue();
	return retStr;
    }
    //~~~~~~~~~~~~~^~~TRAVERSALS~~^~~~~~~~~~~~~~~~~~~~~~
	
	public int sum(){
		int total;
			if ( currNode == null )
		    return;
		total = total + currNode.getValue() ;
	total =total + sum( currNode.getLeft() );
	total = total + sum( currNode.getRight() );
		return total;
	    }	
	}


    //overridden toString
    public String toString()
    {
	return
	    " pre-order trav:" + preOrderTravStr( _root ) + '\n' +
	    "  in-order trav:" + inOrderTravStr( _root ) + '\n' +
	    "post-order trav:" + postOrderTravStr( _root ) + '\n' +
	    "         height: " + height() + '\n' +
	    "     num leaves: " + numLeaves() 
	    ;
    }



    //main method for testing
    public static void main( String[] args ) {

	BST arbol = new BST();

	System.out.println( "tree init'd: " + arbol );

	//inserting in this order will build a perfect tree
	arbol.insert( 3 );
	arbol.insert( 1 );
	arbol.insert( 0 );
	arbol.insert( 2 );
	arbol.insert( 5 );
	arbol.insert( 4 );
	arbol.insert( 6 );
	/*
	*/

	//insering in this order will build a linked list to left
	/*
	arbol.insert( 6 );
	arbol.insert( 5 );
	arbol.insert( 3 );
	arbol.insert( 4 );
	arbol.insert( 2 );
	arbol.insert( 1 );
	arbol.insert( 0 );
	*/

	System.out.println( "tree after insertions:\n" + arbol );
	System.out.println();

	System.out.println();
	for( int i=-1; i<8; i++ ) {
	    System.out.println(" searching for "+i+": " + arbol.search(i) );    
	}

	System.out.println();
	System.out.println( arbol );

	arbol.remove(6);
	System.out.println();
	System.out.println( arbol );

	arbol.remove(5);
	System.out.println();
	System.out.println( arbol );

	arbol.remove(4);
	System.out.println();
	System.out.println( arbol );

	arbol.remove(3);
	System.out.println();
	System.out.println( arbol );

	arbol.remove(2);
	System.out.println();
	System.out.println( arbol );

	arbol.remove(1);
	System.out.println();
	System.out.println( arbol );

	arbol.remove(0);
	System.out.println();
	System.out.println( arbol );

	/*~~~~~~~~~~~~move~me~down~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }

}//end class
