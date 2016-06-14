package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import game.WordPass;

public class TypeOffListener implements ActionListener {

	private WordPass wordPass;
	private JTextField textField;
	private JLabel infoLabel;

	public TypeOffListener(JTextField textField, WordPass wordPass, JLabel infoLabel) {
		this.textField = textField;
		this.wordPass = wordPass;
		this.infoLabel = infoLabel;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(textField.getText().equals(wordPass.getWordToPass()))
		{
			infoLabel.setText("Correct");
		}
		
		else
		{
			infoLabel.setText("Incorrect");
		}
	}

}
