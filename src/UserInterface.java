import java.awt.*;
import java.awt.event.*;

public class UserInterface extends Frame implements MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	public String rowPressed = null;
	public TextField textField1;
	public GameGrid gameGrid = new GameGrid(); //Erzeugen eines Spielfeldes
	private GameCanvas gameMatrix1;

	UserInterface(String Title){
		super(Title);
		int xOffset = FourInARow.COL-1;
		int yOffset = FourInARow.ROW-1;

		gameMatrix1 = new GameCanvas(this);
		add(BorderLayout.CENTER, gameMatrix1);		

		//Add text field
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

		setSize(FourInARow.COL*FourInARow.DOTRADIUS + xOffset + (FourInARow.COL)*FourInARow.DOTSPACE ,FourInARow.ROW*FourInARow.DOTRADIUS + yOffset + (FourInARow.ROW)*FourInARow.DOTSPACE + FourInARow.GAMELOGSPACE);
		setResizable(false);
		setVisible(true);
	}

	
	public void mouseReleased(MouseEvent e) {int xPos = e.getX();
		int yPos = e.getY();
		int col = 0;
		int choice = -1;
		if(gameGrid.gameState == GameState.Play) {
			col = xPos / (FourInARow.DOTRADIUS + FourInARow.DOTSPACE);
			InPlayState state = gameGrid.placeStone(col);
			gameMatrix1.repaint();
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
		}else if(gameGrid.gameState == GameState.Menu){
			choice = yPos / (2*(FourInARow.DOTRADIUS + 2*FourInARow.DOTSPACE));
			if (choice == 0) {
				gameGrid = new GameGrid();
				textField1.setText("Player " + gameGrid.player);
				gameGrid.gameState = GameState.Play;
				gameMatrix1.repaint();
			}
		}else if(gameGrid.gameState == GameState.Won){
			textField1.setText("Menu");
			gameMatrix1.repaint();
			gameGrid.gameState = GameState.Menu;
		}
	}
	
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
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
}
