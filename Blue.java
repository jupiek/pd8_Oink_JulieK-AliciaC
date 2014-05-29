public class Blue extends Property {
	
    public Blue( String s ) {
	    value = 3;
	    name = s;
    	inField = false;
    	type = "Property";
    	rent1 = 3;
    	rent2 = 5; 
    	rent3 = 8;
    }

    public int getRent( int n ) {
    	if (n == 1)
	    return rent1;
	if (n == 2) 
	    return rent2;
	else
	    return rent3;
    }

}
