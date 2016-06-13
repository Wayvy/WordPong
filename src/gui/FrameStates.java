package gui;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JTextField;

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

	/**
	 * Creates an object with methods for the recuring frame states. Also a
	 * method for sending them to the GameFrame
	 * 
	 * @param gframe
	 *            The GameFrame this is responding to.
	 * @see gui.GameFrame GameFrame()
	 */
	public FrameStates(GameFrame gframe) {
		this.gframe = gframe;

		this.responseLabel = gframe.getResponseLabel();
		this.infoLabel = gframe.getInfoLabel();
		this.btn = gframe.getBtn();
		this.playType = gframe.getPlayType();
	}

	public void initFrame() {
		infoLabel.setText("Press Start");
		responseLabel.setText("");
		btn.setText("Start Game");
		playType.setEditable(false);
		send();
	}

	public void startFrame() {
		infoLabel.setText("Player 1 picks Word");
		responseLabel.setText("");
		btn.setText("Begin");
		playType.setEditable(true);
		send();
	}

	public void activFrame() {

	}

	public void passivFrame() {

	}

	public void endFrame() {

	}

	public void send() {
		gframe.setBtn(btn);
		gframe.setInfoLabel(infoLabel);
		gframe.setResponseLabel(responseLabel);
		gframe.setPlayType(playType);
	}

}
