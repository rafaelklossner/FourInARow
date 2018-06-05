
public class FourInARow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int lengthMatrix = 7;
		int heightMatrix = 6;
		int dotRadius = 200;
		int gameBoard [][]=new int [lengthMatrix][heightMatrix];
		gameBoard[0][1]=1;
		gameBoard[0][2]=2;
		gameBoard[5][2]=2;
new UserInterface("Java applicaton with AWT", lengthMatrix, heightMatrix, dotRadius, gameBoard);
	}

}
