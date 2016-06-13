package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates the main frame for the game.
 * 
 * @author Fjiz
 * @version 0.4
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements Runnable {

	// Classmember
	private JPanel backPane = new JPanel();
	private JTextField playType = new JTextField(20);
	private JButton btn = new JButton();
	private JLabel infoLabel = new JLabel();
	private JLabel responseLabel = new JLabel("");
	private GameFrame gframe;
	private FrameStates states;

	/**
	 * Constructor for the GameFrame. Sets the frame title, creates the layout
	 * and an instance of FrameStates whom it calls the initFrame(). Getters and
	 * setters for the JButton, the JLabels and the JTextField.
	 * 
	 * @see gui.FrameStates FrameStates()
	 * @see gui.FrameStates#initFrame() initFrame()
	 */
	public GameFrame() {
		super("WordPong");
		gframe = this;

		backPane.setLayout(new BoxLayout(backPane, BoxLayout.Y_AXIS));
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		responseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);

		backPane.add(Box.createRigidArea(new Dimension(0, 5)));
		backPane.add(infoLabel);
		backPane.add(Box.createRigidArea(new Dimension(0, 5)));
		backPane.add(playType);
		backPane.add(Box.createRigidArea(new Dimension(0, 5)));
		backPane.add(responseLabel);
		backPane.add(Box.createRigidArea(new Dimension(0, 5)));
		backPane.add(btn);

		add(backPane);

		// ToDo rules schreiben :)

		// Adding menu for rules.
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);

		menu.add(Box.createHorizontalGlue());
		menu.setPreferredSize(new Dimension(80, 20));
		JMenu about = new JMenu("About");

		menu.add(about);
		JMenuItem rules = new JMenuItem("Rules");
		about.add(rules);

		// ToDo: ActionListener in FrameStates verschieben
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StartDialog(gframe);

			}
		});

		// produce all frame states call first
		states = new FrameStates(gframe);
		states.initFrame();
	}

	private static void createAndShowGUI() {
		GameFrame gframe = new GameFrame();
		gframe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		gframe.setResizable(true);
		gframe.pack();
		gframe.setVisible(true);

	}

	// Getter und Setter

	public JTextField getPlayType() {
		return playType;
	}

	public void setPlayType(JTextField playType) {
		this.playType = playType;
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	public JLabel getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(JLabel infoLabel) {
		this.infoLabel = infoLabel;
	}

	public JLabel getResponseLabel() {
		return responseLabel;
	}

	public void setResponseLabel(JLabel responseLabel) {
		this.responseLabel = responseLabel;
	}

	// @Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
