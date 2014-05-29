public class Yellow extends Property {
	
    public Yellow( String s ) {
	value = 3;
	name = s;
	inField = false;
	type = "Property";
	rent1 = 2;
	rent2 = 4; 
	rent3 = 6;
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
