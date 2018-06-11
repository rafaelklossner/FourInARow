import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class GameCanvas extends Canvas{
	private static final long serialVersionUID = 1L;
	private UserInterface userInterface;
	
	GameCanvas(UserInterface userInterface){
		this.userInterface = userInterface;
		addMouseListener(userInterface);
		addMouseMotionListener(userInterface);
	}
	
	public void update (Graphics g) {
		paint(g);
	}

	public void paint (Graphics grid) {
		Image bufferedImage = createImage (getWidth(), getHeight());
		Graphics g = bufferedImage.getGraphics();
		
		//draw Background
		grid.setColor(Color.BLUE);
		grid.fillRect(0, 0, FourInARow.COL*(2*FourInARow.DOTRADIUS) + (FourInARow.COL+1)*FourInARow.DOTSPACE, FourInARow.ROW*(2*FourInARow.DOTRADIUS) + (FourInARow.ROW+1)*FourInARow.DOTSPACE);
		grid.setColor(Color.lightGray);
		grid.fillRect(userInterface.gameGrid.posHighlightBar * (FourInARow.DOTRADIUS + FourInARow.DOTSPACE), 0, FourInARow.DOTRADIUS + FourInARow.DOTSPACE, FourInARow.DOTRADIUS * FourInARow.COL + 2*FourInARow.DOTSPACE);

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

				grid.fillOval(x*FourInARow.DOTRADIUS + (x+1)*FourInARow.DOTSPACE - FourInARow.DOTSPACE/2, y*FourInARow.DOTRADIUS + (y+1)*FourInARow.DOTSPACE - FourInARow.DOTSPACE/2, FourInARow.DOTRADIUS, FourInARow.DOTRADIUS);
				g.drawImage(bufferedImage, 0, 0, this);
			}
		}
	}
}
