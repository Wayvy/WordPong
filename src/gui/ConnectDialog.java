package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import listeners.TextFieldListener;

import infrastructure.Client;

/**
 * A dialog, that lets the user input the IP address and the Port of the Server
 * he wants to connect to
 * 
 * @author Wavy
 * @version 0.5
 */
public class ConnectDialog extends JDialog {
	private JTextField IPField = new JTextField(15);
	private JTextField portField = new JTextField(15);

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
		connectBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				connectToHost();
			}
		}); 
		IPField.addFocusListener(new TextFieldListener("Enter IP", IPField));
		portField.addFocusListener(new TextFieldListener("Enter Port", portField));
		
		// Set Configurations
		setSize(200, 120);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	//Creates Socket and connects to the Host
	private void connectToHost() {
		try {
			Socket hero = new Socket(InetAddress.getByName(IPField.getText()), Integer.parseInt(portField.getText()));
			Client client = new Client(hero);
		} catch (NumberFormatException e) {
			// TODO Notify the user, that only numbers are allowed
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Cannot connect to host, so a solution would be to repeat
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Doesn't get input from host ??
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ConnectDialog("Connect To Host");
	}
}
