package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import game.WordPass;
import infrastructure.PassController;

public class SendingTextListener implements ActionListener {

	JTextField sendingField;
	PassController passController;
	
	public SendingTextListener(JTextField sendingField, PassController passController)
	{
		this.sendingField = sendingField;
		this.passController = passController;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		passController.sendPass(new WordPass(sendingField.getText()));
		

	}

}
