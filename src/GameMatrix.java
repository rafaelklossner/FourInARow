import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class GameMatrix extends Canvas{
	Color dotColor0, dotColor1, dotColor2;
	Color backgroundColor;
	int lengthMatrix;
	int heightMatrix;
	int dotRadius;
	int gameBoard [][];


	GameMatrix(Color backgroundColor, int lengthMatrix, int heightMatrix, int dotRadius, int gameBoard[][]){
		this.backgroundColor = backgroundColor;
		this.lengthMatrix = lengthMatrix;
		this.heightMatrix = heightMatrix;
		this.dotRadius = dotRadius;	
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
