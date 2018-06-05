import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class GameCanvas extends Canvas{
	private static final long serialVersionUID = 1L;
	private UserInterface userInterface;
	
	GameCanvas(UserInterface userInterface){
		this.userInterface = userInterface;
	}

	public void paint (Graphics grid) {

		//draw Background
		grid.setColor(Color.BLUE);
		grid.fillRect(0, 0, FourInARow.COL*(2*FourInARow.DOTRADIUS), FourInARow.ROW*(2*FourInARow.DOTRADIUS));

		//draw Dots
		for(int y = 0; y < FourInARow.ROW; y++) {
			for(int x = 0; x < FourInARow.COL; x++) {
				if (userInterface.gameGrid.grid[x][y]==1) {
					grid.setColor(Color.RED);
				}
				else if (userInterface.gameGrid.grid[x][y]==2) {
					grid.setColor(Color.YELLOW);
				}
				else {
					grid.setColor(Color.WHITE);
				}

				grid.fillOval(x*FourInARow.DOTRADIUS, (y)*FourInARow.DOTRADIUS, FourInARow.DOTRADIUS, FourInARow.DOTRADIUS);

			}
		}
	}
}
