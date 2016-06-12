package gui;

import infrastructure.ConnectionController;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import listeners.TextFieldListener;

/**
 * The dialogcard, that asks for the Port the Join want to use to get a
 * connection. When pressing the JoinBtn, a Thread will be created and lets some
 * other player connect to the Join. While waiting the Information to connect
 * will be output at the JoinGUI
 * 
 * @author Wavy, Fjiz
 * @version 0.7
 */
public class JoinCard extends JPanel {

	private JTextField portField = new JTextField(15);
	private JTextField addressField = new JTextField(15);
	private JButton joinBtn = new JButton("Join Server");
	private JTextArea JoinInfo;
	private int port;

	/**
	 * Creates the Joincard, that asks for a Port number and
	 * checks if the input is a valid port number. Afterwards
	 * a new ServerSocket is created.
	 * 
	 * @param the
	 *            parent frame
	 */
	public JoinCard(GameFrame gframe) {
		
		String host = "192.168.0.104";

		//Default Settup
		addressField.setText(host);
		portField.setText("11200");
		
		// Sets Layout
		setLayout(new FlowLayout());

		// Adds Components
		add(joinBtn, FlowLayout.LEFT);
		add(addressField, FlowLayout.LEFT);
		add(portField, FlowLayout.LEFT);
		
		// Adds Listeners
		portField.addFocusListener(new TextFieldListener("Enter A'n P", portField));
		joinBtn.addActionListener(new ActionListener() {
			// Bin zu bloed den Defaultfocus auf den Button zu legen
			public void actionPerformed(ActionEvent arg0) {
				try {
					port = Integer.parseInt(portField.getText());
					if (port < 1025) {
						portField.setText("invalid port");
					} else {
						Join();
					}
				} catch (NumberFormatException f) {
					portField.setText("invalid input");
				}

			}
		});
	}

	/**
	 * Changes the state of the Dialog to output the Information to connect to
	 * the server
	 */
	private void Join() {

		

			joinBtn.setVisible(false);
			portField.setVisible(false);
			addressField.setVisible(false);

			JoinInfo = new JTextArea(2, 3);
			JoinInfo.setEditable(false);
			add(JoinInfo, FlowLayout.LEFT);
			String JoinIP = addressField.getText();
			JoinInfo.setText("Connecting to " +
					"IP " + JoinIP + "\nvia Port " + port);


			// Alles folgende findet nun im ConnectionController statt 'Wavy'

			ConnectionController playerController = new ConnectionController(port, JoinIP);
			playerController.joinGame();


		}

	
}
