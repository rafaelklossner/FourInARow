import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class GameCanvas extends Canvas{
	private static final long serialVersionUID = 1L;
	private final int step = (FourInARow.DOTRADIUS + FourInARow.DOTSPACE);
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

		if(userInterface.gameGrid.gameState == GameState.Play || userInterface.gameGrid.gameState == GameState.Won) {
			//draw Background
			grid.setColor(Color.BLUE);
			grid.fillRect(0, 0, FourInARow.COL*(2*FourInARow.DOTRADIUS) + (FourInARow.COL+1)*FourInARow.DOTSPACE, FourInARow.ROW*(2*FourInARow.DOTRADIUS) + (FourInARow.ROW+1)*FourInARow.DOTSPACE);
			//draw highlightbar
			if (userInterface.gameGrid.gameState == GameState.Play) {
				grid.setColor(Color.lightGray);
				grid.fillRect(userInterface.gameGrid.posHighlightBar * (FourInARow.DOTRADIUS + FourInARow.DOTSPACE), 0, FourInARow.DOTRADIUS + FourInARow.DOTSPACE, FourInARow.DOTRADIUS * FourInARow.COL + 2*FourInARow.DOTSPACE);
			}
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
				}
			}
			if (userInterface.gameGrid.gameState == GameState.Won) {
				int right = 0;
				int up = 0;
				grid.setColor(Color.GREEN);
				Direction direction = userInterface.gameGrid.direction;
				switch(direction) {
				case Up : 
					up = -3;
					right = 0;
					break;
				case UpLeft :
					up = -3;
					right = -3;
					break;
				case UpRight :
					up = -3;
					right = 3;
					break;
				case Down : 
					up = 3;
					right = 0;
					break;
				case DownLeft : 
					up = 3;
					right = -3;
					break;
				case DownRight : 
					up = 3;
					right = 3;
					break;
				case Left : 
					up = 0;
					right = -3;
					break;
				case Right :
					up = 0;
					right = 3;
					break;
				default:
					break;
				}
				grid.drawLine ((userInterface.gameGrid.lastStoneCol)*step + step/2, (userInterface.gameGrid.lastStoneRow)*step + step/2,
						(userInterface.gameGrid.lastStoneCol + right)*step + step/2, (userInterface.gameGrid.lastStoneRow + up)*step + step/2);
			}
		}else if(userInterface.gameGrid.gameState == GameState.Menu) {
			//draw Background
			grid.setColor(Color.BLUE);
			grid.fillRect(0, 0, FourInARow.COL*(2*FourInARow.DOTRADIUS) + (FourInARow.COL+1)*FourInARow.DOTSPACE, FourInARow.ROW*(2*FourInARow.DOTRADIUS) + (FourInARow.ROW+1)*FourInARow.DOTSPACE);
			//draw menu
			grid.setColor(Color.lightGray);
			grid.fillRect(0, userInterface.gameGrid.posMenuHighlightBar * (2*(FourInARow.DOTRADIUS + FourInARow.DOTSPACE)) , (FourInARow.COL*(FourInARow.DOTRADIUS + FourInARow.DOTSPACE)), (2*(FourInARow.DOTRADIUS + FourInARow.DOTSPACE)));
			grid.setColor(Color.WHITE);
			grid.setFont(new Font("Arial", Font.PLAIN, 50)); 
			grid.drawString("Start", FourInARow.COL/2 * (FourInARow.DOTRADIUS + FourInARow.DOTSPACE), 140);
		}
		g.drawImage(bufferedImage, 0, 0, this);
	}
	
}
