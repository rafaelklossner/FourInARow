import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class ButtonRow extends Panel implements ActionListener{
	private ArrayList <Button> buttonArray = new ArrayList <Button> ();
	public String rowPressed = null;
	
	public ButtonRow (int Line, int Column) {
		setLayout(new GridLayout(Line, Column));
		for(int i = 0; i < Line * Column; i++) {
			buttonArray.add(new Button("Spalte" + i));	//add Button to ArrayList
			buttonArray.get(i).addActionListener(this);	//add ActionListener to button
			add(buttonArray.get(i));	//add Button to Panel		
		}
	}

	public void actionPerformed(ActionEvent event) {
		rowPressed= event.getActionCommand();
		System.out.println(rowPressed);
	}

}