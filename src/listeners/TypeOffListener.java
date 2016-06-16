package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import game.WordPass;
import gui.FrameStates;

public class TypeOffListener implements ActionListener {

	private WordPass wordPass;
	private JTextField textField;
	private JLabel infoLabel;
	private FrameStates states;

	public TypeOffListener(JTextField textField, WordPass wordPass, JLabel infoLabel, FrameStates states) {
		this.states = states;
		
		this.textField = textField;
		this.wordPass = wordPass;
		System.out.println(wordPass);
		System.out.println(textField);
		this.infoLabel = infoLabel;

	}

	/**
	 * Method that will update the wordPass. Will probably be called by the
	 * PassController, or the FrameStates
	 * 
	 * @param wordPass
	 */
	public void updateWordPass(WordPass wordPass) {
		this.wordPass = wordPass;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (textField.getText().equals(wordPass.getWordToPass())) {
			infoLabel.setText("Correct");
			states.afterActiveFrame();
			
		}

		else {
			infoLabel.setText("Incorrect");
		}
	}

}
