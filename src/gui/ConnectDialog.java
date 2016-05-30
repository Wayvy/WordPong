package gui;

import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import listeners.TextFieldListener;

/**
 * A dialog, that lets the user input the IP address and the Port of the Server
 * he wants to connect to
 * 
 * @author Wavy
 * @version 0.5
 */
public class ConnectDialog extends JDialog {
	JTextField IPField = new JTextField(15);
	JTextField portField = new JTextField(15);

	/**
	 * Constructs the Dialog and makes it Visible
	 * 
	 * @param title
	 *            - The title of the Dialog
	 */
	public ConnectDialog(String title) {
		
		// Set Layout
		setTitle(title);
		setLayout(new FlowLayout());
		
		// Add local Components
		JButton connectBtn = new JButton("Connect");

		// Set text
		IPField.setText("Enter IP");
		portField.setText("Enter Port");

		// Add components
		add(connectBtn, FlowLayout.LEFT);
		add(portField, FlowLayout.LEFT);
		add(IPField, FlowLayout.LEFT);
		
		// Add Listeners
		connectBtn.addActionListener(connect -> connectToHost());
		IPField.addFocusListener(new TextFieldListener("Enter IP", IPField));
		portField.addFocusListener(new TextFieldListener("Enter Port", portField));
		
		// Set Configurations
		setSize(200, 120);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	//Creates Socket and connects to the Host
	private void connectToHost() {
		//TODO: Create Socket and connect to the Game
	}

	public static void main(String[] args) {
		new ConnectDialog("Connect To Host");
	}
}
