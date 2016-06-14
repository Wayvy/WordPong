package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import game.WordPass;
import infrastructure.ConnectionController;
import infrastructure.PassController;
import listeners.ConnectioDialogioListener;
import listeners.SendingPassListener;
import listeners.TypeOffListener;

//import javafx.scene.paint.Color;

/**
 * A class listing all possible recurring states the game can have. Each state
 * is represented with a method. Gets created by the GameFrame constructor.
 * 
 * @author fjiz
 * @version 0.2
 * @see gui.GameFrame GameFrame()
 */
public class FrameStates {

	GameFrame gframe;

	private JLabel responseLabel;
	private JLabel infoLabel;
	private JButton btn;
	private JTextField playType;
	private ConnectioDialogioListener openDialogLst;
	private SendingPassListener sendingPassLst;
	private WordPass wordPass;
	private TypeOffListener typeOffListener;
	private PassController passController;
	/**
	 * Creates an object with methods for the recuring frame states. Also a
	 * method for sending them to the GameFrame
	 * 
	 * @param gframe
	 *            The GameFrame this is responding to.
	 * @see gui.GameFrame GameFrame()
	 */
	public FrameStates(GameFrame gframe) {
		openDialogLst = new ConnectioDialogioListener(gframe, this, new ConnectionController(this));
		typeOffListener = new TypeOffListener(playType, wordPass, infoLabel);
		
		this.gframe = gframe;

		this.responseLabel = gframe.getResponseLabel();
		this.infoLabel = gframe.getInfoLabel();
		this.btn = gframe.getBtn();
		this.playType = gframe.getPlayType();
	}
	
	public void setPassController(PassController passController)
	{
		this.passController = passController;
	}

	public void initFrame() {
		
		infoLabel.setText("Press Start");
		btn.addActionListener(openDialogLst);
		responseLabel.setText("");
		btn.setText("Start Game");
		playType.setEditable(false);
		send();
	}

	public void startFrame() {
		btn.removeActionListener(openDialogLst);
		btn.addActionListener(sendingPassLst);
		infoLabel.setText("Player 1 picks Word");
		responseLabel.setText("");
		btn.setText("Begin");
		playType.setEditable(true);
		send();
	}

	public void activFrame() {
		btn.addActionListener(typeOffListener);
		infoLabel.setText("Player 1 types off Word");
		responseLabel.setText(wordPass.getWordToPass());
		btn.setText("");
		playType.setEditable(true);
		send();
	}

	public void passivFrame() {
		btn.removeActionListener(sendingPassLst);
		infoLabel.setText("Wait for Other Player to make Turn");
		responseLabel.setText("");
		btn.setText("");
		playType.setEditable(false);
		send();
	}

	public void afterActiveFrame() {
		btn.removeActionListener(typeOffListener);
		infoLabel.setText("Player 1 picks Word");
		responseLabel.setText("");
		btn.setText("Send");
		playType.setEditable(true);
		send();
	}

	public void send() {
		gframe.setBtn(btn);
		gframe.setInfoLabel(infoLabel);
		gframe.setResponseLabel(responseLabel);
		gframe.setPlayType(playType);
	}

}
