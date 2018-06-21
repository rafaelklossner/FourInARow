import java.awt.*;
import java.awt.event.*;

/**
 * Diese Klasse erbt von Frame und implementiert die Methoden der Klassen
 * MouseListener und MouseMotionListener.
 * Die Klasse stellt sämtliche Schnittstellen zum User bereit. Dies sind:
 * Bildschirm (Frame) und Maus.
 */
public class UserInterface extends Frame implements MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	public String rowPressed = null;
	public TextField textField1;
	public GameGrid gameGrid = new GameGrid(); //Erzeugen eines Spielfeldes
	private GameCanvas gameMatrix1;

	/**
	 * Der Konstruktor von UserInterface fügt das Canvas mit dem Spielfeld und
	 * das Gamelog am unteren Rand zum Frame hinzu.
	 * @param Title
	 */
	UserInterface(String Title){
		super(Title); //Titel an Konsruktor von Frame übergeben
		
		//Canvas erzeugen und zum Frame hinzufügen
		gameMatrix1 = new GameCanvas(this); //Referenz auf das Objekt userInterface übergeben.
		add(BorderLayout.CENTER, gameMatrix1);		

		//Gamelog am unteren Rand hinzufügen
		Panel textPanel = new Panel();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		textPanel.setLayout(gbl);
		gbc.fill = GridBagConstraints.BOTH;

		Label label1 = new Label("Game Log");
		gbc.weightx = 1;
		gbl.setConstraints(label1, gbc);
		textPanel.add(label1);

		textField1 = new TextField("Player " + gameGrid.player);

		gbc.weightx = 6;
		gbl.setConstraints(textField1, gbc);

		textPanel.add(textField1);
		add(BorderLayout.SOUTH, textPanel);

		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		
		// Dynamische Berechung der Framegrösse aus den Konstanten
		setSize(FourInARow.COL*FourInARow.DOTRADIUS + FourInARow.COL-1 + (FourInARow.COL)*FourInARow.DOTSPACE ,
				FourInARow.ROW*FourInARow.DOTRADIUS + FourInARow.ROW-1 + (FourInARow.ROW)*FourInARow.DOTSPACE + FourInARow.GAMELOGSPACE);
		
		setResizable(false); //Skalierung sperren
		setVisible(true);
	}

	/**
	 * Diese Methode reagiert auf das Loslassen der Maustaste.
	 * Damit wird die Eingabe durch den User ermöglicht.
	 * @param MouseEvent e
	 */
	public void mouseReleased(MouseEvent e) {
		//Speichern der Mausposition
		int xPos = e.getX();
		int yPos = e.getY();
		int col = 0;
		int choice = -1;
		
		//Wenn das Spiel läuft
		if(gameGrid.gameState == GameState.Play) {
			col = xPos / (FourInARow.DOTRADIUS + FourInARow.DOTSPACE); //Berechung der angeklickten Spalte
			InPlayState state = gameGrid.placeStone(col); //Stein setzen und Ergebnis abfangen
			gameMatrix1.repaint();
			//Fallunterscheidung für Reihe voll, Unentschieden, Gewinn oder weiter spielen
			if (state == InPlayState.ColumnFull) {
				textField1.setText("Column is full");
			}else if(state == InPlayState.StandOff) {
				textField1.setText("Tie");
				gameGrid.gameState = GameState.Won;
			}else if(state == InPlayState.Player1) {
				textField1.setText("Player 1 has won");
				gameGrid.gameState = GameState.Won;
			}else if(state == InPlayState.Player2) {
				textField1.setText("Player 2 has won");
				gameGrid.gameState = GameState.Won;
			}else {
				textField1.setText("Player " + gameGrid.player);
			}
		//Wenn in menu
		}else if(gameGrid.gameState == GameState.Menu){
			choice = yPos / (2*(FourInARow.DOTRADIUS + 2*FourInARow.DOTSPACE));
			if (choice == 0) {
				gameGrid = new GameGrid();
				textField1.setText("Player " + gameGrid.player);
				gameGrid.gameState = GameState.Play;
				gameMatrix1.repaint();
			}
		//Wenn ein Spieler gerade gewonnen
		}else if(gameGrid.gameState == GameState.Won){
			textField1.setText("Menu");
			gameMatrix1.repaint();
			gameGrid.gameState = GameState.Menu;
		}
	}
	
	/**
	 * Diese Methode reagiert auf die Mausbewegung.
	 * Sie bewegt den Auswahlbalken auf dem Spielfeld und dem Menu.
	 * Je nach Zustand, Play oder Menu, wird der eine oder andere Balken
	 * neu berechnet.
	 * @param MouseEvent e
	 */
	public void mouseMoved(MouseEvent e) {
		int xPos = e.getX();
		int yPos = e.getY();
		if(gameGrid.gameState == GameState.Play) {
			int col = xPos / (FourInARow.DOTRADIUS + FourInARow.DOTSPACE);
			gameGrid.placeHighlightBar(col);
			textField1.setText("Player " + gameGrid.player);	
		}else if(gameGrid.gameState == GameState.Menu){
			int choice = yPos / (2*(FourInARow.DOTRADIUS + FourInARow.DOTSPACE));
			gameGrid.placeMenuHighlightBar(choice);
		}
		gameMatrix1.repaint();
	}
	
	//Implemantation der nichtbenötigten Methoden
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
}
