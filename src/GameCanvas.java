import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Diese Klasse erbt von der Klasse Canvas. Sie bietet Methoden zum zeichnen im
 * Canvas und zum Neuzeichnen, nach einer Aktualisierung.
 */
public class GameCanvas extends Canvas{
	private static final long serialVersionUID = 1L;
	private final int step = (FourInARow.DOTRADIUS + FourInARow.DOTSPACE);
	private UserInterface userInterface;

	/**
	 * Dem Konstruktor von GameCanvas wird die Referenz auf das Objekt userInterface übergeben.
	 * Dazu werden MouseListener und MouseMotionListene zum Canvas hinzugefügt. Als Beobachter dient
	 * userInterface.
	 * @param userInterface
	 */
	GameCanvas(UserInterface userInterface){
		this.userInterface = userInterface;
		addMouseListener(userInterface);
		addMouseMotionListener(userInterface);
	}

	/**
	 * Implementierung der Methode upsate der Klasse Canvas.
	 * Damit wird beim Aufruf von repaint einfach paint aufgerufen.
	 * Dies verbessert die Darstellung.
	 * @param Graphics g
	 */
	public void update (Graphics g) {
		paint(g);
	}
	
	/**
	 * Die Methode paint der Klasse Canvas dient zum Zeichnen ins Canvas.
	 * Je nach Spielzustand, ist die dastellung anders.
	 * Die Informationen zur Darstellung, also die Position der Steine, die Position der
	 * Auswahlbalken in Spiel und Menu, sind im Objekt Grid gespeichert.
	 */
	public void paint (Graphics grid) {
		//Bild Buffern, zur flüssigen Darstellung
		Image bufferedImage = createImage (getWidth(), getHeight());
		Graphics g = bufferedImage.getGraphics();

		if(userInterface.gameGrid.gameState == GameState.Play || userInterface.gameGrid.gameState == GameState.Won) {
			//zeichne Hintergrund
			grid.setColor(Color.BLUE);
			grid.fillRect(0, 0, FourInARow.COL*(2*FourInARow.DOTRADIUS) + (FourInARow.COL+1)*FourInARow.DOTSPACE, FourInARow.ROW*(2*FourInARow.DOTRADIUS) + (FourInARow.ROW+1)*FourInARow.DOTSPACE);
			//zeichne Auswahlbalken
			if (userInterface.gameGrid.gameState == GameState.Play) {
				grid.setColor(Color.lightGray);
				grid.fillRect(userInterface.gameGrid.posHighlightBar * (FourInARow.DOTRADIUS + FourInARow.DOTSPACE), 0, FourInARow.DOTRADIUS + FourInARow.DOTSPACE, FourInARow.DOTRADIUS * FourInARow.COL + 2*FourInARow.DOTSPACE);
			}
			//zeichne Steine
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
			//Darstellung der Gewinnerkombination mit grünem Balken.
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
			//zeichne Hintergrund
			grid.setColor(Color.BLUE);
			grid.fillRect(0, 0, FourInARow.COL*(2*FourInARow.DOTRADIUS) + (FourInARow.COL+1)*FourInARow.DOTSPACE, FourInARow.ROW*(2*FourInARow.DOTRADIUS) + (FourInARow.ROW+1)*FourInARow.DOTSPACE);
			//zeichne Menu mit Auswahlbalken
			grid.setColor(Color.lightGray);
			grid.fillRect(0, userInterface.gameGrid.posMenuHighlightBar * (2*(FourInARow.DOTRADIUS + FourInARow.DOTSPACE)) , (FourInARow.COL*(FourInARow.DOTRADIUS + FourInARow.DOTSPACE)), (2*(FourInARow.DOTRADIUS + FourInARow.DOTSPACE)));
			grid.setColor(Color.WHITE);
			grid.setFont(new Font("Arial", Font.PLAIN, 50)); 
			grid.drawString("Start", FourInARow.COL/2 * (FourInARow.DOTRADIUS + FourInARow.DOTSPACE), 140);
		}
		
		//gebuffertes Bild anzeigen
		g.drawImage(bufferedImage, 0, 0, this);
	}
	
}
