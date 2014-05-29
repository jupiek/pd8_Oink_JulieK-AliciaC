public class ActionCard extends Card {

    public ActionCard() {
	super();
    }

    public String getDescription() {
	//the subclasses of ActionCard will describe their own selves, this will be overridden
	return "";
    }

}
