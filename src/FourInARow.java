	/**
	 * Diese Klasse beinhaltet die wichtigsten Paramter zur Berechnung des Spielfeldes.
	 * Mit DOTRADIUS uns DOTSPACE kann die Spielfeldgrösse an andere Bildschrimauflösungen
	 * angepasst werden.
	 */
public class FourInARow {
	public final static int COL = 7;
	public final static int ROW = 6;
	public final static int DOTRADIUS = 100;
	public final static int DOTSPACE = 25;
	public final static int GAMELOGSPACE = 70;
	
	/**
	 * Die Mainmethode des Projekts.
	 */
	public static void main(String[] args) {
		new UserInterface("FourInARow"); 	//UserInreface erzeugen (Spielstart)
	}

}
