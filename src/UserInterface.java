import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserInterface extends Frame implements ActionListener{
	public String rowPressed = null;
	public TextField textField1;
	int lengthMatrix;
	int heightMatrix;
	int dotRadius;	//responsible for Window Size
	int gameBoard [][];
	GameMatrix gameMatrix1;
	UserInterface(String Title,int lengthMatrix, int heightMatrix, int dotRadius, int gameBoard[][]){
		super(Title);
		this.lengthMatrix = lengthMatrix;
		this.heightMatrix = heightMatrix;
		this.dotRadius = dotRadius;	//responsible for Window Size
		int xOffset = lengthMatrix-1;
		int yOffset = heightMatrix-1;
		int randHeight = 77;
	
		/*
		//Testing, gameBoard contains position of stones
		int gameBoard [][]=new int [lengthMatrix][heightMatrix];
		gameBoard[0][1]=1;
		gameBoard[0][2]=2;
		gameBoard[5][2]=2;
		*/

		//Add buttons to select row
		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(1,lengthMatrix));
		for(int i = 0; i < lengthMatrix; i++) {
			Button button = new Button("Spalte " + i);
			button.addActionListener(this);
			buttonPanel.add(button);
		}
		add(BorderLayout.NORTH, buttonPanel);

		
		gameMatrix1 = new GameMatrix(Color.BLUE, lengthMatrix, heightMatrix, dotRadius, gameBoard);
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
		
		textField1 = new TextField("-");
		textField1.setEditable(false);
		
		gbc.weightx = 6;
		gbl.setConstraints(textField1, gbc);

		textPanel.add(textField1);
		add(BorderLayout.SOUTH, textPanel);



		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		
		setSize(lengthMatrix*dotRadius + xOffset ,heightMatrix*dotRadius + yOffset + randHeight);
		setResizable(false);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent event) {
		rowPressed= event.getActionCommand();
		gameMatrix1.repaint();
		textField1.setText(rowPressed);
		
	}
}
