import java.awt.*;
import java.awt.event.*;

class ButtonRow extends Panel{
	public ButtonRow (int Line, int Column) {
		setLayout(new GridLayout(Line, Column));
		for(int i = 0; i < Line * Column; i++) {
			add(new Button("Spalte" + i));
		}
	}

}
class GameMatrix extends Canvas{
	Color dotColor0, dotColor1, dotColor2;
	Color backgroundColor;
	int lengthMatrix;
	int heightMatrix;
	int dotRadius;
	int border;
	int gameBoard [][];//=new int [lengthMatrix][heightMatrix];


	GameMatrix(Color backgroundColor, int lengthMatrix, int heightMatrix, int dotRadius, int border, int gameBoard[][]){
		this.backgroundColor = backgroundColor;
		this.lengthMatrix = lengthMatrix;
		this.heightMatrix = heightMatrix;
		this.dotRadius = dotRadius;	
		this.border = border;
		this.gameBoard = gameBoard;
		
	}

	public void paint (Graphics grid) {

		//draw Background
		grid.setColor(backgroundColor);
		grid.fillRect(0, 0, lengthMatrix*(2*dotRadius), heightMatrix*(2*dotRadius));
		
		//draw Dots
		for(int y = 0; y < heightMatrix; y++) {
			for(int x = 0; x < lengthMatrix; x++) {
				if (gameBoard[x][y]==1) {
					grid.setColor(Color.RED);
				}
				else if (gameBoard[x][y]==2) {
					grid.setColor(Color.YELLOW);
				}
				else {
					grid.setColor(Color.WHITE);
				}

				grid.fillOval(x*dotRadius, (heightMatrix-1-y)*dotRadius, dotRadius, dotRadius);

			}
		}
	}
}

public class UserInterface extends Frame{
	UserInterface(String Title){
		super(Title);
		int lengthMatrix = 7;
		int heightMatrix = 6;
	
		int dotRadius = 200;
		int buttonHeight = 54;
		int xOffset = lengthMatrix-1;
		int yOffset = heightMatrix-1;
		int x = 5;
		int y = 5;
		//todo gameBoard einlesen
		int border = 5;
		int gameBoard [][]=new int [lengthMatrix][heightMatrix];
		gameBoard[0][1]=1;
		gameBoard[0][2]=2;
		
		setSize(lengthMatrix*dotRadius + xOffset ,heightMatrix*dotRadius + yOffset + buttonHeight);

		setResizable(false);

		add(BorderLayout.NORTH, new ButtonRow(1,lengthMatrix));

		add(BorderLayout.CENTER, new GameMatrix(Color.BLUE, lengthMatrix, heightMatrix, dotRadius, border, gameBoard));

		setVisible(true);
	}
}
