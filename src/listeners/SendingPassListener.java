package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import game.WordPass;
import gui.FrameStates;
import infrastructure.PassController;

/**
 * This Listener, will send a WordPass to the other player, when a button is pressed,
 * however first it will be checked, whether the word is inside of the database.
 * @author Wavy
 *
 */
public class SendingPassListener implements ActionListener {

	private JTextField sendingField;
	private PassController passController;
	private FrameStates frameStates;
	
	public SendingPassListener(JTextField sendingField, PassController passController, FrameStates frameStates)
	{
		this.sendingField = sendingField;
		this.passController = passController;
		this.frameStates = frameStates;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO if(wordInDatabase())
		//TODO passController.sendPass(new WordPass(sendingField.getText()));
		frameStates.passivFrame();
		//TODO else show notification

	}

}
