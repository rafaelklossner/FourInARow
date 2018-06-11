import java.awt.*;
import java.awt.event.*;

public class UserInterface extends Frame implements MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	public String rowPressed = null;
	public TextField textField1;
	int gameBoard [][];

	public GameGrid gameGrid = new GameGrid(); //Erzeugen eines Spielfeldes
	GameCanvas gameMatrix1;

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

	public void mouseClicked(MouseEvent e) {
		int xPos = e.getX();
		int yPos = e.getY();
		int col = 0;
		int status = -2;
		int choice = -1;
		if(gameGrid.playing == true) {
			col = xPos / (FourInARow.DOTRADIUS + FourInARow.DOTSPACE);
			status = gameGrid.placeStone(col);
			gameMatrix1.repaint();
			if (status == -1) {
				textField1.setText("Column is full");
			}else if(status == 1) {
				textField1.setText("Player 1 has won");
				gameGrid.playing = false;
				//gameGrid.menu = false;
			}else if(status == 2) {
				textField1.setText("Player 2 has won");
				gameGrid.playing = false;
				//gameGrid.menu = false;
			}else {
				textField1.setText("Player " + gameGrid.player);
			}
		}else {
			choice = yPos / (2*(FourInARow.DOTRADIUS + 2*FourInARow.DOTSPACE));
			if (choice == 0) {
				gameGrid = new GameGrid();
				gameGrid.playing = true;
				gameMatrix1.repaint();
			}
		}	
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		int xPos = e.getX();
		int yPos = e.getY();
		if(gameGrid.playing == true) {
			int col = xPos / (FourInARow.DOTRADIUS + FourInARow.DOTSPACE);
			gameGrid.placeHighlightBar(col);
			gameMatrix1.repaint();	
		}else {
			int choice = yPos / (2*(FourInARow.DOTRADIUS + FourInARow.DOTSPACE));
			gameGrid.placeMenuHighlightBar(choice);
		}
		gameMatrix1.repaint();
	}
}
