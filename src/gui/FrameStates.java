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

	private GameFrame gframe;

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
	 * Creates an object with methods for the recurring frame states. Also a
	 * method for sending them to the GameFrame
	 * 
	 * @param gframe
	 *            The GameFrame this is responding to.
	 * @see gui.GameFrame GameFrame()
	 */
	public FrameStates(GameFrame gframe) {
		wordPass = new WordPass("word"); // TODO Hart gecoded -> Connect Backend
		this.gframe = gframe;

		this.responseLabel = gframe.getResponseLabel();
		this.infoLabel = gframe.getInfoLabel();
		this.btn = gframe.getBtn();
		this.playType = gframe.getPlayType();

		openDialogLst = new ConnectioDialogioListener(gframe, this, new ConnectionController(this));
		typeOffListener = new TypeOffListener(playType, wordPass, infoLabel, this);
		
	}

	/**
	 * @param passController
	 */

	public void setPassController(PassController passController) {
		this.passController = passController;
		System.out.println("Pass Controller has been set");
	}

	/**
	 * The state before the player connects to the other player.
	 */
	public void initFrame() {
		//Setup Listeners
		btn.addActionListener(openDialogLst);
		playType.addActionListener(openDialogLst);

		// Set Text of the FrameComponents
		infoLabel.setText("Press Start");
		responseLabel.setText("");
		btn.setText("Start Game");
		playType.setEditable(false);

	}

	/**
	 * The Frame for the player who starts the game
	 */
	public void startFrame() {
		//Setup Listeners
		btn.setEnabled(true);
		btn.removeActionListener(openDialogLst);
		playType.removeActionListener(openDialogLst);
		btn.addActionListener(sendingPassLst);
		playType.addActionListener(sendingPassLst);

		// Set Text of the FrameComponents
		infoLabel.setText("Player 1 picks Word");
		responseLabel.setText("");
		btn.setText("Begin");
		sendingPassLst = new SendingPassListener(playType, passController, this);

		playType.setEditable(true);
	}
	
	public void waitForJoin()
	{
		btn.setEnabled(false);
		btn.setText("");
		infoLabel.setText("Wait for other Player to join");
	}

	/**
	 * State in which the player catch the pass, so type out the word send from
	 * the other player.
	 */
	public void activFrame() {
		//Setup Listeners
		btn.addActionListener(typeOffListener);
		playType.addActionListener(typeOffListener);

		// Set Text of the FrameComponents
		infoLabel.setText("Player 1 types off Word");
		// TODO responseLabel.setText(wordPass.getWordToPass());
		responseLabel.setText(wordPass.getWordToPass());
		//
		btn.setText("Type out");
		playType.setEditable(true);
	}

	/**
	 * In this state the Player waits for the other player to catch the pass and
	 * to send a new pass.
	 */
	public void passivFrame() {
		//Setup Listeners
		btn.removeActionListener(sendingPassLst);
		playType.removeActionListener(sendingPassLst);

		// Set Text of the FrameComponents
		infoLabel.setText("Wait for Other Player to make Turn");
		responseLabel.setText("");
		btn.setText("");
		playType.setEditable(false);
		playType.setText("");

		// TODO Only to test the Frontend -> Implement Backend
		/*new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					activFrame();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		*/
		passController.catchPass();
		activFrame();

	}

	/**
	 * The state of the frame in which the player picks a word and then sends it
	 * via the button
	 */
	public void afterActiveFrame() {
		//Setup Listeners
		btn.removeActionListener(typeOffListener);
		playType.removeActionListener(typeOffListener);
		btn.addActionListener(sendingPassLst);
		playType.addActionListener(sendingPassLst);

		// Set Text of the FrameComponents
		infoLabel.setText("Player 1 picks Word");
		playType.setText("");
		responseLabel.setText("");
		btn.setText("Send");
		playType.setEditable(true);
	}

}
