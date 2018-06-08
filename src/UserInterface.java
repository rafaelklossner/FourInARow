import java.awt.*;
import java.awt.event.*;

public class UserInterface extends Frame implements ActionListener{
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
		int randHeight = 77;

		//Add buttons to select row
		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(1,FourInARow.COL));
		for(int i = 0; i < FourInARow.COL; i++) {
			Button button = new Button("Spalte " + i);
			button.addActionListener(this);
			buttonPanel.add(button);
		}
		add(BorderLayout.NORTH, buttonPanel);

		
		gameMatrix1 = new GameCanvas(this);
		add(BorderLayout.CENTER, gameMatrix1);		

		//to repaint: gameMatrix1.paint
		
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
		
		setSize(FourInARow.COL*FourInARow.DOTRADIUS + xOffset + (FourInARow.COL+1)*FourInARow.DOTSPACE ,FourInARow.ROW*FourInARow.DOTRADIUS + yOffset + (FourInARow.ROW+1)*FourInARow.DOTSPACE + randHeight);
		setResizable(false);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent event) {
		int status = 0;
		rowPressed= event.getActionCommand();
		switch(rowPressed) {
		case "Spalte 0" :
			status = gameGrid.placeStone(0);
			break;
		case "Spalte 1" :
			status = gameGrid.placeStone(1);
			break;
		case "Spalte 2" :
			status = gameGrid.placeStone(2);
			break;
		case "Spalte 3" :
			status = gameGrid.placeStone(3);
			break;
		case "Spalte 4" :
			status = gameGrid.placeStone(4);
			break;
		case "Spalte 5" :
			status = gameGrid.placeStone(5);
			break;
		case "Spalte 6" :
			status = gameGrid.placeStone(6);
			break;
		default:
			System.out.println("Invalid button press");
			break;
			
		}
		
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
}
