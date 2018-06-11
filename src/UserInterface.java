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
		System.out.println(xPos);
		System.out.println((FourInARow.DOTRADIUS + FourInARow.DOTSPACE));
		int col = xPos / (FourInARow.DOTRADIUS + FourInARow.DOTSPACE);
		System.out.println(col);
		int status = gameGrid.placeStone(col);
		if (status == -1) {
			textField1.setText("Column is full");
		}else if(status == 1) {
			textField1.setText("Player 1 has won");
			gameGrid = new GameGrid();
		}else if(status == 2) {
			textField1.setText("Player 2 has won");
			gameGrid = new GameGrid();
		}else {
			textField1.setText("Player " + gameGrid.player);
		}
		gameMatrix1.repaint();	
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		int xPos = e.getX();
		int col = xPos / (FourInARow.DOTRADIUS + FourInARow.DOTSPACE);
		gameGrid.placeHighlightBar(col);
		gameMatrix1.repaint();	
	}
}
